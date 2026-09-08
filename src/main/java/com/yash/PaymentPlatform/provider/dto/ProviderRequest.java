package com.yash.paymentplatform.provider.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.yash.paymentplatform.provider.ProviderStatus;


public class ProviderRequest {

    @NotBlank(message = "Provider name must not be blank")
    private String name;
    @NotNull(message = "Provider Status must not be null")
    private ProviderStatus status;

    public String getName() {
        return name;
    }
    public ProviderStatus getStatus(){
        return status;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(ProviderStatus status){
        this.status=status;
    }
}