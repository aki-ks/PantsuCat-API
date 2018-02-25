package de.kaysubs.tracker.pantsucat.exception;

public class LoginException extends ApiException {

    public LoginException(String[] errors) {
        super(errors);
    }

}
