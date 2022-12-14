/*
 *  Copyright 2022 Jeremy Long
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package io.github.jeremylong.cdnjs.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.github.jeremylong.cdnjs.json.Libraries;
import org.apache.hc.client5.http.async.methods.SimpleHttpRequest;
import org.apache.hc.client5.http.async.methods.SimpleHttpResponse;
import org.apache.hc.client5.http.async.methods.SimpleRequestBuilder;
import org.apache.hc.core5.net.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Jeremy Long
 * @see <a href="https://cdnjs.com/api">cdnjs API</a>
 */
public class CdnjsApi {

    /**
     * Reference to the logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(CdnjsApi.class);
    /**
     * The default endpoint for the cdnsjs libraries API.
     */
    private final static String LIBRARIES_ENDPOINT = "https://api.cdnjs.com/libraries";
    /**
     * Jackson object mapper.
     */
    private final ObjectMapper objectMapper;
    /**
     * The index for the client to use.
     */
    private AtomicInteger clientIndex = new AtomicInteger(0);
    /**
     * The rate meter to limit traffic to the API.
     */
    private RateMeter meter;
    /**
     * The rate limited HTTP client for calling theF APIs.
     */
    private List<RateLimitedClient> clients;
    /**
     * The list of future responses for library calls.
     */
    private LibraryAsynchResults libraryApiCalls = new LibraryAsynchResults();
    /**
     * The list of future responses for library version calls.
     */
    private LibraryAsynchResults versionApiCalls = new LibraryAsynchResults();

    /**
     * Constructs a new cdnjs API client.
     *
     * @param threadCount the number of threads to use when calling the API.
     */
    public CdnjsApi(int threadCount) {
        this(600, threadCount);
    }

    /**
     * Constructs a new API client.
     *
     * @param delay the delay in milliseconds between API calls on a single thread.
     * @param threadCount the number of threads to use when calling the API.
     */
    public CdnjsApi(long delay, int threadCount) {
        if (threadCount <= 0) {
            threadCount = 1;
        }

        meter = new RateMeter(50, 32500);

        clients = new ArrayList<>(threadCount);
        for (int i = 0; i < threadCount; i++) {
            clients.add(new RateLimitedClient(delay, meter));
        }
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    /**
     * Asynchronously calls the API.
     *
     * @return the future
     * @throws CdnjsApiException thrown if there is a problem calling the API
     */
    private SimpleHttpResponse callLibraries() throws CdnjsApiException {
        try {
            URIBuilder uriBuilder = new URIBuilder(LIBRARIES_ENDPOINT + "?fields=filename,version");
            final SimpleRequestBuilder builder = SimpleRequestBuilder.get();
            URI uri = uriBuilder.build();
            final SimpleHttpRequest request = builder.setUri(uri).build();
            return getNextClient().execute(request).get();
        } catch (URISyntaxException | ExecutionException e) {
            throw new CdnjsApiException(e);
        } catch (InterruptedException e) {
            Thread.interrupted();
            throw new CdnjsApiException(e);
        }
    }

    public Libraries getlibraries() {
        String json = null;
        int responseCode = 0;
        try {
            SimpleHttpResponse response = callLibraries();
            responseCode = response.getCode();
            if (responseCode == 200) {
                json = response.getBodyText();
                Libraries list = objectMapper.readValue(json, Libraries.class);
                return list;
            }
        } catch (JsonProcessingException e) {
            LOG.debug("JSON Parsing error", e);
            LOG.debug("JSON:\\n\\n" + json);
            throw new CdnjsApiException("Unable to parse the cdnjs json", e);
        }
        throw new CdnjsApiException("Request failed; status code: " + responseCode);
    }

    public void asynchGetLibrary(String library) {
        try {
            String lib = URLEncoder.encode(library, StandardCharsets.UTF_8.name());
            URIBuilder uriBuilder = new URIBuilder(LIBRARIES_ENDPOINT + "/" + lib);
            final SimpleRequestBuilder builder = SimpleRequestBuilder.get();
            URI uri = uriBuilder.build();
            final SimpleHttpRequest request = builder.setUri(uri).build();
            libraryApiCalls.add(getNextClient().execute(request));
        } catch (URISyntaxException e) {
            throw new CdnjsApiException(e);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void asynchGetLibraryVersion(String library, String version) {
        try {
            String lib = URLEncoder.encode(library, StandardCharsets.UTF_8.name());
            String ver = URLEncoder.encode(version, StandardCharsets.UTF_8.name());
            URIBuilder uriBuilder = new URIBuilder(LIBRARIES_ENDPOINT + "/" + lib + "/" + ver);
            final SimpleRequestBuilder builder = SimpleRequestBuilder.get();
            URI uri = uriBuilder.build();
            final SimpleHttpRequest request = builder.setUri(uri).build();
            versionApiCalls.add(getNextClient().execute(request));
        } catch (URISyntaxException e) {
            throw new CdnjsApiException(e);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @SuppressFBWarnings(value = {"EI_EXPOSE_REP"})
    public LibraryAsynchResults getLibraryCalls() {
        return this.libraryApiCalls;
    }

    @SuppressFBWarnings(value = {"EI_EXPOSE_REP"})
    public LibraryAsynchResults getLibraryVersionCalls() {
        return this.versionApiCalls;
    }

    private RateLimitedClient getNextClient() {
        int index = clientIndex.getAndIncrement();
        if (index >= this.clients.size()) {
            clientIndex.set(1);
            index = 0;
        }
        return clients.get(index);
    }
}
