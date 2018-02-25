package de.kaysubs.tracker.pantsucat.model;

import com.google.gson.JsonObject;

public class FailableApiResponse {
    private boolean ok;

    // ok == false
    private String[] errors;
    private JsonObject all_errors;

    public boolean isOk() {
        return ok;
    }

    public String[] getErrors() {
        return errors;
    }

    public JsonObject getAllErrors() {
        return all_errors;
    }
}
