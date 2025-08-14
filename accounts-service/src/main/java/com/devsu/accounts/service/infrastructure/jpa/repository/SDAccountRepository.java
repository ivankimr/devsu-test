package com.devsu.accounts.service.infrastructure.jpa.repository;

import com.devsu.accounts.service.infrastructure.jpa.entity.AccountJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SDAccountRepository extends JpaRepository<AccountJpaEntity, UUID> {
    Optional<AccountJpaEntity> findByAccountNumber(String accountNumber);
    List<AccountJpaEntity> findByCustomerId(UUID customerId);
    List<AccountJpaEntity> findByAccountIdIn(Collection<UUID> ids);
}
