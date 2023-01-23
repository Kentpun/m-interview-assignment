package com.acmebank.accountmanager.service.impl;

import com.acmebank.accountmanager.entity.dto.AccountEnquiryResponseDto;
import com.acmebank.accountmanager.entity.po.Account;
import com.acmebank.accountmanager.exception.BizException;
import com.acmebank.accountmanager.exception.response.EnquiryErrorResponse;
import com.acmebank.accountmanager.repository.AccountRepository;
import com.acmebank.accountmanager.service.AccountEnquiryService;
import com.acmebank.accountmanager.utils.response.SystemResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AccountEnquiryServiceImpl implements AccountEnquiryService {

    @Autowired
    private AccountRepository accountRepository;

    private AccountEnquiryResponseDto responseDto;

    @Override
    public AccountEnquiryResponseDto getBalanceByAccountId(Long accountId) {
        List<Account> accounts;
        try {
             accounts = accountRepository.findAccountById(accountId);
        } catch (Exception e){
            e.printStackTrace();
            log.error(String.valueOf(e));
            throw new BizException(EnquiryErrorResponse.ACC0001);
        }
        responseDto = new AccountEnquiryResponseDto();
        if (accounts != null && accounts.size() > 0) responseDto.setAccounts(accounts);
        else throw new BizException(EnquiryErrorResponse.ACC0002);
        return responseDto;
    }

}
