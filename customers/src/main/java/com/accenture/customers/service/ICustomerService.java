package com.accenture.customers.service;

import com.accenture.customers.dto.CustomerDTO;
import com.accenture.customers.dto.CustomerWithAccountsDTO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;


public interface ICustomerService {
    void createCustomer(CustomerDTO customerDto);
    CustomerDTO fetchCustomerByDocument(String document);
    CustomerDTO fetchCustomerByEmail(String email);
    CustomerDTO updateCustomer(CustomerDTO customerDto);
    void deleteCustomerByDocument(String document);
    void deleteCustomerByEmail(String email);
    CustomerWithAccountsDTO fetchCustomerWithAccountsByDocument(String document);
    CustomerDTO findById(Long id);
}
