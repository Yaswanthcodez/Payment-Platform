package com.yash.paymentplatform.merchant;
import org.springframework.stereotype.Service;
import com.yash.paymentplatform.merchant.dto.MerchantRequest;
import com.yash.paymentplatform.common.exceptions.MerchantNotFoundException;
@Service
public class MerchantService {
    private final MerchantRepository merchantRepository;

    public MerchantService(MerchantRepository merchantRepository) {
        this.merchantRepository = merchantRepository;
    }

    public Merchant CreateMerchant(MerchantRequest request){
        Merchant merchant=new Merchant();
        merchant.setName(request.getName());

        return merchantRepository.save(merchant);    }

    public Merchant GetMerchant(Long id){
        return merchantRepository.findById(id)
        .orElseThrow(() -> new MerchantNotFoundException(id));
        
}
}