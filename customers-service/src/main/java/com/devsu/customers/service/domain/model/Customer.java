package com.devsu.customers.service.domain.model;

import java.util.UUID;

public class Customer {
	private UUID customerId;
    private UUID personId;
    private String passwordHash;
    private short customerStatusId;
    
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
	
	public short getCustomerStatusId() {
		return customerStatusId;
	}
	
	public void setCustomerStatusId(short customerStatusId) {
		this.customerStatusId = customerStatusId;
	}
}
