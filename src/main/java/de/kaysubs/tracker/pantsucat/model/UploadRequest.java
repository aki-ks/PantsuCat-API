package de.kaysubs.tracker.pantsucat.model;

import java.io.File;
import java.util.Optional;

/**
 * Since either a magnet link or seedfile, not both, must be defined,
 * this class can only be initialized through static methods
 */
public class UploadRequest {
    private Optional<String> apiKey = Optional.empty();
    private Optional<String> username = Optional.empty();
    private Optional<String> name = Optional.empty();
    private Optional<Category> category = Optional.empty();
    private Optional<Boolean> isRemake = Optional.empty();
    private Optional<String> description = Optional.empty();
    private Optional<TorrentStatus> status = Optional.empty();
    private Optional<Boolean> isAnonymous = Optional.empty();
    private Optional<String> website = Optional.empty();
    private Optional<Language[]> languages = Optional.empty();
    private Optional<String> magnet = Optional.empty();
    private Optional<File> seedFile = Optional.empty();

    /**
     * Upload a magnet-link
     */
    public static UploadRequest fromMagnet(String magnet) {
        UploadRequest request = new UploadRequest();
        request.magnet = Optional.of(magnet);
        return request;
    }

    /**
     * Upload a .torrent file
     */
    public static UploadRequest fromSeedfile(File file) {
        UploadRequest request = new UploadRequest();
        request.seedFile = Optional.of(file);
        return request;
    }

    private UploadRequest() {}

    public Optional<String> getApiKey() {
        return apiKey;
    }

    public UploadRequest setApiKey(String apiKey) {
        this.apiKey = Optional.ofNullable(apiKey);
        return this;
    }

    public Optional<String> getUsername() {
        return username;
    }

    public UploadRequest setUsername(String username) {
        this.username = Optional.ofNullable(username);
        return this;
    }

    public Optional<String> getName() {
        return name;
    }

    public UploadRequest setName(String name) {
        this.name = Optional.ofNullable(name);
        return this;
    }

    public Optional<Category> getCategory() {
        return category;
    }

    public UploadRequest setCategory(Category category) {
        this.category = Optional.ofNullable(category);
        return this;
    }

    public Optional<Boolean> isRemake() {
        return isRemake;
    }

    public UploadRequest setRemake(Boolean remake) {
        isRemake = Optional.ofNullable(remake);
        return this;
    }

    public Optional<String> getDescription() {
        return description;
    }

    public UploadRequest setDescription(String description) {
        this.description = Optional.ofNullable(description);
        return this;
    }

    public Optional<TorrentStatus> getStatus() {
        return status;
    }

    public UploadRequest setStatus(TorrentStatus status) {
        this.status = Optional.ofNullable(status);
        return this;
    }

    public Optional<Boolean> isAnonymous() {
        return isAnonymous;
    }

    public UploadRequest setAnonymous(Boolean anonymous) {
        this.isAnonymous = Optional.ofNullable(anonymous);
        return this;
    }

    public Optional<String> getWebsite() {
        return website;
    }

    public UploadRequest setWebsite(String website) {
        this.website = Optional.ofNullable(website);
        return this;
    }

    public Optional<Language[]> getLanguages() {
        return languages;
    }

    public UploadRequest setLanguages(Language... languages) {
        this.languages = Optional.ofNullable(languages);
        return this;
    }

    public Optional<String> getMagnetLink() {
        return magnet;
    }

    public Optional<File> getSeedFile() {
        return seedFile;
    }

}
