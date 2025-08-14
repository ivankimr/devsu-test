package com.devsu.customers.service.infrastructure.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import com.devsu.customers.service.api.event.CustomerDeletedEvent;
import com.devsu.customers.service.api.event.CustomerInsUpdEvent;
import com.devsu.customers.service.infrastructure.config.AmqpConfig;

import lombok.RequiredArgsConstructor;

@Component
public class CustomerEventsPublisher {
	private final RabbitTemplate rt;

	public CustomerEventsPublisher(RabbitTemplate rt) {
		this.rt = rt;
	}

	public void publishInsUpd(CustomerInsUpdEvent e) {
		rt.convertAndSend(AmqpConfig.EXCHANGE, "customer.insupd", e);
	}

	public void publishDeleted(CustomerDeletedEvent e) {
		rt.convertAndSend(AmqpConfig.EXCHANGE, "customer.deleted", e);
	}
}
