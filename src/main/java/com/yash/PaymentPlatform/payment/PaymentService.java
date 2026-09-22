package com.yash.paymentplatform.payment;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.yash.paymentplatform.common.exceptions.IdempotencyConflictException;
import com.yash.paymentplatform.common.exceptions.MerchantNotFoundException;
import com.yash.paymentplatform.merchant.Merchant;
import com.yash.paymentplatform.merchant.MerchantRepository;
import com.yash.paymentplatform.payment.dto.PaymentRequest;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final MerchantRepository merchantRepository;

    public PaymentService(
            PaymentRepository paymentRepository,
            MerchantRepository merchantRepository) {
        this.paymentRepository = paymentRepository;
        this.merchantRepository = merchantRepository;
    }

    public Payment createPayment(PaymentRequest request,String idempotencyKey) {
    Optional<Payment> existingPayment=paymentRepository.findByMerchant_IdAndIdempotencyKey(request.getMerchantId(),idempotencyKey);
    if(existingPayment.isPresent()){
        Payment paymentExist=existingPayment.get();
        if(!((paymentExist.getAmount()).equals( request.getAmount()))||!((paymentExist.getCurrency()).equals(request.getCurrency()))){
            throw new IdempotencyConflictException();
        }
        return paymentExist;
    }

    
    Merchant merchant = merchantRepository.findById(request.getMerchantId())
            .orElseThrow(() -> new MerchantNotFoundException(request.getMerchantId()));

    Payment payment = new Payment();
    payment.setMerchant(merchant);
    payment.setAmount(request.getAmount());
    payment.setCurrency(request.getCurrency());
    payment.setStatus(PaymentStatus.CREATED);
    payment.setIdempotencyKey(idempotencyKey);
    return paymentRepository.save(payment);

    }

    public Payment getPayment(Long id) {

    return paymentRepository.findById(id)
            .orElseThrow(() ->
                    new RuntimeException("Payment not found with id: " + id));
}
}