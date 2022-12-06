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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"name", "version", "rawFiles", "files", "sri"})
public class Version {

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("name")
    private String name = "jquery";
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("version")
    private String version = "3.5.1";
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("rawFiles")
    private List<String> rawFiles = new ArrayList<String>();
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
    @JsonProperty("sri")
    private Map<String, String> sri = new LinkedHashMap<String, String>();

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("rawFiles")
    public List<String> getRawFiles() {
        return rawFiles;
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

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("sri")
    public Map<String, String> getSri() {
        return sri;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Version.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this)))
                .append('[');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null) ? "<null>" : this.name));
        sb.append(',');
        sb.append("version");
        sb.append('=');
        sb.append(((this.version == null) ? "<null>" : this.version));
        sb.append(',');
        sb.append("rawFiles");
        sb.append('=');
        sb.append(((this.rawFiles == null) ? "<null>" : this.rawFiles));
        sb.append(',');
        sb.append("files");
        sb.append('=');
        sb.append(((this.files == null) ? "<null>" : this.files));
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
    public int hashCode() {
        int result = 1;
        result = ((result * 31) + ((this.name == null) ? 0 : this.name.hashCode()));
        result = ((result * 31) + ((this.rawFiles == null) ? 0 : this.rawFiles.hashCode()));
        result = ((result * 31) + ((this.files == null) ? 0 : this.files.hashCode()));
        result = ((result * 31) + ((this.sri == null) ? 0 : this.sri.hashCode()));
        result = ((result * 31) + ((this.version == null) ? 0 : this.version.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Version) == false) {
            return false;
        }
        Version rhs = ((Version) other);
        return ((((((this.name == rhs.name) || ((this.name != null) && this.name.equals(rhs.name)))
                && ((this.rawFiles == rhs.rawFiles) || ((this.rawFiles != null) && this.rawFiles.equals(rhs.rawFiles))))
                && ((this.files == rhs.files) || ((this.files != null) && this.files.equals(rhs.files))))
                && ((this.sri == rhs.sri) || ((this.sri != null) && this.sri.equals(rhs.sri))))
                && ((this.version == rhs.version) || ((this.version != null) && this.version.equals(rhs.version))));
    }

}
