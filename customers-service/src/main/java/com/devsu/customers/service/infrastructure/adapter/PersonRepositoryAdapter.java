package com.devsu.customers.service.infrastructure.adapter;

import com.devsu.customers.service.domain.model.Person;
import com.devsu.customers.service.domain.repository.PersonRepository;
import com.devsu.customers.service.infrastructure.jpa.entity.PersonJpaEntity;
import com.devsu.customers.service.infrastructure.jpa.repository.SDPersonRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public class PersonRepositoryAdapter implements PersonRepository {
    private final SDPersonRepository personRepo;

    public PersonRepositoryAdapter(SDPersonRepository personRepo) { 
    	this.personRepo = personRepo; 
	}

    @Override
    public Person save(Person p) {
        PersonJpaEntity jePerson = toEntity(p);
        jePerson = personRepo.save(jePerson);
        
        return toDomain(jePerson);
    }

    @Override
    public Optional<Person> findById(UUID id) {
        return personRepo.findById(id).map(this::toDomain);
    }

    @Override
    public Optional<Person> findByIdentification(String idNumber) {
        return personRepo.findByIdentificationNumber(idNumber).map(this::toDomain);
    }

    private PersonJpaEntity toEntity(Person oPerson) {
        PersonJpaEntity jePerson = new PersonJpaEntity();
        
        jePerson.setPersonId(oPerson.getPersonId());
        jePerson.setPersonName(oPerson.getPersonName());
        jePerson.setGenderId(oPerson.getGenderId());
        jePerson.setAge(oPerson.getAge());
        jePerson.setIdentificationTypeId(oPerson.getIdentificationTypeId());
        jePerson.setIdentificationNumber(oPerson.getIdentificationNumber());
        jePerson.setAddress(oPerson.getAddress());
        jePerson.setPhone(oPerson.getPhone());
        
        return jePerson;
    }

    private Person toDomain(PersonJpaEntity jePerson) {
        Person oPerson = new Person();
        
        oPerson.setPersonId(jePerson.getPersonId());
        oPerson.setPersonName(jePerson.getPersonName());
        oPerson.setGenderId(jePerson.getGenderId());
        oPerson.setAge(jePerson.getAge());
        oPerson.setIdentificationTypeId(jePerson.getIdentificationTypeId());
        oPerson.setIdentificationNumber(jePerson.getIdentificationNumber());
        oPerson.setAddress(jePerson.getAddress());
        oPerson.setPhone(jePerson.getPhone());
        
        return oPerson;
    }
}