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
import org.springframework.stereotype.Service;

import java.util.List;

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
        List<Account> senderAccount = null;
        List<Account> receiverAccount = null;
        try{
            senderAccount = accountRepository.findAccountById(transferRequestDto.getSenderAccountId());
            receiverAccount = accountRepository.findAccountById(transferRequestDto.getReceiverAccountId());
        } catch (Exception e){
            e.printStackTrace();
            log.error(String.valueOf(e));
        }
        if (senderAccount.size() == 0 || receiverAccount.size() == 0){
            throw new BizException(EnquiryErrorResponse.ACC0002);
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
