package com.yash.paymentplatform.payment.dto;

import com.yash.paymentplatform.payment.PaymentStatus;

import java.time.Instant;

public class PaymentResponse{
    private Long id;
    private Long merchantId;
    private Long amount;
    private String currency;
    private PaymentStatus status;
    private Instant createdAt;

    public PaymentResponse(
            Long id,
            Long merchantId,
            Long amount,
            String currency,
            PaymentStatus status,
            Instant createdAt) {

        this.id = id;
        this.merchantId = merchantId;
        this.amount = amount;
        this.currency = currency;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public Long getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}