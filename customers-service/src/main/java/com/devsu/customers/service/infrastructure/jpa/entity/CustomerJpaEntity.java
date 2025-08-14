package com.devsu.customers.service.infrastructure.jpa.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "customer", schema = "devsu")
public class CustomerJpaEntity {
    @Id @GeneratedValue
    @Column(name = "customer_id", columnDefinition = "UUID")
    private UUID customerId;

    @Column(name = "person_id", nullable = false, columnDefinition = "UUID")
    private UUID personId;

    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;

    @Column(name = "customer_status_id", nullable = false)
    private Short customerStatusId;

	public UUID getCustomerId() {
		return customerId;
	}

	public void setCustomerId(UUID customerId) {
		this.customerId = customerId;
	}

	public UUID getPersonId() {
		return personId;
	}

	public void setPersonId(UUID personId) {
		this.personId = personId;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public Short getCustomerStatusId() {
		return customerStatusId;
	}

	public void setCustomerStatusId(Short customerStatusId) {
		this.customerStatusId = customerStatusId;
	}
}
