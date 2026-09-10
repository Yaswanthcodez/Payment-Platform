package com.yash.paymentplatform.payment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yash.paymentplatform.attempt.PaymentAttempt;
import com.yash.paymentplatform.attempt.PaymentAttemptStatus;
import com.yash.paymentplatform.merchant.Merchant;
import com.yash.paymentplatform.merchant.MerchantRepository;
import com.yash.paymentplatform.provider.Provider;
import com.yash.paymentplatform.provider.ProviderRepository;
import com.yash.paymentplatform.provider.ProviderStatus;

@SpringBootTest
class PaymentOrchestratorTest {

    @Autowired
    private PaymentOrchestrator paymentOrchestrator;

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ProviderRepository providerRepository;

    @Test
    void shouldCreatePaymentAttemptUsingActiveProvider() {

        Merchant merchant = new Merchant();
        merchant.setName("Test Merchant");
        merchant = merchantRepository.save(merchant);

        Payment payment = new Payment();
        payment.setMerchant(merchant);
        payment.setAmount(10000L);
        payment.setCurrency("INR");
        payment.setStatus(PaymentStatus.CREATED);
        payment = paymentRepository.save(payment);

        Provider provider = new Provider();
        provider.setName("Test Provider");
        provider.setStatus(ProviderStatus.ACTIVE);
        provider = providerRepository.save(provider);

        PaymentAttempt attempt =
                paymentOrchestrator.processPayment(payment.getId());

        assertNotNull(attempt);
        assertNotNull(attempt.getId());
        assertEquals(payment.getId(), attempt.getPayment().getId());
        assertEquals(ProviderStatus.ACTIVE, attempt.getProvider().getStatus());
       // assertEquals(provider.getId(), attempt.getProvider().getId());
        assertEquals(PaymentAttemptStatus.INITIATED, attempt.getStatus());
    }
}