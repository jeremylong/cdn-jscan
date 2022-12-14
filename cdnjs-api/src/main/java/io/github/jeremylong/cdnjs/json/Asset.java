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
package io.github.jeremylong.cdnjs.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"version", "files", "rawFiles", "sri"})
@SuppressFBWarnings(value = {"EI_EXPOSE_REP"})
public class Asset {

    /**
     * (Required)
     */
    @JsonProperty("version")
    private String version;
    /**
     * (Required)
     */
    @JsonProperty("files")
    private List<String> files = new ArrayList<String>();
    /**
     * (Required)
     */
    @JsonProperty("rawFiles")
    private List<String> rawFiles = new ArrayList<String>();
    /**
     * (Required)
     *
     */
    @JsonProperty("sri")
    private Map<String, String> sri = new LinkedHashMap<String, String>();

    /**
     * (Required)
     *
     * @return version
     */
    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    /**
     * (Required)
     *
     * @return files
     */
    @JsonProperty("files")
    public List<String> getFiles() {
        return files;
    }

    /**
     * (Required)
     *
     * @return rawFiles
     */
    @JsonProperty("rawFiles")
    public List<String> getRawFiles() {
        return rawFiles;
    }

    /**
     * (Required)
     *
     * @return sri
     */
    @JsonProperty("sri")
    public Map<String, String> getSri() {
        return sri;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Asset.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this)))
                .append('[');
        sb.append("version");
        sb.append('=');
        sb.append(((this.version == null) ? "<null>" : this.version));
        sb.append(',');
        sb.append("files");
        sb.append('=');
        sb.append(((this.files == null) ? "<null>" : this.files));
        sb.append(',');
        sb.append("rawFiles");
        sb.append('=');
        sb.append(((this.rawFiles == null) ? "<null>" : this.rawFiles));
        sb.append(',');
        sb.append("sri");
        sb.append('=');
        sb.append(((this.sri == null) ? "<null>" : this.sri));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Asset asset = (Asset) o;
        return Objects.equals(version, asset.version) && Objects.equals(files, asset.files)
                && Objects.equals(rawFiles, asset.rawFiles) && Objects.equals(sri, asset.sri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(version, files, rawFiles, sri);
    }
}
