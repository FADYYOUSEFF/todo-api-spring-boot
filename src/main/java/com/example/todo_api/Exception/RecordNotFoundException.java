package com.example.todo_api.Exception;

public class RecordNotFoundException extends RuntimeException{

    public RecordNotFoundException() {
    }

    public RecordNotFoundException(String message) {
        super(message);
    }
}
