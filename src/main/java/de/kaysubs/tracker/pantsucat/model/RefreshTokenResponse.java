package de.kaysubs.tracker.pantsucat.model;

public class RefreshTokenResponse extends FailableApiResponse {
    // ok == true
    private String[] infos;
    private AuthorizedUserProfile data;

    public String[] getInfos() {
        return infos;
    }

    public AuthorizedUserProfile getUserData() {
        return data;
    }
}
