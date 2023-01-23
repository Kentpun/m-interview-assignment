package com.acmebank.accountmanager.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransferRequestDto {
    @NotNull
    private Long senderAccountId;
    @NotNull
    private Long receiverAccountId;
    @NotNull
    private Double amount;
    @NotNull
    private String currency;
}
