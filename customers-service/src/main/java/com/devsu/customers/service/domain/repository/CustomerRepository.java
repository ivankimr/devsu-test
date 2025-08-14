package com.devsu.customers.service.domain.repository;

import com.devsu.customers.service.domain.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {    
	Page<Customer> findAll(Pageable pageable);    
    Optional<Customer> findById(UUID id);    
    Customer save(Customer customer);    
    void deleteById(UUID id);
}
