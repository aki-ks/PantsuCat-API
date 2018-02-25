package de.kaysubs.tracker.pantsucat.model;

import java.util.Optional;
import java.util.OptionalInt;

public class UpdateRequest {
    private Optional<String> apiKey = Optional.empty();
    private Optional<String> username = Optional.empty();
    private OptionalInt torrentId = OptionalInt.empty();
    private Optional<String> name = Optional.empty();
    private Optional<Category> category = Optional.empty();
    private Optional<Boolean> isRemake = Optional.empty();
    private Optional<String> description = Optional.empty();
    private Optional<TorrentStatus> status = Optional.empty();
    private Optional<Boolean> isAnonymous = Optional.empty();
    private Optional<String> website = Optional.empty();
    private Optional<Language[]> languages = Optional.empty();

    public Optional<String> getApiKey() {
        return apiKey;
    }

    public UpdateRequest setApiKey(String apiKey) {
        this.apiKey = Optional.ofNullable(apiKey);
        return this;
    }

    public Optional<String> getUsername() {
        return username;
    }

    public UpdateRequest setUsername(String username) {
        this.username = Optional.ofNullable(username);
        return this;
    }

    public OptionalInt getTorrentId() {
        return torrentId;
    }

    public UpdateRequest setTorrentId(Integer torrentId) {
        this.torrentId = torrentId == null ? OptionalInt.empty() : OptionalInt.of(torrentId);
        return this;
    }

    public Optional<String> getName() {
        return name;
    }

    public UpdateRequest setName(String name) {
        this.name = Optional.ofNullable(name);
        return this;
    }

    public Optional<Category> getCategory() {
        return category;
    }

    public UpdateRequest setCategory(Category category) {
        this.category = Optional.ofNullable(category);
        return this;
    }

    public Optional<Boolean> isRemake() {
        return isRemake;
    }

    public UpdateRequest setRemake(Boolean remake) {
        isRemake = Optional.ofNullable(remake);
        return this;
    }

    public Optional<String> getDescription() {
        return description;
    }

    public UpdateRequest setDescription(String description) {
        this.description = Optional.ofNullable(description);
        return this;
    }

    public Optional<TorrentStatus> getStatus() {
        return status;
    }

    public UpdateRequest setStatus(TorrentStatus status) {
        this.status = Optional.ofNullable(status);
        return this;
    }

    public Optional<Boolean> isAnonymous() {
        return isAnonymous;
    }

    public UpdateRequest setAnonymous(Boolean anonymous) {
        this.isAnonymous = Optional.ofNullable(anonymous);
        return this;
    }

    public Optional<String> getWebsite() {
        return website;
    }

    public UpdateRequest setWebsite(String website) {
        this.website = Optional.ofNullable(website);
        return this;
    }

    public Optional<Language[]> getLanguages() {
        return languages;
    }

    public UpdateRequest setLanguages(Language... languages) {
        this.languages = Optional.ofNullable(languages);
        return this;
    }
}
