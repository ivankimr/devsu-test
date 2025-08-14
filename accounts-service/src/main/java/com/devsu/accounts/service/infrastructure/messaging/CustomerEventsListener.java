package com.devsu.accounts.service.infrastructure.messaging;

import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.devsu.accounts.service.infrastructure.config.AmqpConfig;
import com.devsu.accounts.service.infrastructure.jpa.entity.CustomerProjectionJpaEntity;
import com.devsu.accounts.service.infrastructure.jpa.repository.SDCustomerProjectionRepository;

@Component
public class CustomerEventsListener {
	private final SDCustomerProjectionRepository customerProjectionRepo;

	public CustomerEventsListener(SDCustomerProjectionRepository customerProjectionRepo) {
		this.customerProjectionRepo = customerProjectionRepo;
	}

	public record CustomerInsUpdEventUUID(
			UUID customerId, UUID personId, String personName,
			String identificationNumber, short customerStatusId, 
			OffsetDateTime occurredAt) {
	}

	public record CustomerDeletedEvent(
			UUID customerId, 
			OffsetDateTime occurredAt) {
	}

	@RabbitListener(queues = AmqpConfig.QUEUE)
	public void onInsUpd(CustomerInsUpdEventUUID e, 
			@Header(AmqpHeaders.RECEIVED_ROUTING_KEY) String routingKey) {
		var entity = new CustomerProjectionJpaEntity();
		entity.setCustomerId(e.customerId());
		entity.setPersonName(e.personName());
		entity.setSourceEventId((short) 1);
		entity.setSourceEventType(routingKey);
		entity.setReceivedAt(OffsetDateTime.now());

		customerProjectionRepo.save(entity);
	}

	@RabbitListener(queues = AmqpConfig.QUEUE)
	public void onDeleted(CustomerDeletedEvent e) {
		customerProjectionRepo.deleteById(e.customerId());
	}
}
