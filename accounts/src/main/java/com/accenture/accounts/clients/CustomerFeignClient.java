package com.accenture.accounts.clients;

import com.accenture.accounts.dto.CustomerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient( value = "customers")
public interface CustomerFeignClient {
    @GetMapping(value = "/api/findById/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CustomerDTO> fetchCustomerInformation(@PathVariable Long id);
}




