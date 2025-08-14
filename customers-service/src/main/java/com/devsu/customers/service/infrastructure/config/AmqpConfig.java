package com.devsu.customers.service.infrastructure.config;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {
	public static final String EXCHANGE = "customers.events";

	@Bean
	TopicExchange customersExchange() {
		return new TopicExchange(EXCHANGE, true, false);
	}

	@Bean
	MessageConverter jacksonConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	RabbitTemplate rabbitTemplate(ConnectionFactory cf, MessageConverter c) {
		var t = new RabbitTemplate(cf);
		t.setMessageConverter(c);
		return t;
	}
}
