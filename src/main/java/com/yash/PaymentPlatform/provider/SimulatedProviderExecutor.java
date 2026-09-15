package com.yash.paymentplatform.provider;

import org.springframework.stereotype.Component;

import com.yash.paymentplatform.payment.Payment;

@Component
public class SimulatedProviderExecutor implements ProviderExecutor {
    private ProviderExecutionOutcome outcome = ProviderExecutionOutcome.SUCCESS;

    public void setOutcome(ProviderExecutionOutcome outcome){
        this.outcome = outcome;
    }


    @Override
    public ProviderExecutionOutcome execute(Payment payment, Provider provider) {
        return outcome;
    }
}