package com.yash.paymentplatform.payment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yash.paymentplatform.common.exceptions.IdempotencyConflictException;
import com.yash.paymentplatform.merchant.Merchant;
import com.yash.paymentplatform.merchant.MerchantRepository;
import com.yash.paymentplatform.payment.dto.PaymentRequest;


@SpringBootTest
class PaymentServiceTest {
    @Autowired
    PaymentService paymentService;

    @Autowired
    MerchantRepository merchantRepository;

    @Autowired
    PaymentRepository paymentRepository;

    private String idempotencyKey="test-123";

    @Test
    void shouldReturnExistingPaymentForSameIdempotencyKey() {
        Merchant merchant = new Merchant();
        merchant.setName("Test Merchant");
        merchant = merchantRepository.save(merchant);

        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setMerchantId(merchant.getId());
        paymentRequest.setAmount(10000L);
        paymentRequest.setCurrency("INR");

        Payment firstPayment=paymentService.createPayment(paymentRequest, idempotencyKey);
        Payment SecondPayment=paymentService.createPayment(paymentRequest, idempotencyKey);

        assertEquals(firstPayment.getId(),SecondPayment.getId());
        assertEquals(1,paymentRepository.countByMerchant_IdAndIdempotencyKey(merchant.getId(),idempotencyKey));        
}
    @Test
    void shouldRejectSameIdempotencyKeyWithDifferentPaymentData(){
        Merchant merchant = new Merchant();
        merchant.setName("Test Merchant");
        merchant = merchantRepository.save(merchant);

        PaymentRequest paymentRequest1 = new PaymentRequest();
        paymentRequest1.setMerchantId(merchant.getId());
        paymentRequest1.setAmount(10000L);
        paymentRequest1.setCurrency("INR");

        PaymentRequest paymentRequest2 = new PaymentRequest();
        paymentRequest2.setMerchantId(merchant.getId());
        paymentRequest2.setAmount(50000L);
        paymentRequest2.setCurrency("INR");


        Payment firstPayment=paymentService.createPayment(paymentRequest1, idempotencyKey);
        assertThrows(IdempotencyConflictException.class,()->{paymentService.createPayment(paymentRequest2, idempotencyKey);});

    }
}