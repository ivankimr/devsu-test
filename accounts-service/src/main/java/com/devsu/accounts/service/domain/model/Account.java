package com.devsu.accounts.service.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public class Account {
    private UUID accountId;
    private UUID customerId;
    private String accountNumber;
    private short accountTypeId;
    private BigDecimal initialBalance;
    private short accountStatusId;
	public UUID getAccountId() {
		return accountId;
	}
	
	public void setAccountId(UUID accountId) {
		this.accountId = accountId;
	}
	
	public UUID getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(UUID customerId) {
		this.customerId = customerId;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public short getAccountTypeId() {
		return accountTypeId;
	}
	public void setAccountTypeId(short accountTypeId) {
		this.accountTypeId = accountTypeId;
	}
	
	public BigDecimal getInitialBalance() {
		return initialBalance;
	}
	
	public void setInitialBalance(BigDecimal initialBalance) {
		this.initialBalance = initialBalance;
	}
	
	public short getAccountStatusId() {
		return accountStatusId;
	}
	
	public void setAccountStatusId(short accountStatusId) {
		this.accountStatusId = accountStatusId;
	}
}
