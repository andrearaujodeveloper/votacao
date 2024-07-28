package com.associacao.votacao.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(DomainBusinessException.class)
    public ResponseEntity tratarDomainBusinessException(DomainBusinessException e ) {
        return ResponseEntity.unprocessableEntity().body(e.getMessage());
    }
}
