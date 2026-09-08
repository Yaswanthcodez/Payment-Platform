package com.yash.paymentplatform.provider;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yash.paymentplatform.provider.dto.ProviderRequest;
import com.yash.paymentplatform.provider.dto.ProviderResponse;

import jakarta.validation.Valid;



@RestController
public class ProviderController {
    private final ProviderService ProviderService;

    public ProviderController(ProviderService providerService) {
           this.ProviderService = providerService;
    }
    
    @PostMapping("/api/v1/providers")
    public ProviderResponse CreateProvider(@Valid@RequestBody ProviderRequest request) {
        Provider provider=ProviderService.CreateProvider(request);

        return new ProviderResponse(provider.getId(), provider.getName(), provider.getStatus());
    }

    @GetMapping("/api/v1/providers/{id}")
    public ProviderResponse getProvider(@PathVariable Long id) {
           Provider provider = this.ProviderService.getProvider(id);

        return new ProviderResponse(
            provider.getId(),
            provider.getName(),
            provider.getStatus()
        );
}

    
}