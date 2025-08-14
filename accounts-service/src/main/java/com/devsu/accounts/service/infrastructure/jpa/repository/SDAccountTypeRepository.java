package com.devsu.accounts.service.infrastructure.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsu.accounts.service.infrastructure.jpa.entity.AccountTypeJpaEntity;

public interface SDAccountTypeRepository extends JpaRepository<AccountTypeJpaEntity, Short> {}
