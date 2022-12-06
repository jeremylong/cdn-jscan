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

/**
 * JSON Schema for https://api.cdnjs.com/libraries?fields=filename,version
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"results", "total", "available"})
public class Libraries {

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("results")
    private List<Result> results = new ArrayList<Result>();
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("total")
    private Integer total = 4343;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("available")
    private Integer available = 4343;

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("results")
    public List<Result> getResults() {
        return results;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("total")
    public Integer getTotal() {
        return total;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("available")
    public Integer getAvailable() {
        return available;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Libraries.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this)))
                .append('[');
        sb.append("results");
        sb.append('=');
        sb.append(((this.results == null) ? "<null>" : this.results));
        sb.append(',');
        sb.append("total");
        sb.append('=');
        sb.append(((this.total == null) ? "<null>" : this.total));
        sb.append(',');
        sb.append("available");
        sb.append('=');
        sb.append(((this.available == null) ? "<null>" : this.available));
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
        result = ((result * 31) + ((this.available == null) ? 0 : this.available.hashCode()));
        result = ((result * 31) + ((this.total == null) ? 0 : this.total.hashCode()));
        result = ((result * 31) + ((this.results == null) ? 0 : this.results.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Libraries) == false) {
            return false;
        }
        Libraries rhs = ((Libraries) other);
        return ((((this.available == rhs.available)
                || ((this.available != null) && this.available.equals(rhs.available)))
                && ((this.total == rhs.total) || ((this.total != null) && this.total.equals(rhs.total))))
                && ((this.results == rhs.results) || ((this.results != null) && this.results.equals(rhs.results))));
    }

}
