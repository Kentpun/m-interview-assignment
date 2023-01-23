package com.acmebank.accountmanager.entity.dto;

import com.acmebank.accountmanager.entity.po.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountEnquiryResponseDto {
    private List<Account> accounts;
}
