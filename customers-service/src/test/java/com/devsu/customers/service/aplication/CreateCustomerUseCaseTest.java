package com.devsu.customers.service.aplication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import com.devsu.customers.service.api.event.CustomerInsUpdEvent;
import com.devsu.customers.service.domain.model.Customer;
import com.devsu.customers.service.domain.model.Person;
import com.devsu.customers.service.domain.repository.CustomerRepository;
import com.devsu.customers.service.domain.repository.PersonRepository;
import com.devsu.customers.service.infrastructure.messaging.CustomerEventsPublisher;

class CreateCustomerUseCaseTest {

    private CustomerRepository customerRepository;
    private PersonRepository personRepository;
    private CustomerEventsPublisher publisher;

    private CreateCustomerUseCase useCase;

    @BeforeEach
    void setUp() {
        customerRepository = mock(CustomerRepository.class);
        personRepository = mock(PersonRepository.class);
        publisher = mock(CustomerEventsPublisher.class);

        useCase = new CreateCustomerUseCase(customerRepository, personRepository, publisher);
    }

    @Test
    void handle_shouldCreateCustomerSuccessfully() {
        // Arrange
        Person person = new Person();
        person.setPersonName("Susana Rueda");
        person.setIdentificationNumber("1234567897");

        when(personRepository.findByIdentification("1234567897")).thenReturn(Optional.empty());

        Person savedPerson = new Person();
        savedPerson.setPersonId(UUID.randomUUID());
        savedPerson.setPersonName("Susana Rueda");
        savedPerson.setIdentificationNumber("1234567897");

        when(personRepository.save(any(Person.class))).thenReturn(savedPerson);

        Customer savedCustomer = new Customer();
        savedCustomer.setCustomerId(UUID.randomUUID());
        savedCustomer.setPersonId(savedPerson.getPersonId());
        savedCustomer.setCustomerStatusId((short)1);
        savedCustomer.setPasswordHash("hashedPassword");

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        // Act
        Customer result = useCase.handle(person, "hashedPassword", (short)1);

        // Assert
        assertNotNull(result);
        assertEquals(savedPerson.getPersonId(), result.getPersonId());
        assertEquals("hashedPassword", result.getPasswordHash());
        assertEquals(1, result.getCustomerStatusId());

        // Verificar que se publicó el evento (simulación Rabbit MQ)
        ArgumentCaptor<CustomerInsUpdEvent> captor = ArgumentCaptor.forClass(CustomerInsUpdEvent.class);
        verify(publisher, times(1)).publishInsUpd(captor.capture());

        CustomerInsUpdEvent event = captor.getValue();
        assertEquals(savedCustomer.getCustomerId(), event.customerId());
        assertEquals(savedCustomer.getPersonId(), event.personId());
    }
    
    @Test
    void handle_shouldThrowException_whenPasswordIsEmpty() {
        // Arrange
        Person person = new Person();
        person.setPersonName("Susana Rueda");
        person.setIdentificationNumber("1234567897");

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> useCase.handle(person, "", (short)1)
        );
        assertEquals("Password no puede ser vacío o nulo", exception.getMessage());

        // También con null
        exception = assertThrows(
            IllegalArgumentException.class,
            () -> useCase.handle(person, null, (short)1)
        );
        assertEquals("Password no puede ser vacío o nulo", exception.getMessage());
    }
    
    @Test
    void handle_shouldThrowException_whenIdentificationAlreadyExists() {
        // Arrange
        Person person = new Person();
        person.setPersonName("Susana Rueda");
        person.setIdentificationNumber("1234567897");

        // Simulamos que ya existe en el repositorio
        when(personRepository.findByIdentification("1234567897"))
            .thenReturn(Optional.of(person));

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> useCase.handle(person, "hashedPassword", (short)1)
        );
        assertEquals("Identificación ya existe", exception.getMessage());
    }
    
    
}