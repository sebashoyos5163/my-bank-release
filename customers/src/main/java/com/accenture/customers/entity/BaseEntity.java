package com.accenture.customers.entity;


import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @CreatedDate
    @Column( name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    @CreatedBy
    @Column( name = "created_by", updatable = false)
    private String createdBy;

    @LastModifiedDate
    @Column( name = "updated_date", insertable = false)
    private LocalDateTime updatedDate;

    @LastModifiedBy
    @Column( name = "updated_by", insertable = false)
    private String updatedBy;
}
/*created_date date NOT NULL,
//created_by varchar(20) NOT NULL,
updated_date date DEFAULT NULL,
updated_by varchar(20) DEFAULT NULL*/
