package com.example.todo_api.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<?> handlerRecordNotFoundException(RecordNotFoundException ex){
        return exceptionResponseHelper(ex,HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(RecordDublicatedException.class)
    public ResponseEntity<?> handlerRecordDublicatedException(RecordDublicatedException ex){
        return exceptionResponseHelper(ex,HttpStatus.CONFLICT);

    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handlerUerNotFoundException(UserNotFoundException ex){
        return exceptionResponseHelper(ex,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handlerBadRequestException(BadRequestException ex){
        return exceptionResponseHelper(ex,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        return  exceptionResponseHelper(ex,HttpStatus.BAD_REQUEST);
    }
    private ResponseEntity<?> exceptionResponseHelper(Exception ex,HttpStatus error){
        ExceptionResponse exceptionResponse = new ExceptionResponse(error,ex.getMessage());
        return ResponseEntity.status(error).body(exceptionResponse);
    }
}
