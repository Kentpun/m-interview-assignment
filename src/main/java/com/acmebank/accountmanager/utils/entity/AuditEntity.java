package com.acmebank.accountmanager.utils.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(value = AuditingEntityListener.class)
public class AuditEntity implements Serializable {
    @Version
    @Column(name = "version")
    protected int version;

    @Column(name = "create_dt")
    @CreatedDate
    protected Date createDt;

    @Column(name = "update_dt")
    @LastModifiedDate
    protected Date updateDt;
}
