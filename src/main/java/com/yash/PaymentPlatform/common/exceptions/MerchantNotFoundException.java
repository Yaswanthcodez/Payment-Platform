package com.yash.paymentplatform.common.exceptions;

public class MerchantNotFoundException extends RuntimeException {

    public MerchantNotFoundException(Long id) {
        super("Merchant not found with id: " + id);
    }
}