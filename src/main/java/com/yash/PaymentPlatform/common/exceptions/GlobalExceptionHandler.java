package com.yash.paymentplatform.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MerchantNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleMerchantNotFound(MerchantNotFoundException exception) {
        return exception.getMessage();
    }
}