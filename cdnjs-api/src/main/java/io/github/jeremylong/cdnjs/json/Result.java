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

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"name", "latest", "filename", "version"})
@SuppressFBWarnings(value = {"EI_EXPOSE_REP"})
public class Result {

    /**
     * (Required)
     */
    @JsonProperty("name")
    private String name = "";
    /**
     * (Required)
     */
    @JsonProperty("latest")
    private String latest = "";
    /**
     * (Required)
     */
    @JsonProperty("filename")
    private String filename = "";
    /**
     * (Required)
     */
    @JsonProperty("version")
    private String version = "";

    /**
     * (Required)
     *
     * @return name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * (Required)
     *
     * @return latest
     */
    @JsonProperty("latest")
    public String getLatest() {
        return latest;
    }

    /**
     * (Required)
     *
     * @return filename
     */
    @JsonProperty("filename")
    public String getFilename() {
        return filename;
    }

    /**
     * (Required)
     *
     * @return version
     */
    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Result.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this)))
                .append('[');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null) ? "<null>" : this.name));
        sb.append(',');
        sb.append("latest");
        sb.append('=');
        sb.append(((this.latest == null) ? "<null>" : this.latest));
        sb.append(',');
        sb.append("filename");
        sb.append('=');
        sb.append(((this.filename == null) ? "<null>" : this.filename));
        sb.append(',');
        sb.append("version");
        sb.append('=');
        sb.append(((this.version == null) ? "<null>" : this.version));
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
        Result result = (Result) o;
        return Objects.equals(name, result.name) && Objects.equals(latest, result.latest)
                && Objects.equals(filename, result.filename) && Objects.equals(version, result.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, latest, filename, version);
    }
}
