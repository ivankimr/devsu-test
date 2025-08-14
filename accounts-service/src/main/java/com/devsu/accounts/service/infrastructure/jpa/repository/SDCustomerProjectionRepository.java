package com.devsu.accounts.service.infrastructure.jpa.repository;

import com.devsu.accounts.service.infrastructure.jpa.entity.CustomerProjectionJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface SDCustomerProjectionRepository extends JpaRepository<CustomerProjectionJpaEntity, UUID> { }
