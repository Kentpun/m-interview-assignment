package com.acmebank.accountmanager.service;

import com.acmebank.accountmanager.entity.dto.TransferRequestDto;
import com.acmebank.accountmanager.entity.dto.TransferResponseDto;

public interface TransferService {
    public TransferResponseDto transferBalance(TransferRequestDto transferRequestDto);
}
