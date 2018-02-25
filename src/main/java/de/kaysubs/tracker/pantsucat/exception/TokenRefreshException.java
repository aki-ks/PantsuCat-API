package de.kaysubs.tracker.pantsucat.exception;

public class TokenRefreshException extends ApiException {

    public TokenRefreshException(String[] errors) {
        super(errors);
    }

}
