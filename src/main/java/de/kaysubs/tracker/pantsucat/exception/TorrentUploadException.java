package de.kaysubs.tracker.pantsucat.exception;

public class TorrentUploadException extends ApiException {

    public TorrentUploadException(String[] errors) {
        super(errors);
    }

}
