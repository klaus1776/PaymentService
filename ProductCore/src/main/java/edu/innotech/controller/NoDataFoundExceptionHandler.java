package edu.innotech.controller;

import edu.innotech.dto.ErrorResponseDto;
import edu.innotech.exceptions.NoDataFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NoDataFoundExceptionHandler {

    @ExceptionHandler(NoDataFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDto handle(NoDataFoundException noDataFoundException) {
        return new ErrorResponseDto(noDataFoundException.getMessage(), noDataFoundException.getExternalSystemCode());
    }
}
