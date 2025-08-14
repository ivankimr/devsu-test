package com.devsu.customers.service.aplication;

import com.devsu.customers.service.api.event.CustomerInsUpdEvent;
import com.devsu.customers.service.domain.model.Customer;
import com.devsu.customers.service.domain.model.Person;
import com.devsu.customers.service.domain.repository.CustomerRepository;
import com.devsu.customers.service.domain.repository.PersonRepository;
import com.devsu.customers.service.infrastructure.messaging.CustomerEventsPublisher;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateCustomerUseCase {
	private final CustomerRepository crCustomer;
	private final PersonRepository prPerson;
	private final CustomerEventsPublisher publisher; 

	public CreateCustomerUseCase(CustomerRepository crCustomer, PersonRepository prPerson, CustomerEventsPublisher publisher) {
		this.crCustomer = crCustomer;
		this.prPerson = prPerson;
		this.publisher = publisher;
	}

	@Transactional
	public Customer handle(Person oPerson, String passwordHash, short statusId) {
    	if (passwordHash == null || passwordHash.trim().isEmpty()) {
            throw new IllegalArgumentException("Password no puede ser vacío o nulo");
        }
    	
		prPerson.findByIdentification(oPerson.getIdentificationNumber()).ifPresent(x -> {
			throw new IllegalArgumentException("Identificación ya existe");
		});

		Person oPersonTmp = prPerson.save(oPerson);

		Customer oCustomer = new Customer();
		oCustomer.setPersonId(oPersonTmp.getPersonId());
		oCustomer.setPasswordHash(passwordHash);
		oCustomer.setCustomerStatusId(statusId);

		Customer customerResult = crCustomer.save(oCustomer);

		publisher.publishInsUpd(
				new CustomerInsUpdEvent(
						customerResult.getCustomerId(), 
						customerResult.getPersonId(), 
						oPersonTmp.getPersonName(),
						oPersonTmp.getIdentificationNumber(), 
						customerResult.getCustomerStatusId(), 
						OffsetDateTime.now()));

		return customerResult;
	}
}
