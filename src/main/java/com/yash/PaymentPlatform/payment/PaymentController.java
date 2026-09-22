package com.yash.paymentplatform.payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.yash.paymentplatform.payment.dto.PaymentRequest;
import com.yash.paymentplatform.payment.dto.PaymentResponse;

import jakarta.validation.Valid;

@RestController
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/api/v1/payments")
    public PaymentResponse createPayment(
            @Valid @RequestBody PaymentRequest request,
            @RequestHeader("Idempotency-Key") String idempotencyKey) {

        Payment payment = paymentService.createPayment(request,idempotencyKey);

        return new PaymentResponse(
                payment.getId(),
                payment.getMerchant().getId(),
                payment.getAmount(),
                payment.getCurrency(),
                payment.getStatus(),
                payment.getCreatedAt()
        );
    }

    @GetMapping("/api/v1/payments/{id}")
    public PaymentResponse getPayment(@PathVariable Long id) {

        Payment payment = paymentService.getPayment(id);

        return new PaymentResponse(
                payment.getId(),
                payment.getMerchant().getId(),
                payment.getAmount(),
                payment.getCurrency(),
                payment.getStatus(),
                payment.getCreatedAt()
        );
    }
}