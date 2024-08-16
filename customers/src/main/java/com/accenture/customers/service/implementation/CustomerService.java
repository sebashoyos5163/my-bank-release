package com.accenture.customers.service.implementation;

import com.accenture.customers.dto.AccountDto;
import com.accenture.customers.dto.CustomerDTO;
import com.accenture.customers.dto.CustomerWithAccountsDTO;
import com.accenture.customers.entity.Customer;
import com.accenture.customers.exceptions.ResourceAlreadyExists;
import com.accenture.customers.exceptions.ResourceNotFound;
import com.accenture.customers.mapper.CustomerMapper;
import com.accenture.customers.repository.CustomerRepository;
import com.accenture.customers.service.ICustomerService;
import com.accenture.customers.service.clients.CustomerFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService implements ICustomerService {


    private CustomerRepository customerRepository;
    private CustomerFeignClient customerFeignClient;

    @Override
    public void createCustomer(CustomerDTO customerDto) {
        Optional<Customer> optionalCustomer = customerRepository.findByDocument(customerDto.getDocument());
        if (optionalCustomer.isPresent()) {
            throw new ResourceAlreadyExists("Cliente","Documento", customerDto.getDocument());
        }
            Customer customer = CustomerMapper.mapDtoToCustomer(customerDto, new Customer());
            /*customer.setCreatedDate(LocalDateTime.now());
            customer.setCreatedBy("Admin");*/
            this.customerRepository.save(customer);
    }

    @Override
    public CustomerDTO fetchCustomerByDocument(String document) {
        Customer customer = customerRepository.findByDocument(document).orElseThrow(
                ()->new ResourceNotFound("Cliente","Documento", document)
        );
        return CustomerMapper.mapCustomerToDto(customer, new CustomerDTO());
    }

    @Override
    public CustomerDTO fetchCustomerByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFound("Cliente","Email", email)
        );
        return CustomerMapper.mapCustomerToDto(customer, new CustomerDTO());
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDto) {
        Customer customer = customerRepository.findByDocument(customerDto.getDocument()).orElseThrow(
                ()-> new ResourceNotFound("Cliente","Documento", customerDto.getDocument())
        );

            CustomerMapper.mapDtoToCustomer(customerDto, customer);
            this.customerRepository.save(customer);
            return customerDto;
    }

    @Override
    public void deleteCustomerByDocument(String document) {
        customerRepository.deleteByDocument(document);
    }

    @Override
    public void deleteCustomerByEmail(String email) {

    }

    @Override
    public CustomerWithAccountsDTO fetchCustomerWithAccountsByDocument(String document) {
        Customer customer = customerRepository.findByDocument(document).orElseThrow(
                ()->new ResourceNotFound("Cliente","Documento", document)
        );

        CustomerWithAccountsDTO customerWithAccountsDTO = CustomerMapper.mapCustomerToDto(customer, new CustomerWithAccountsDTO());
        ResponseEntity<List<AccountDto>> accountsResponse = customerFeignClient.fetchCustomerAccounts(customer.getCustomerId());
        List<AccountDto> accounts = accountsResponse.getBody();
        customerWithAccountsDTO.setAccounts(accounts);
        return customerWithAccountsDTO;
    }

    @Override
    public CustomerDTO findById(Long id) {
            Customer customer = customerRepository.findById(id).orElseThrow(
                    ()->new ResourceNotFound("Cliente","Documento", String.valueOf(id))
            );
            return CustomerMapper.mapCustomerToDto(customer, new CustomerDTO());
        }
}


