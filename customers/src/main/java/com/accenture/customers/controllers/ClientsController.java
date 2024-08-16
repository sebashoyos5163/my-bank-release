package com.accenture.customers.controllers;


import com.accenture.customers.dto.CustomerDTO;
import com.accenture.customers.dto.CustomerWithAccountsDTO;
import com.accenture.customers.service.ICustomerService;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api")
public class ClientsController {

    ICustomerService iCustomerService;

    @GetMapping(value = "/fetchWithAccounts/{document}")
    public ResponseEntity<CustomerWithAccountsDTO> fetchWithAccounts(
            @RequestParam
            @NotEmpty( message = "El campo numero de documento no puede ser vacío")
            @Size( min = 5, max = 20, message = "El documento debe ocupar de 5 a 20 caracteres")
            String document){
        CustomerDTO customerDTO = iCustomerService.fetchCustomerByDocument(document);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new CustomerWithAccountsDTO());
    }
}


