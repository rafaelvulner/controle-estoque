package com.estoque.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(VendaNotFoundException.class)
    protected ResponseEntity<?> handleVendaNotFoundException(VendaNotFoundException vendaNotFoundException, WebRequest request) {
        return handleExceptionInternal(vendaNotFoundException, vendaNotFoundException.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);

    }
}
