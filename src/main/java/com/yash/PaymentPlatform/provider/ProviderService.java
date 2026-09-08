package com.yash.paymentplatform.provider;
import org.springframework.stereotype.Service;
import com.yash.paymentplatform.provider.dto.ProviderRequest;

@Service
public class ProviderService {

    private final ProviderRepository providerRepository;

    public ProviderService(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    public Provider CreateProvider(ProviderRequest request) {
        Provider provider=new Provider();
        provider.setName(request.getName());
        provider.setStatus(request.getStatus());

        return providerRepository.save(provider);
    }

    public Provider getProvider(Long id) {
        return providerRepository.findById(id)
            .orElseThrow(() ->
                    new RuntimeException("Provider not found with id: " + id));
    
    }
}