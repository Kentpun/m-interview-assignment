package com.acmebank.accountmanager.repository;

import com.acmebank.accountmanager.entity.po.Account;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository  extends JpaRepositoryImplementation<Account, Long> {
    @Query("select a from Account a where a.ownerId = :ownerId")
    List<Account> findAccountsByOwnerId(@Param("ownerId") Long ownerId);

    @Query("select a from Account a where a.ownerId = :ownerId AND a.id = :accountId")
    List<Account> findAccountByOwnerIdAndAccountId(@Param("ownerId") Long ownerId, @Param("accountId") Long accountId);

    @Modifying
    @Query(value = "UPDATE Account acc set acc.balance = (acc.balance + ?2) WHERE acc.id =?1", nativeQuery = true)
    void updateAccountBalance(Long accountId, Double amount);
}
