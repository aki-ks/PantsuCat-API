package de.kaysubs.tracker.pantsucat.model;

public class CheckTokenResponse extends FailableApiResponse {
    // ok == true
    private String[] infos;
    private AuthorizedUserProfile data;

    public boolean isValid() {
        return isOk();
    }

    public String[] getInfos() {
        return infos;
    }

    public AuthorizedUserProfile getUserData() {
        return data;
    }
}
