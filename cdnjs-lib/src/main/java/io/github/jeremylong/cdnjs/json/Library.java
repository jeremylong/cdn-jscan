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
 * JSON Schema for https://api.cdnjs.com/libraries/library
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"name", "latest", "sri", "authors", "autoupdate", "description", "filename", "homepage", "keywords",
        "license", "repository", "version", "author", "assets", "versions"})
public class Library {

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
    @JsonProperty("latest")
    private String latest = "https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js";
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("sri")
    private String sri = "sha512-aVKKRRi/Q/YV+4mjoKBsE4x3H+BkegoM/em46NNlCqNTmUYADjBbeNefNxYV7giUp0VxICtqdrbqU7iVaeZNXA==";
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("authors")
    private List<Author> authors = new ArrayList<Author>();
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("autoupdate")
    private Autoupdate autoupdate;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("description")
    private String description = "JavaScript library for DOM operations";
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("filename")
    private String filename = "jquery.min.js";
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("homepage")
    private String homepage = "http://jquery.com/";
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("keywords")
    private List<String> keywords = new ArrayList<String>();
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("license")
    private String license = "MIT";
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("repository")
    private Repository repository;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("version")
    private String version = "3.6.1";
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("author")
    private String author = "jQuery Foundation and other contributors (https://github.com/jquery/jquery/blob/master/AUTHORS.txt)";
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("assets")
    private List<Asset> assets = new ArrayList<Asset>();
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("versions")
    private List<String> versions = new ArrayList<String>();

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
    @JsonProperty("latest")
    public String getLatest() {
        return latest;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("sri")
    public String getSri() {
        return sri;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("authors")
    public List<Author> getAuthors() {
        return authors;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("autoupdate")
    public Autoupdate getAutoupdate() {
        return autoupdate;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("filename")
    public String getFilename() {
        return filename;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("homepage")
    public String getHomepage() {
        return homepage;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("keywords")
    public List<String> getKeywords() {
        return keywords;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("license")
    public String getLicense() {
        return license;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("repository")
    public Repository getRepository() {
        return repository;
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
    @JsonProperty("author")
    public String getAuthor() {
        return author;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("assets")
    public List<Asset> getAssets() {
        return assets;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("versions")
    public List<String> getVersions() {
        return versions;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Library.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this)))
                .append('[');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null) ? "<null>" : this.name));
        sb.append(',');
        sb.append("latest");
        sb.append('=');
        sb.append(((this.latest == null) ? "<null>" : this.latest));
        sb.append(',');
        sb.append("sri");
        sb.append('=');
        sb.append(((this.sri == null) ? "<null>" : this.sri));
        sb.append(',');
        sb.append("authors");
        sb.append('=');
        sb.append(((this.authors == null) ? "<null>" : this.authors));
        sb.append(',');
        sb.append("autoupdate");
        sb.append('=');
        sb.append(((this.autoupdate == null) ? "<null>" : this.autoupdate));
        sb.append(',');
        sb.append("description");
        sb.append('=');
        sb.append(((this.description == null) ? "<null>" : this.description));
        sb.append(',');
        sb.append("filename");
        sb.append('=');
        sb.append(((this.filename == null) ? "<null>" : this.filename));
        sb.append(',');
        sb.append("homepage");
        sb.append('=');
        sb.append(((this.homepage == null) ? "<null>" : this.homepage));
        sb.append(',');
        sb.append("keywords");
        sb.append('=');
        sb.append(((this.keywords == null) ? "<null>" : this.keywords));
        sb.append(',');
        sb.append("license");
        sb.append('=');
        sb.append(((this.license == null) ? "<null>" : this.license));
        sb.append(',');
        sb.append("repository");
        sb.append('=');
        sb.append(((this.repository == null) ? "<null>" : this.repository));
        sb.append(',');
        sb.append("version");
        sb.append('=');
        sb.append(((this.version == null) ? "<null>" : this.version));
        sb.append(',');
        sb.append("author");
        sb.append('=');
        sb.append(((this.author == null) ? "<null>" : this.author));
        sb.append(',');
        sb.append("assets");
        sb.append('=');
        sb.append(((this.assets == null) ? "<null>" : this.assets));
        sb.append(',');
        sb.append("versions");
        sb.append('=');
        sb.append(((this.versions == null) ? "<null>" : this.versions));
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
        result = ((result * 31) + ((this.keywords == null) ? 0 : this.keywords.hashCode()));
        result = ((result * 31) + ((this.author == null) ? 0 : this.author.hashCode()));
        result = ((result * 31) + ((this.description == null) ? 0 : this.description.hashCode()));
        result = ((result * 31) + ((this.sri == null) ? 0 : this.sri.hashCode()));
        result = ((result * 31) + ((this.repository == null) ? 0 : this.repository.hashCode()));
        result = ((result * 31) + ((this.version == null) ? 0 : this.version.hashCode()));
        result = ((result * 31) + ((this.autoupdate == null) ? 0 : this.autoupdate.hashCode()));
        result = ((result * 31) + ((this.license == null) ? 0 : this.license.hashCode()));
        result = ((result * 31) + ((this.filename == null) ? 0 : this.filename.hashCode()));
        result = ((result * 31) + ((this.assets == null) ? 0 : this.assets.hashCode()));
        result = ((result * 31) + ((this.versions == null) ? 0 : this.versions.hashCode()));
        result = ((result * 31) + ((this.name == null) ? 0 : this.name.hashCode()));
        result = ((result * 31) + ((this.latest == null) ? 0 : this.latest.hashCode()));
        result = ((result * 31) + ((this.authors == null) ? 0 : this.authors.hashCode()));
        result = ((result * 31) + ((this.homepage == null) ? 0 : this.homepage.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Library) == false) {
            return false;
        }
        Library rhs = ((Library) other);
        return ((((((((((((((((this.keywords == rhs.keywords)
                || ((this.keywords != null) && this.keywords.equals(rhs.keywords)))
                && ((this.author == rhs.author) || ((this.author != null) && this.author.equals(rhs.author))))
                && ((this.description == rhs.description)
                        || ((this.description != null) && this.description.equals(rhs.description))))
                && ((this.sri == rhs.sri) || ((this.sri != null) && this.sri.equals(rhs.sri))))
                && ((this.repository == rhs.repository)
                        || ((this.repository != null) && this.repository.equals(rhs.repository))))
                && ((this.version == rhs.version) || ((this.version != null) && this.version.equals(rhs.version))))
                && ((this.autoupdate == rhs.autoupdate)
                        || ((this.autoupdate != null) && this.autoupdate.equals(rhs.autoupdate))))
                && ((this.license == rhs.license) || ((this.license != null) && this.license.equals(rhs.license))))
                && ((this.filename == rhs.filename) || ((this.filename != null) && this.filename.equals(rhs.filename))))
                && ((this.assets == rhs.assets) || ((this.assets != null) && this.assets.equals(rhs.assets))))
                && ((this.versions == rhs.versions) || ((this.versions != null) && this.versions.equals(rhs.versions))))
                && ((this.name == rhs.name) || ((this.name != null) && this.name.equals(rhs.name))))
                && ((this.latest == rhs.latest) || ((this.latest != null) && this.latest.equals(rhs.latest))))
                && ((this.authors == rhs.authors) || ((this.authors != null) && this.authors.equals(rhs.authors))))
                && ((this.homepage == rhs.homepage)
                        || ((this.homepage != null) && this.homepage.equals(rhs.homepage))));
    }

}
