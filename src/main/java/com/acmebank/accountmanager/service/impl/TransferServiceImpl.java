package com.acmebank.accountmanager.service.impl;

import com.acmebank.accountmanager.entity.dto.TransferRequestDto;
import com.acmebank.accountmanager.entity.dto.TransferResponseDto;
import com.acmebank.accountmanager.entity.po.Account;
import com.acmebank.accountmanager.entity.po.Transaction;
import com.acmebank.accountmanager.exception.BizException;
import com.acmebank.accountmanager.exception.response.EnquiryErrorResponse;
import com.acmebank.accountmanager.exception.response.TransferErrorResponse;
import com.acmebank.accountmanager.repository.AccountRepository;
import com.acmebank.accountmanager.repository.TransactionRepository;
import com.acmebank.accountmanager.service.TransferService;
import com.acmebank.accountmanager.utils.response.SystemResponseMessage;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class TransferServiceImpl implements TransferService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    private TransferResponseDto transferResponseDto;

    @Override
    @Transactional(rollbackOn=BizException.class)
    public TransferResponseDto transferBalance(TransferRequestDto transferRequestDto) {
        // Check API Request Business Logics
        if (Objects.equals(transferRequestDto.getSenderAccountId(), transferRequestDto.getReceiverAccountId())){
            throw new BizException(TransferErrorResponse.TXN0002);
        }
        Account senderAccount = null;
        Account receiverAccount = null;
        List<Account> accountsOfRequestOwner = null;
        try{
            accountsOfRequestOwner = accountRepository.findAccountsByOwnerId(transferRequestDto.getOwnerId());
            Assert.notNull(accountsOfRequestOwner, "Error querying Accounts");
        } catch (Exception e){
            e.printStackTrace();
            log.error(String.valueOf(e));
            throw new BizException(EnquiryErrorResponse.ACC0001);
        }
        senderAccount = accountsOfRequestOwner.stream().filter(account -> transferRequestDto.getSenderAccountId().equals(account.getId())).findFirst().orElse(null);
        receiverAccount = accountsOfRequestOwner.stream().filter(account -> transferRequestDto.getReceiverAccountId().equals(account.getId())).findFirst().orElse(null);
        // Check if Both Accounts Exists
        try {
            Assert.notNull(senderAccount, "Sender Account does not exist");
            log.info("Sender Account: "+ senderAccount.getId().toString());
            Assert.notNull(receiverAccount, "Receiver Account does not exist");
            log.info("Receiver Account: "+ receiverAccount.getId().toString());
        } catch (Exception e) {
            e.printStackTrace();
            log.error(String.valueOf(e));
            throw new BizException(EnquiryErrorResponse.ACC0002);
        }
        /*
            Business Logic:
            1. Check if the Sender Account has sufficient balance
        */
        if (senderAccount.getBalance() < transferRequestDto.getAmount()){
            throw new BizException(TransferErrorResponse.TXN0002);
        }

        boolean success = true;
        try {
            accountRepository.updateAccountBalance(transferRequestDto.getSenderAccountId(), (0- transferRequestDto.getAmount()));
            accountRepository.updateAccountBalance(transferRequestDto.getReceiverAccountId(), transferRequestDto.getAmount());
        } catch (Exception e){
            success = false;
            e.printStackTrace();
            log.error(String.valueOf(e));
            throw new BizException(TransferErrorResponse.TXN0001);
        }
        transferResponseDto = new TransferResponseDto();

        if (success){
            transferResponseDto.setAmount(String.valueOf(transferRequestDto.getAmount()));
            transferResponseDto.setSenderAccountId(String.valueOf(transferRequestDto.getSenderAccountId()));
            transferResponseDto.setReceiverAccountId(String.valueOf(transferRequestDto.getReceiverAccountId()));
            transferResponseDto.setStatus("Success");
            try{
                Transaction transaction = new Transaction();
                transaction.setAmount(transferRequestDto.getAmount());
                transaction.setCurrency(transferRequestDto.getCurrency());
                transaction.setReceiverId(transferRequestDto.getReceiverAccountId());
                transaction.setSenderId(transferRequestDto.getSenderAccountId());
                transactionRepository.save(transaction);
            } catch (Exception e){
                e.printStackTrace();
                log.error(String.valueOf(e));
            }
        }
        return transferResponseDto;
    }
}
