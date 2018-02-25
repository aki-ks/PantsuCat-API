package de.kaysubs.tracker.pantsucat.model;

public class UploadResponse extends FailableApiResponse {
    // ok == true
    private String[] infos;
    private TorrentInfo data;

    public String[] getInfos() {
        return infos;
    }

    public TorrentInfo getData() {
        return data;
    }
}
