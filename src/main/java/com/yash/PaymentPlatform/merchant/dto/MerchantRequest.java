package com.yash.paymentplatform.merchant.dto;
import jakarta.validation.constraints.NotBlank;

public class MerchantRequest {

    @NotBlank(message = "Merchant name must not be blank")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}