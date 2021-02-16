package com.springprep.examples;


import com.springprep.examples.exception.DataNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {DataNotFoundException.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {


        //language=JSON
        String bodyOfResponse = "{\n" +
                "  \"message\": \"Record Not Found in Database\"\n" +
                "}";
        if (ex instanceof DataNotFoundException) {
            return handleExceptionInternal(ex, "Record Not Found in Database",
                    new HttpHeaders(), HttpStatus.NOT_FOUND,
                    request);

        }

        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(),
                HttpStatus.NOT_FOUND, request);
    }


}
