package com.yash.paymentplatform.payment;

import org.springframework.stereotype.Service;

import com.yash.paymentplatform.attempt.PaymentAttempt;
import com.yash.paymentplatform.attempt.PaymentAttemptService;
import com.yash.paymentplatform.provider.Provider;
import com.yash.paymentplatform.provider.ProviderRouter;
import com.yash.paymentplatform.provider.ProviderExecutor;
import com.yash.paymentplatform.attempt.PaymentAttemptStatus;





@Service
public class PaymentOrchestrator {

    private final ProviderRouter providerRouter;
    private final PaymentAttemptService paymentAttemptService;
    private final PaymentRepository paymentRepository;
    private final ProviderExecutor providerExecutor;

    public PaymentOrchestrator(
            ProviderRouter providerRouter,
            PaymentAttemptService paymentAttemptService,
            PaymentRepository paymentRepository,
            ProviderExecutor providerExecutor) {
        this.providerRouter = providerRouter;
        this.paymentAttemptService = paymentAttemptService;
        this.paymentRepository=paymentRepository;
        this.providerExecutor=providerExecutor;
    }
    public PaymentAttempt processPayment(Long paymentId) {
        Payment payment=paymentRepository.findById(paymentId)
            .orElseThrow(() ->
                    new RuntimeException("Payment not found with id: " + paymentId));
        Provider provider = providerRouter.selectProvider();
        PaymentAttempt attempt = paymentAttemptService.createPaymentAttempt(paymentId, provider);
        paymentAttemptService.updateStatus(attempt,PaymentAttemptStatus.PROCESSING);
        boolean succesful=providerExecutor.execute(payment,provider);
        if (succesful){
            paymentAttemptService.updateStatus(attempt,PaymentAttemptStatus.SUCCEEDED);
            payment.setStatus(PaymentStatus.SUCCEEDED);
        }

        else{
            paymentAttemptService.updateStatus(attempt,PaymentAttemptStatus.FAILED);
            payment.setStatus(PaymentStatus.FAILED);
        }

        paymentRepository.save(payment);
        return attempt;

        }

    }

