package com.yash.paymentplatform.attempt;

import org.springframework.stereotype.Service;

import com.yash.paymentplatform.payment.Payment;
import com.yash.paymentplatform.payment.PaymentRepository;
import com.yash.paymentplatform.provider.Provider;


@Service
public class PaymentAttemptService{
    private final PaymentRepository paymentRepository;
    private final PaymentAttemptRepository paymentAttemptRepository;

    public PaymentAttemptService(
            PaymentRepository paymentRepository,

            PaymentAttemptRepository paymentAttemptRepository) {
        this.paymentRepository = paymentRepository;
        this.paymentAttemptRepository=paymentAttemptRepository;
    }

    public PaymentAttempt createPaymentAttempt(Long paymentId,Provider provider){
                
        Payment payment=paymentRepository.findById(paymentId)
        .orElseThrow(() ->
                    new RuntimeException("Payment not found with id: " + paymentId));
        
        PaymentAttempt attempt=new PaymentAttempt();
        attempt.setPayment(payment);
        attempt.setProvider(provider);
        attempt.setStatus(PaymentAttemptStatus.INITIATED); 

        return paymentAttemptRepository.save(attempt);   
    }

    public PaymentAttempt updateStatus(PaymentAttempt paymentAttempt,PaymentAttemptStatus paymentAttemptStatus){
        paymentAttempt.setStatus(paymentAttemptStatus);       
        return paymentAttemptRepository.save(paymentAttempt);   
    }


}