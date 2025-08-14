package com.devsu.customers.service.aplication;

import java.util.NoSuchElementException;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.devsu.customers.service.api.dto.CustomerResponse;
import com.devsu.customers.service.domain.repository.CustomerRepository;
import com.devsu.customers.service.domain.repository.PersonRepository;

@Service
public class GetCustomerUseCase {
  private final CustomerRepository crCustomer;
  private final PersonRepository prPerson;
  
  public GetCustomerUseCase(CustomerRepository crCustomer, PersonRepository prPerson){ 
	  this.crCustomer = crCustomer; this.prPerson = prPerson; 
  }

  public CustomerResponse handle(UUID id){
    var oCustomer = crCustomer.findById(id).orElseThrow(() -> new NoSuchElementException("Cliente no encontrado"));
    
    var oPerson = prPerson.findById(oCustomer.getPersonId()).orElseThrow();
    
    return new CustomerResponse(
    		oCustomer.getCustomerId(), 
    		oCustomer.getPersonId(), 
    		oPerson.getPersonName(), 
    		oPerson.getIdentificationNumber());
  }
}
