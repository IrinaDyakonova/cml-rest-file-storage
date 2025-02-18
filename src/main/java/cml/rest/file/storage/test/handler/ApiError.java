package cml.rest.file.storage.test.handler;

import java.util.Arrays;
import java.util.List;

public class ApiError {
    private boolean success;
    private List<String> errors;

    public ApiError(boolean success, List<String> errors) {
        this.success = success;
        this.errors = errors;
    }

    public ApiError(boolean success, String error) {
        this.success = success;
        this.errors = Arrays.asList(error);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
