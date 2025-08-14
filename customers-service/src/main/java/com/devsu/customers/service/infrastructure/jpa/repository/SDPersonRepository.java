package com.devsu.customers.service.infrastructure.jpa.repository;

import com.devsu.customers.service.infrastructure.jpa.entity.PersonJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SDPersonRepository extends JpaRepository<PersonJpaEntity, UUID> {
    Optional<PersonJpaEntity> findByIdentificationNumber(String identificationNumber);
}
