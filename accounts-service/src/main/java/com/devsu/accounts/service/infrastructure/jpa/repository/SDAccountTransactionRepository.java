package com.devsu.accounts.service.infrastructure.jpa.repository;

import com.devsu.accounts.service.infrastructure.jpa.entity.AccountTransactionJpaEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.UUID;

public interface SDAccountTransactionRepository extends JpaRepository<AccountTransactionJpaEntity, UUID> { 
	Page<AccountTransactionJpaEntity> findByAccountId(UUID accountId, Pageable pageable);
	
	Page<AccountTransactionJpaEntity> findByAccount_CustomerIdAndTransactionDateBetween(
            UUID customerId,
            OffsetDateTime from,
            OffsetDateTime to, 
            Pageable pageable
    );	
	
	Page<AccountTransactionJpaEntity> findByAccount_CustomerIdAndTransactionDateBetweenOrderByTransactionDateAsc(
            UUID customerId,
            OffsetDateTime from,
            OffsetDateTime to, 
            Pageable pageable
    );		
}

