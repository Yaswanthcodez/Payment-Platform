package com.yash.paymentplatform.provider;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
Provider findFirstByStatusOrderByIdAsc(ProviderStatus status);
}
