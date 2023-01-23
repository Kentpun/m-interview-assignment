package com.acmebank.accountmanager.entity.po;

import com.acmebank.accountmanager.utils.entity.AuditEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
@Builder
public class Transaction extends AuditEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name = "sender_id")
    private Long senderId;

    @Basic
    @Column(name = "receiver_id")
    private Long receiverId;

    @Basic
    @Column(name = "amount")
    private Double amount;

    @Basic
    @Column(name = "currency")
    private String currency;

}
