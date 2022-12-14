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

/**
 * JSON Schema for https://api.cdnjs.com/libraries?fields=filename,version
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"results", "total", "available"})
@SuppressFBWarnings(value = {"EI_EXPOSE_REP"})
public class Libraries {

    /**
     * (Required)
     */
    @JsonProperty("results")
    private List<Result> results = new ArrayList<Result>();
    /**
     * (Required)
     */
    @JsonProperty("total")
    private Integer total;
    /**
     * (Required)
     */
    @JsonProperty("available")
    private Integer available;

    /**
     * (Required)
     *
     * @return results
     */
    @JsonProperty("results")
    public List<Result> getResults() {
        return results;
    }

    /**
     * (Required)
     *
     * @return total
     */
    @JsonProperty("total")
    public Integer getTotal() {
        return total;
    }

    /**
     * (Required)
     *
     * @return available
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
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Libraries libraries = (Libraries) o;
        return Objects.equals(results, libraries.results) && Objects.equals(total, libraries.total)
                && Objects.equals(available, libraries.available);
    }

    @Override
    public int hashCode() {
        return Objects.hash(results, total, available);
    }
}
