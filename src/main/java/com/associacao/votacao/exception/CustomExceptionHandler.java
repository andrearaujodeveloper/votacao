package com.associacao.votacao.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(DomainBusinessException.class)
    public ResponseEntity tratarDomainBusinessException(DomainBusinessException e ) {
        return ResponseEntity.unprocessableEntity().body(e.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity tratarNotFoundException(NotFoundException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity tratarInternalServerError(Exception e) {
        return ResponseEntity.internalServerError().body(e.getMessage());
    }
}
