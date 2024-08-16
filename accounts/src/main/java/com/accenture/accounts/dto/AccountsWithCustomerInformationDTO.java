package com.accenture.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountsWithCustomerInformationDTO {
    private String document;
    private String name;
    private String email;
    private String phone;
    private String address;
    private Long accountNumber;
}
