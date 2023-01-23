package com.acmebank.accountmanager.service.impl;

import com.acmebank.accountmanager.entity.dto.AccountEnquiryResponseDto;
import com.acmebank.accountmanager.entity.po.Account;
import com.acmebank.accountmanager.repository.AccountRepository;
import com.acmebank.accountmanager.service.AccountEnquiryService;
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
        List<Account> accounts = accountRepository.findAccountById(accountId);
        responseDto = new AccountEnquiryResponseDto();
        responseDto.setAccounts(accounts);
        return responseDto;
    }

}
