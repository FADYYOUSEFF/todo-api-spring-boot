package com.example.todo_api.Exception;

import org.springframework.http.HttpStatus;

public class ExceptionResponse {
    private HttpStatus error;
    private String message;

    public ExceptionResponse(HttpStatus error, String message) {
        this.error = error;
        this.message = message;
    }

    public HttpStatus getError() {
        return error;
    }

    public void setError(HttpStatus error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
