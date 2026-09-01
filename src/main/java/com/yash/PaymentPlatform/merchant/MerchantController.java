package com.yash.paymentplatform.merchant;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yash.paymentplatform.merchant.dto.MerchantRequest;
import com.yash.paymentplatform.merchant.dto.MerchantResponse;



@RestController
public class MerchantController {
    private final MerchantService merchantService;

    public MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }
    
    @PostMapping("/api/v1/merchants")
    public MerchantResponse CreateMerchant(@RequestBody MerchantRequest request) {
        Merchant merchant=merchantService.CreateMerchant(request);

        return new MerchantResponse(merchant.getId(),merchant.getName());
    }

    @GetMapping("/api/v1/merchants/{id}")
    public MerchantResponse getMerchant(@PathVariable Long id) {
        Merchant merchant = merchantService.GetMerchant(id);

    return new MerchantResponse(    
            merchant.getId(),
            merchant.getName()
    );
}

    
}