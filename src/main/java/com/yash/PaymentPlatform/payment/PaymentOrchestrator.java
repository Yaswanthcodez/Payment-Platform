package com.yash.paymentplatform.payment;

import org.springframework.stereotype.Service;

import com.yash.paymentplatform.attempt.PaymentAttempt;
import com.yash.paymentplatform.attempt.PaymentAttemptService;
import com.yash.paymentplatform.provider.Provider;
import com.yash.paymentplatform.provider.ProviderRouter;



@Service
public class PaymentOrchestrator {

    private final ProviderRouter providerRouter;
    private final PaymentAttemptService paymentAttemptService;

    public PaymentOrchestrator(
            ProviderRouter providerRouter,
            PaymentAttemptService paymentAttemptService) {
        this.providerRouter = providerRouter;
        this.paymentAttemptService = paymentAttemptService;
    }
    public PaymentAttempt processPayment(Long paymentId) {
        Provider provider = providerRouter.selectProvider();
        PaymentAttempt attempt = paymentAttemptService.createPaymentAttempt(paymentId, provider);
        return attempt;

}

}