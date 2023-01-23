package com.acmebank.accountmanager.service;

import com.acmebank.accountmanager.entity.dto.AccountEnquiryResponseDto;
import org.springframework.stereotype.Component;

@Component
public interface AccountEnquiryService {
    public AccountEnquiryResponseDto getBalanceByAccountId(Long accountId);

}
