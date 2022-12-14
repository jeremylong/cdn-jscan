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
 * JSON Schema for https://api.cdnjs.com/libraries/library
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"name", "latest", "sri", "authors", "autoupdate", "description", "filename", "homepage", "keywords",
        "license", "repository", "version", "author", "assets", "versions"})
@SuppressFBWarnings(value = {"EI_EXPOSE_REP"})
public class Library {

    /**
     * (Required)
     */
    @JsonProperty("name")
    private String name;
    /**
     * (Required)
     */
    @JsonProperty("latest")
    private String latest;
    /**
     * (Required)
     */
    @JsonProperty("sri")
    private String sri;
    /**
     * (Required)
     */
    @JsonProperty("authors")
    private List<Author> authors = new ArrayList<Author>();
    /**
     * (Required)
     */
    @JsonProperty("autoupdate")
    private Autoupdate autoupdate;
    /**
     * (Required)
     */
    @JsonProperty("description")
    private String description;
    /**
     * (Required)
     */
    @JsonProperty("filename")
    private String filename;
    /**
     * (Required)
     */
    @JsonProperty("homepage")
    private String homepage;
    /**
     * (Required)
     */
    @JsonProperty("keywords")
    private List<String> keywords = new ArrayList<String>();
    /**
     * (Required)
     */
    @JsonProperty("license")
    private String license;
    /**
     * (Required)
     */
    @JsonProperty("repository")
    private Repository repository;
    /**
     * (Required)
     */
    @JsonProperty("version")
    private String version;
    /**
     * (Required)
     */
    @JsonProperty("author")
    private String author;
    /**
     * (Required)
     */
    @JsonProperty("assets")
    private List<Asset> assets = new ArrayList<Asset>();
    /**
     * (Required)
     */
    @JsonProperty("versions")
    private List<String> versions = new ArrayList<String>();

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
     * @return sri
     */
    @JsonProperty("sri")
    public String getSri() {
        return sri;
    }

    /**
     * (Required)
     *
     * @return author
     */
    @JsonProperty("authors")
    public List<Author> getAuthors() {
        return authors;
    }

    /**
     * (Required)
     *
     * @return autoupdate
     */
    @JsonProperty("autoupdate")
    public Autoupdate getAutoupdate() {
        return autoupdate;
    }

    /**
     * (Required)
     *
     * @return description
     */
    @JsonProperty("description")
    public String getDescription() {
        return description;
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
     * @return homepage
     */
    @JsonProperty("homepage")
    public String getHomepage() {
        return homepage;
    }

    /**
     * (Required)
     *
     * @return keywords
     */
    @JsonProperty("keywords")
    public List<String> getKeywords() {
        return keywords;
    }

    /**
     * (Required)
     *
     * @return license
     */
    @JsonProperty("license")
    public String getLicense() {
        return license;
    }

    /**
     * (Required)
     *
     * @return repository
     */
    @JsonProperty("repository")
    public Repository getRepository() {
        return repository;
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

    /**
     * (Required)
     *
     * @return author
     */
    @JsonProperty("author")
    public String getAuthor() {
        return author;
    }

    /**
     * (Required)
     *
     * @return assets
     */
    @JsonProperty("assets")
    public List<Asset> getAssets() {
        return assets;
    }

    /**
     * (Required)
     *
     * @return versions
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
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Library library = (Library) o;
        return Objects.equals(name, library.name) && Objects.equals(latest, library.latest)
                && Objects.equals(sri, library.sri) && Objects.equals(authors, library.authors)
                && Objects.equals(autoupdate, library.autoupdate) && Objects.equals(description, library.description)
                && Objects.equals(filename, library.filename) && Objects.equals(homepage, library.homepage)
                && Objects.equals(keywords, library.keywords) && Objects.equals(license, library.license)
                && Objects.equals(repository, library.repository) && Objects.equals(version, library.version)
                && Objects.equals(author, library.author) && Objects.equals(assets, library.assets)
                && Objects.equals(versions, library.versions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, latest, sri, authors, autoupdate, description, filename, homepage, keywords, license,
                repository, version, author, assets, versions);
    }
}
