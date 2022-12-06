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

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"source", "target", "fileMap"})
public class Autoupdate {

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("source")
    private String source = "npm";
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("target")
    private String target = "jquery";
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("fileMap")
    private List<FileMap> fileMap = new ArrayList<FileMap>();

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("source")
    public String getSource() {
        return source;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("target")
    public String getTarget() {
        return target;
    }

    /**
     * 
     * (Required)
     * 
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
    public int hashCode() {
        int result = 1;
        result = ((result * 31) + ((this.fileMap == null) ? 0 : this.fileMap.hashCode()));
        result = ((result * 31) + ((this.source == null) ? 0 : this.source.hashCode()));
        result = ((result * 31) + ((this.target == null) ? 0 : this.target.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Autoupdate) == false) {
            return false;
        }
        Autoupdate rhs = ((Autoupdate) other);
        return ((((this.fileMap == rhs.fileMap) || ((this.fileMap != null) && this.fileMap.equals(rhs.fileMap)))
                && ((this.source == rhs.source) || ((this.source != null) && this.source.equals(rhs.source))))
                && ((this.target == rhs.target) || ((this.target != null) && this.target.equals(rhs.target))));
    }

}
