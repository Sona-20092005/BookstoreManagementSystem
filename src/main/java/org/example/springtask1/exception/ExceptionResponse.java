package org.example.springtask1.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ExceptionResponse {

    private String message;
    private HttpStatus status;

    @JsonProperty("status")
    public Integer getStatusCode() {
        return status.value();
    }
    public ExceptionResponse(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
    public ExceptionResponse() {
    }

    public static class ExceptionResponseBuilder {
        private String message;
        private HttpStatus status;

        public ExceptionResponseBuilder withMessage(String message) {
            this.message = message;
            return this;
        }

        public ExceptionResponseBuilder withStatus(HttpStatus status) {
            this.status = status;
            return this;
        }

        public ExceptionResponse build() {
            ExceptionResponse response = new ExceptionResponse(this.message, this.status);
            return response;
        }
    }

    public static ExceptionResponseBuilder builder() {
        return new ExceptionResponseBuilder();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
