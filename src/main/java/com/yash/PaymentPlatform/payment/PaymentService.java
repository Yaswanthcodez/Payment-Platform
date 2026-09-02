package com.yash.paymentplatform.payment;

import org.springframework.stereotype.Service;

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

    public Payment createPayment(PaymentRequest request) {

    Merchant merchant = merchantRepository.findById(request.getMerchantId())
            .orElseThrow(() -> new MerchantNotFoundException(request.getMerchantId()));

    Payment payment = new Payment();
    payment.setMerchant(merchant);
    payment.setAmount(request.getAmount());
    payment.setCurrency(request.getCurrency());
    payment.setStatus(PaymentStatus.CREATED);

    return paymentRepository.save(payment);

    }

    public Payment getPayment(Long id) {

    return paymentRepository.findById(id)
            .orElseThrow(() ->
                    new RuntimeException("Payment not found with id: " + id));
}
}