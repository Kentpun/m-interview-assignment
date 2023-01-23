package com.acmebank.accountmanager.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransferResponseDto {
    private String amount;
    private String senderAccountId;
    private String receiverAccountId;
    private String status;
}
