package com.yash.paymentplatform.merchant.dto;

public class MerchantResponse {

    private Long id;
    private String name;

    public MerchantResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}