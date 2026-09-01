package com.yash.paymentplatform.merchant;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MerchantController {
    private final MerchantService merchantService;

    public MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }
    
    @PostMapping("/api/v1/merchants")
    public Merchant CreateMerchant(@RequestBody Merchant merchant) {
        return merchantService.CreateMerchant(merchant);
    }

    @GetMapping("/api/v1/merchants/{id}")
    public Merchant GetMerchant(@PathVariable Long id){
        return merchantService.GetMerchant(id);
    }

    
}