package com.accenture.customers.repository;

import com.accenture.customers.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByDocument(String document);
    Optional<Customer> findByEmail(String email);
    @Modifying
    @Transactional
    void deleteByDocument(String document);
    @Modifying
    @Transactional
    void deleteByEmail(String email);
}
