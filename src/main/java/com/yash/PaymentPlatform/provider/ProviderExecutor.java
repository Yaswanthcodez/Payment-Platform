package com.yash.paymentplatform.provider;

import com.yash.paymentplatform.payment.Payment;

public interface ProviderExecutor {

    boolean execute(Payment payment, Provider provider);
}