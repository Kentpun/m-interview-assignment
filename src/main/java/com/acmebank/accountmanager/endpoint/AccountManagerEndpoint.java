package com.acmebank.accountmanager.endpoint;


import com.acmebank.accountmanager.entity.dto.AccountEnquiryResponseDto;
import com.acmebank.accountmanager.entity.dto.TransferRequestDto;
import com.acmebank.accountmanager.entity.dto.TransferResponseDto;
import com.acmebank.accountmanager.exception.BizException;
import com.acmebank.accountmanager.service.AccountEnquiryService;
import com.acmebank.accountmanager.service.TransferService;
import com.acmebank.accountmanager.utils.response.Res;
import com.acmebank.accountmanager.utils.response.Result;
import com.acmebank.accountmanager.utils.response.SystemResponseMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/account-manager")
@AllArgsConstructor
@Slf4j
public class AccountManagerEndpoint {
    @Autowired
    private AccountEnquiryService accountEnquiryService;

    @Autowired
    private TransferService transferService;

    @GetMapping("/balances/{accountId}")
    public Result<AccountEnquiryResponseDto> getBalance(@PathVariable Long accountId){
        log.info("account enquiry info {}", accountId);
        return Res.success(accountEnquiryService.getBalanceByAccountId(accountId));
    }

    @PostMapping("/balance/transfer")
    public Result<TransferResponseDto> transferBalance(@Valid @RequestBody TransferRequestDto transferRequestDto){
        log.info("Balance transfer info {}", transferRequestDto);
        return Res.success(transferService.transferBalance(transferRequestDto));
    }

    @GetMapping("/error")
    public Result<String> error(){
        throw new BizException(SystemResponseMessage.SYS9999);
    }
}