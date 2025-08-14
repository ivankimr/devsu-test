package com.devsu.customers.service.aplication;

import org.springframework.stereotype.Service;
import com.devsu.customers.service.api.dto.CustomerResponse;
import com.devsu.customers.service.domain.repository.CustomerRepository;
import com.devsu.customers.service.domain.repository.PersonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class ListCustomersUseCase {
  private final CustomerRepository crCustomer;
  private final PersonRepository prPerson;
  
  public ListCustomersUseCase(CustomerRepository crCustomer, PersonRepository prPerson){ 
	  this.crCustomer = crCustomer; 
	  this.prPerson = prPerson; }

  public Page<CustomerResponse> handle(Pageable pageable){
    return crCustomer.findAll(pageable).map(mCustomer -> {
      var p = prPerson.findById(mCustomer.getPersonId()).orElse(null);
      
      return new CustomerResponse(
    		  mCustomer.getCustomerId(), 
    		  mCustomer.getPersonId(),
              p != null ? p.getPersonName() : null,
              p != null ? p.getIdentificationNumber() : null);
    });
  }
}
