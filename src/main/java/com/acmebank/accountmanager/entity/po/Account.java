package com.acmebank.accountmanager.entity.po;


import com.acmebank.accountmanager.utils.entity.AuditEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
@Builder
public class Account extends AuditEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name = "owner_id")
    private Long ownerId;

    @Basic
    @Column(name = "balance")
    private Double balance;

    @Basic
    @Column(name = "currency")
    private String currency;

}
