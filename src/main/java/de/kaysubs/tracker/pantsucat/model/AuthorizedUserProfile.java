package de.kaysubs.tracker.pantsucat.model;

public class AuthorizedUserProfile extends UserProfile {
    private String token;

    public String getApiToken() {
        return token;
    }
}
