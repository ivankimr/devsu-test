package com.devsu.accounts.service.infrastructure.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.devsu.accounts.service.infrastructure.jpa.entity.AccountTransactionTypeJpaEntity;

public interface SDAccountTransactionTypeRepository
        extends JpaRepository<AccountTransactionTypeJpaEntity, Short> {
}
