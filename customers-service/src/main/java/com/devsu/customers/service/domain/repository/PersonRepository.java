package com.devsu.customers.service.domain.repository;
import com.devsu.customers.service.domain.model.Person;

import java.util.Optional;
import java.util.UUID;

public interface PersonRepository {
    Person save(Person person);
    Optional<Person> findById(UUID id);
    Optional<Person> findByIdentification(String identificationNumber);
}
