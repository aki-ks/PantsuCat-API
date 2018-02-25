package de.kaysubs.tracker.pantsucat.exception;

import java.util.Arrays;

public abstract class ApiException extends RuntimeException {

    private final String[] errors;

    public ApiException(String[] errors) {
        super(Arrays.toString(errors));
        this.errors = getErrors();
    }

    public String[] getErrors() {
        return errors;
    }
}
