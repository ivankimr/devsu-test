package com.devsu.customers.service.aplication;

import java.time.OffsetDateTime;
import java.util.NoSuchElementException;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsu.customers.service.api.dto.CustomerRequest;
import com.devsu.customers.service.api.dto.CustomerResponse;
import com.devsu.customers.service.api.event.CustomerInsUpdEvent;
import com.devsu.customers.service.domain.model.Customer;
import com.devsu.customers.service.domain.model.Person;
import com.devsu.customers.service.domain.repository.CustomerRepository;
import com.devsu.customers.service.domain.repository.PersonRepository;
import com.devsu.customers.service.infrastructure.messaging.CustomerEventsPublisher;


@Service
public class UpdateCustomerUseCase {
  private final CustomerRepository crCustomer;
  private final PersonRepository prPerson;
  private final CustomerEventsPublisher publisher;
  
  public UpdateCustomerUseCase(CustomerRepository crCustomer, PersonRepository prPerson, CustomerEventsPublisher publisher){ 
	  this.crCustomer = crCustomer; 
	  this.prPerson = prPerson;  
	  this.publisher = publisher;
  }

  @Transactional
  public CustomerResponse handle(UUID id, CustomerRequest oCustomerDataToUpdate){
    var oCustomer = crCustomer.findById(id)
    		.orElseThrow(() -> new NoSuchElementException("Cliente no encontrado"));

    var oPerson = prPerson.findById(oCustomer.getPersonId()).orElseThrow();
    
    oPerson.setPersonName(oCustomerDataToUpdate.personName());
    oPerson.setGenderId(oCustomerDataToUpdate.genderId());
    oPerson.setAge(oCustomerDataToUpdate.age());
    oPerson.setIdentificationTypeId(oCustomerDataToUpdate.identificationTypeId());
    oPerson.setIdentificationNumber(oCustomerDataToUpdate.identificationNumber());
    oPerson.setAddress(oCustomerDataToUpdate.address());
    oPerson.setPhone(oCustomerDataToUpdate.phone());
    
    Person personResult = prPerson.save(oPerson);

    oCustomer.setCustomerStatusId(oCustomerDataToUpdate.customerStatusId());
    
    if (oCustomerDataToUpdate.passwordHash() != null && !oCustomerDataToUpdate.passwordHash().isBlank()) 
    	oCustomer.setPasswordHash(oCustomerDataToUpdate.passwordHash());    
    
    Customer customerResult = crCustomer.save(oCustomer);
    
	publisher.publishInsUpd(
			new CustomerInsUpdEvent(
					customerResult.getCustomerId(), 
					customerResult.getPersonId(), 
					personResult.getPersonName(),
					personResult.getIdentificationNumber(), 
					customerResult.getCustomerStatusId(), 
					OffsetDateTime.now()));

    
    return new CustomerResponse(
    		oCustomer.getCustomerId(), 
    		oCustomer.getPersonId(), 
    		oPerson.getPersonName(), 
    		oPerson.getIdentificationNumber());
  }
}
