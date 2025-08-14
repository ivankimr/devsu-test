
package com.devsu.customers.service.infrastructure.adapter;

import com.devsu.customers.service.domain.model.Customer;
import com.devsu.customers.service.domain.repository.CustomerRepository;
import com.devsu.customers.service.infrastructure.jpa.entity.CustomerJpaEntity;
import com.devsu.customers.service.infrastructure.jpa.repository.SDCustomerRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;
import java.util.UUID;

@Repository
public class CustomerRepositoryAdapter implements CustomerRepository {
    private final SDCustomerRepository customerRepo;
    
    public CustomerRepositoryAdapter(SDCustomerRepository customerRepo) { 
    	this.customerRepo = customerRepo; 
	}
    
    @Override
    public Page<Customer> findAll(Pageable oPageable){
      var page = customerRepo.findAll(oPageable);
      
      return page.map(m -> {
        var oCustomer = new Customer();
        
        oCustomer.setCustomerId(m.getCustomerId());
        oCustomer.setPersonId(m.getPersonId());
        oCustomer.setPasswordHash(m.getPasswordHash());
        oCustomer.setCustomerStatusId(m.getCustomerStatusId());
        
        return oCustomer;
      });
    }    

    @Override
    public Customer save(Customer oCustomer) {
        CustomerJpaEntity jeCustomer = new CustomerJpaEntity();
        
        jeCustomer.setCustomerId(oCustomer.getCustomerId());
        jeCustomer.setPersonId(oCustomer.getPersonId());
        jeCustomer.setPasswordHash(oCustomer.getPasswordHash());
        jeCustomer.setCustomerStatusId(oCustomer.getCustomerStatusId());
        jeCustomer = customerRepo.save(jeCustomer);
        
        oCustomer.setCustomerId(jeCustomer.getCustomerId());
        
        return oCustomer;
    }

    @Override
    public Optional<Customer> findById(UUID id) {
        return customerRepo.findById(id).map(m -> {
            Customer oCustomer = new Customer();
            
            oCustomer.setCustomerId(m.getCustomerId());
            oCustomer.setPersonId(m.getPersonId());
            oCustomer.setPasswordHash(m.getPasswordHash());
            oCustomer.setCustomerStatusId(m.getCustomerStatusId());
            
            return oCustomer;
        });
    }

    @Override
    public void deleteById(UUID id) { 
    	customerRepo.deleteById(id); 
	}
}
