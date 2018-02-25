package de.kaysubs.tracker.pantsucat.exception;

public class TorrentUpdateException extends ApiException {

    public TorrentUpdateException(String[] errors) {
        super(errors);
    }

}
