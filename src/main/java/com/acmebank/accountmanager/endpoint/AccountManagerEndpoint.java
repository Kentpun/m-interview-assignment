package com.acmebank.accountmanager.endpoint;


import com.acmebank.accountmanager.entity.dto.AccountEnquiryRequestDto;
import com.acmebank.accountmanager.entity.dto.AccountEnquiryResponseDto;
import com.acmebank.accountmanager.entity.dto.TransferRequestDto;
import com.acmebank.accountmanager.entity.dto.TransferResponseDto;
import com.acmebank.accountmanager.exception.BizException;
import com.acmebank.accountmanager.exception.GlobalExceptionHandler;
import com.acmebank.accountmanager.service.AccountEnquiryService;
import com.acmebank.accountmanager.service.TransferService;
import com.acmebank.accountmanager.utils.response.Res;
import com.acmebank.accountmanager.utils.response.Result;
import com.acmebank.accountmanager.utils.response.SystemResponseMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/account-manager")
@AllArgsConstructor
@Slf4j
public class AccountManagerEndpoint extends GlobalExceptionHandler {
    @Autowired
    private AccountEnquiryService accountEnquiryService;

    @Autowired
    private TransferService transferService;

    @PostMapping("/balance")
    public Result<AccountEnquiryResponseDto> getBalance(@Valid @RequestBody AccountEnquiryRequestDto accountEnquiryRequestDto){
        log.info("account enquiry info {}", accountEnquiryRequestDto);
        return Res.success(accountEnquiryService.getAccountByOwnerId(accountEnquiryRequestDto));
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
