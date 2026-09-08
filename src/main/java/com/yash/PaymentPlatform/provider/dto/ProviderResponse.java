package com.yash.paymentplatform.provider.dto;
import com.yash.paymentplatform.provider.ProviderStatus;


public class ProviderResponse {

    private Long id;
    private String name;
    private ProviderStatus status;

    public ProviderResponse(Long id, String name,ProviderStatus status) {
        this.id = id;
        this.name = name;
        this.status=status;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ProviderStatus getStatus(){
        return status;
    }
}