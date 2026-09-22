package com.yash.paymentplatform.payment;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
Optional<Payment> findByMerchant_IdAndIdempotencyKey(Long merchantId, String idempotencyKey);
int countByMerchant_IdAndIdempotencyKey(Long merchantId,String idempotencyKey);

}