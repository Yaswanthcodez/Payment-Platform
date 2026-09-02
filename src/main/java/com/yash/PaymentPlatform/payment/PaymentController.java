package com.yash.paymentplatform.payment;

import com.yash.paymentplatform.payment.dto.PaymentRequest;
import com.yash.paymentplatform.payment.dto.PaymentResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/api/v1/payments")
    public PaymentResponse createPayment(
            @Valid @RequestBody PaymentRequest request) {

        Payment payment = paymentService.createPayment(request);

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