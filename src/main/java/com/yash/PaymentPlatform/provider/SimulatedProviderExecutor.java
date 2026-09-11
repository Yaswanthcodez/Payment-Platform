package com.yash.paymentplatform.provider;

import org.springframework.stereotype.Component;

import com.yash.paymentplatform.payment.Payment;

@Component
public class SimulatedProviderExecutor implements ProviderExecutor {

    @Override
    public boolean execute(Payment payment, Provider provider) {
        return true;
    }
}