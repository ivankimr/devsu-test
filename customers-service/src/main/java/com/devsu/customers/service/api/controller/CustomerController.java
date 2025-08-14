package com.devsu.customers.service.api.controller;

import com.devsu.customers.service.api.dto.CustomerRequest;
import com.devsu.customers.service.api.dto.CustomerResponse;
import com.devsu.customers.service.aplication.CreateCustomerUseCase;
import com.devsu.customers.service.aplication.DeleteCustomerUseCase;
import com.devsu.customers.service.aplication.GetCustomerUseCase;
import com.devsu.customers.service.aplication.ListCustomersUseCase;
import com.devsu.customers.service.aplication.UpdateCustomerUseCase;
import com.devsu.customers.service.domain.model.Customer;
import com.devsu.customers.service.domain.model.Person;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/clientes")
public class CustomerController {
    private final CreateCustomerUseCase createCustomer;
    private final GetCustomerUseCase customer;
    private final ListCustomersUseCase customerList;
    private final UpdateCustomerUseCase updateCustomer;
    private final DeleteCustomerUseCase deleteCustomer;

    public CustomerController(
    		CreateCustomerUseCase createCustomer, GetCustomerUseCase customer, ListCustomersUseCase customerList, 
    		UpdateCustomerUseCase updateCustomer, DeleteCustomerUseCase deleteCustomer) { 
    	this.createCustomer = createCustomer;
    	this.customer = customer;
        this.customerList = customerList; 
        this.updateCustomer = updateCustomer;
        this.deleteCustomer = deleteCustomer;
	}

    @GetMapping
    public Page<CustomerResponse> getCustomerList(Pageable pageable){ 
    	return customerList.handle(pageable); 
	}
    
    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerRequest customer) {
        Person oPerson = new Person();
        
        oPerson.setPersonName(customer.personName());
        oPerson.setGenderId(customer.genderId());
        oPerson.setAge(customer.age());
        oPerson.setIdentificationTypeId(customer.identificationTypeId());
        oPerson.setIdentificationNumber(customer.identificationNumber());
        oPerson.setAddress(customer.address());
        oPerson.setPhone(customer.phone());

        Customer oCustomer = createCustomer.handle(oPerson, customer.passwordHash(), customer.customerStatusId());
        
        return ResponseEntity
        		.status(HttpStatus.CREATED)
                .body(new CustomerResponse(
                		oCustomer.getCustomerId(), 
                		oCustomer.getPersonId(), 
                		customer.personName(), 
                		customer.identificationNumber()
            		)
        		);
    }
    

    @GetMapping("/{id}")
    public CustomerResponse getCustomer(@PathVariable UUID id){ 
    	return customer.handle(id); 
	}

    @PutMapping("/{id}")
    public CustomerResponse updateCustomerr(@PathVariable UUID id, @RequestBody CustomerRequest oCustomerDataToUpadte){
      return updateCustomer.handle(id, oCustomerDataToUpadte);
    }    
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable UUID id) {
        deleteCustomer.handle(id);
    }
    
}
