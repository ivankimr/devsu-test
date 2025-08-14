package com.devsu.customers.service.infrastructure.jpa.repository;

import com.devsu.customers.service.infrastructure.jpa.entity.CustomerJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SDCustomerRepository extends JpaRepository<CustomerJpaEntity, UUID> { }
