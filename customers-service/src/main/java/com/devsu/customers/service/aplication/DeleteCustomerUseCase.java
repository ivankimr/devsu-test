package com.devsu.customers.service.aplication;

import com.devsu.customers.service.api.event.CustomerDeletedEvent;
import com.devsu.customers.service.domain.repository.CustomerRepository;
import com.devsu.customers.service.infrastructure.messaging.CustomerEventsPublisher;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class DeleteCustomerUseCase {
    private final CustomerRepository crCustomer;
    private final CustomerEventsPublisher publisher;

    public DeleteCustomerUseCase(CustomerRepository crCustomer, CustomerEventsPublisher publisher) {
        this.crCustomer = crCustomer;
        this.publisher = publisher;
    }

    @Transactional
    public void handle(UUID id) {
    	crCustomer.findById(id)
    		.orElseThrow(() -> new NoSuchElementException("Cliente no encontrado"));
    	
    	crCustomer.deleteById(id);
    	
    	publisher.publishDeleted(new CustomerDeletedEvent(id, java.time.OffsetDateTime.now()));

    }
}
