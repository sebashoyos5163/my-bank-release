package com.accenture.customers.mapper;

import com.accenture.customers.dto.CustomerDTO;
import com.accenture.customers.dto.CustomerWithAccountsDTO;
import com.accenture.customers.entity.Customer;

public class CustomerMapper {

    public static CustomerDTO mapCustomerToDto(Customer customer, CustomerDTO customerDto) {
        customerDto.setAddress(customer.getAddress());
        customerDto.setDocument(customer.getDocument());
        customerDto.setName(customer.getName());
        customerDto.setPhone(customer.getPhone());
        customerDto.setEmail(customer.getEmail());
        return customerDto;
    }
    public static CustomerWithAccountsDTO mapCustomerToDto(Customer customer, CustomerWithAccountsDTO customerWithAccountsDTO) {
        customerWithAccountsDTO.setAddress(customer.getAddress());
        customerWithAccountsDTO.setDocument(customer.getDocument());
        customerWithAccountsDTO.setName(customer.getName());
        customerWithAccountsDTO.setPhone(customer.getPhone());
        customerWithAccountsDTO.setEmail(customer.getEmail());
        return customerWithAccountsDTO;
    }

    public static Customer mapDtoToCustomer(CustomerDTO customerDto, Customer customer) {
        customer.setAddress(customerDto.getAddress());
        customer.setDocument(customerDto.getDocument());
        customer.setName(customerDto.getName());
        customer.setPhone(customerDto.getPhone());
        customer.setEmail(customerDto.getEmail());
        return customer;
    }
}
