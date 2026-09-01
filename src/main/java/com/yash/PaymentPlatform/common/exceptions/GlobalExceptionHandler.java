package com.yash.paymentplatform.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.yash.paymentplatform.common.error.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

 

    @ExceptionHandler(MerchantNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleMerchantNotFound(
        MerchantNotFoundException exception) {

    return new ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            exception.getMessage()
    );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationException(
        MethodArgumentNotValidException exception) {

    String message = exception.getBindingResult()
            .getFieldErrors()
            .getFirst()
            .getDefaultMessage();

    return new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            message
    );
}
}
