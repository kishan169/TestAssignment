package com.project.orderservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorDetails> CustomerException(CustomerNotFoundException ee, WebRequest wr){
        ErrorDetails error =new ErrorDetails(LocalDateTime.now() , ee.getMessage(),wr.getDescription(false));
        return new ResponseEntity<ErrorDetails>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> ValidationExceptionhandler(MethodArgumentNotValidException ee, WebRequest req){
        ErrorDetails err= new ErrorDetails(LocalDateTime.now(), "ValidationError", ee.getBindingResult().getFieldError().getDefaultMessage());
        return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> otherException(Exception ee, WebRequest wr){
        ErrorDetails error =new ErrorDetails(LocalDateTime.now() , ee.getMessage(),wr.getDescription(false));
        return new ResponseEntity<ErrorDetails>(error, HttpStatus.NOT_FOUND);
    }
}
