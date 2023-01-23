package com.acmebank.accountmanager.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransferRequestDto {
    private Long senderAccountId;
    private Long receiverAccountId;
    private Double amount;
    private String currency;
}
