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
package io.github.jeremylong.cdnjs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.async.methods.SimpleHttpResponse;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public abstract class AbstractApiCalls<T> implements Iterator<T> {

    /**
     * Jackson object mapper.
     */
    private final ObjectMapper objectMapper = new ObjectMapper();
    /**
     * The list of future responses for library calls.
     */
    private List<Future<SimpleHttpResponse>> futures = new ArrayList<>();
    private int lastResponseCode = 200;
    private Class<T> genericType;

    public AbstractApiCalls(Class<T> genericType){
        this.genericType = genericType;
    }

    void add(Future<SimpleHttpResponse> future) {
        futures.add(future);
    }

    private SimpleHttpResponse getCompletedFuture() throws InterruptedException, ExecutionException {
        boolean notFound = futures.size() > 0;
        Future<SimpleHttpResponse> result = null;
        while (notFound) {
            for (Future<SimpleHttpResponse> future : futures) {
                if (future.isDone()) {
                    result = future;
                    notFound = false;
                    break;
                }
            }
            Thread.sleep(500);
        }
        if (result != null) {
            futures.remove(result);
            return result.get();
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        return !futures.isEmpty();
    }

    @Override
    public T next() {
        String json = "";
        T result = null;
        try {
            SimpleHttpResponse response = getCompletedFuture();
            if (response.getCode() == 200) {
                json = response.getBodyText();
                result = objectMapper.readValue(json, this.genericType);
            }
            lastResponseCode = response.getCode();
        } catch (ExecutionException e) {
            throw new CdnjsApiException(e);
        } catch (InterruptedException e) {
            Thread.interrupted();
            throw new CdnjsApiException(e);
        } catch (JsonMappingException e) {
            throw new CdnjsApiException(e);
        } catch (JsonProcessingException e) {
            throw new CdnjsApiException(e);
        }
        return result;
    }
}
