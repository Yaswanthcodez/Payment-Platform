package com.yash.paymentplatform.provider;

import org.springframework.stereotype.Component;

@Component
public class ProviderRouter {

    private final ProviderRepository providerRepository;

    public ProviderRouter(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    public Provider selectProvider() {
        Provider provider = providerRepository.findFirstByStatusOrderByIdAsc(ProviderStatus.ACTIVE);
        if (provider == null) {
            throw new RuntimeException("No Active Providers Found ");
        }
        return provider;
    }
}