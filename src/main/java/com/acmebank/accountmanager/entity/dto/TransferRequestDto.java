package com.acmebank.accountmanager.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransferRequestDto {
    @NotNull
    @Valid
    private Long senderAccountId;
    @NotNull
    @Valid
    private Long receiverAccountId;
    @NotNull
    @Valid
    private Double amount;
    @NotNull
    @Valid
    private String currency;
    // a mock parameter, should be handled by proper OAuth
    @NotNull
    @Valid
    private Long ownerId;
}
