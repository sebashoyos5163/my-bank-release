package com.accenture.customers.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column( name = "customer_id")
    private Long customerId;
    @Column( name = "document")
    private String document;
    @Column( name = "name")
    private String name;
    @Column( name = "email")
    private String email;
    @Column( name = "phone")
    private String phone;
    @Column( name = "address")
    private String address;

}
/*customer_id int AUTO_INCREMENT PRIMARY KEY,
document varchar(20) NOT NULL,
name varchar(100) NOT NULL,
email varchar(100) NOT NULL,
phone varchar(20) NOT NULL,
address varchar(200) NOT NULL,*/
