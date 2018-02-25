package de.kaysubs.tracker.pantsucat.model;

public class GetUserProfilResponse extends FailableApiResponse {
    // ok == true
    private String[] infos;
    private UserProfile data;

    public String[] getInfos() {
        return infos;
    }

    public UserProfile getUserData() {
        return data;
    }

}
