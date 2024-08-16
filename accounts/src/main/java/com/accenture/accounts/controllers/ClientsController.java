package com.accenture.accounts.controllers;

import com.accenture.accounts.dto.AccountsWithCustomerInformationDTO;
import com.accenture.accounts.services.implementation.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class ClientsController {

    private final AccountService accountService;
    public ClientsController(AccountService accountService){
        this.accountService = accountService;
    }
    //Fetch <customers> service for getting customer information |synchronous fetch vía http|
    @GetMapping(value = "/fetchCustomerInformationByAccount/{accountNumber}")
    public ResponseEntity<AccountsWithCustomerInformationDTO> fetchCustomerInformationByAccount(@PathVariable Long accountNumber){
        AccountsWithCustomerInformationDTO accountsWithCustomerInformationDTO = accountService.fetchCustomerInformationWithAccountsByAccount(accountNumber);
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountsWithCustomerInformationDTO);
    }
}
