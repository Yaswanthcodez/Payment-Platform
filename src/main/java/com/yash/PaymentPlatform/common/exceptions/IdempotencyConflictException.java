package com.yash.paymentplatform.common.exceptions;

public class IdempotencyConflictException extends  RuntimeException{
    public IdempotencyConflictException(){
        super("This idempotency key is already associated with a payment, but the new request's data conflicts with it.");
    }
}