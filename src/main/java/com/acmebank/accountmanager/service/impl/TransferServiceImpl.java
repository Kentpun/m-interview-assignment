package com.acmebank.accountmanager.service.impl;

import com.acmebank.accountmanager.entity.dto.TransferRequestDto;
import com.acmebank.accountmanager.entity.dto.TransferResponseDto;
import com.acmebank.accountmanager.entity.po.Transaction;
import com.acmebank.accountmanager.exception.BizException;
import com.acmebank.accountmanager.repository.AccountRepository;
import com.acmebank.accountmanager.repository.TransactionRepository;
import com.acmebank.accountmanager.service.TransferService;
import com.acmebank.accountmanager.utils.response.SystemResponseMessage;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Transactional
public class TransferServiceImpl implements TransferService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    private TransferResponseDto transferResponseDto;

    @Override
    public TransferResponseDto transferBalance(TransferRequestDto transferRequestDto) {
        boolean success = true;
        try {
            accountRepository.updateAccountBalance(transferRequestDto.getSenderAccountId(), (0- transferRequestDto.getAmount()));
            accountRepository.updateAccountBalance(transferRequestDto.getReceiverAccountId(), transferRequestDto.getAmount());
        } catch (Exception e){
            success = false;
            e.printStackTrace();
            log.error(String.valueOf(e));
            throw new BizException(SystemResponseMessage.SYS0001);
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
