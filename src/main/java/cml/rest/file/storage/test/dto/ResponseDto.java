package cml.rest.file.storage.test.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ResponseDto {

    boolean success;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String error;

    public ResponseDto() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
