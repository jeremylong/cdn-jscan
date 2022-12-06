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
@JsonPropertyOrder({"basePath", "files"})
public class FileMap {

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("basePath")
    private String basePath = "dist";
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("files")
    private List<String> files = new ArrayList<String>();

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("basePath")
    public String getBasePath() {
        return basePath;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("files")
    public List<String> getFiles() {
        return files;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(FileMap.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this)))
                .append('[');
        sb.append("basePath");
        sb.append('=');
        sb.append(((this.basePath == null) ? "<null>" : this.basePath));
        sb.append(',');
        sb.append("files");
        sb.append('=');
        sb.append(((this.files == null) ? "<null>" : this.files));
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
        result = ((result * 31) + ((this.basePath == null) ? 0 : this.basePath.hashCode()));
        result = ((result * 31) + ((this.files == null) ? 0 : this.files.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof FileMap) == false) {
            return false;
        }
        FileMap rhs = ((FileMap) other);
        return (((this.basePath == rhs.basePath) || ((this.basePath != null) && this.basePath.equals(rhs.basePath)))
                && ((this.files == rhs.files) || ((this.files != null) && this.files.equals(rhs.files))));
    }

}
