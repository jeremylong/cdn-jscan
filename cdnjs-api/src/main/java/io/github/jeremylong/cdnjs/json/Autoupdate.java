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
import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"source", "target", "fileMap"})
@SuppressFBWarnings(value = {"EI_EXPOSE_REP"})
public class Autoupdate {

    /**
     * (Required)
     */
    @JsonProperty("source")
    private String source;
    /**
     * (Required)
     */
    @JsonProperty("target")
    private String target;
    /**
     * (Required)
     */
    @JsonProperty("fileMap")
    private List<FileMap> fileMap = new ArrayList<FileMap>();

    /**
     * (Required)
     *
     * @return source
     */
    @JsonProperty("source")
    public String getSource() {
        return source;
    }

    /**
     * (Required)
     *
     * @return target
     */
    @JsonProperty("target")
    public String getTarget() {
        return target;
    }

    /**
     * (Required)
     *
     * @return fileMap
     */
    @JsonProperty("fileMap")
    public List<FileMap> getFileMap() {
        return fileMap;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Autoupdate.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this)))
                .append('[');
        sb.append("source");
        sb.append('=');
        sb.append(((this.source == null) ? "<null>" : this.source));
        sb.append(',');
        sb.append("target");
        sb.append('=');
        sb.append(((this.target == null) ? "<null>" : this.target));
        sb.append(',');
        sb.append("fileMap");
        sb.append('=');
        sb.append(((this.fileMap == null) ? "<null>" : this.fileMap));
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
        Autoupdate that = (Autoupdate) o;
        return Objects.equals(source, that.source) && Objects.equals(target, that.target)
                && Objects.equals(fileMap, that.fileMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, target, fileMap);
    }
}
