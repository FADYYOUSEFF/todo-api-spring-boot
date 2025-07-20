package com.example.todo_api.Exception;

public class RecordDublicatedException extends RuntimeException {
    public RecordDublicatedException(String message) {
        super(message);
    }

    public RecordDublicatedException() {
    }
}
