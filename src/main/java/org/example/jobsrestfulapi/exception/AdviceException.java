package org.example.joblisting.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceException {
    @ExceptionHandler(ResourcesNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFound(ResourcesNotFound e){
        return e.getMessage();
    }
}
