package com.devsu.accounts.service.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public class AccountTransaction {
    private UUID accountTransactionId;
    private UUID accountId;
    private short accountTransactionTypeId;
    private OffsetDateTime transactionDate;
    private BigDecimal transactionAmount;
    private BigDecimal accountBalance;
    
	public UUID getAccountTransactionId() {
		return accountTransactionId;
	}
	
	public void setAccountTransactionId(UUID accountTransactionId) {
		this.accountTransactionId = accountTransactionId;
	}
	
	public UUID getAccountId() {
		return accountId;
	}
	
	public void setAccountId(UUID accountId) {
		this.accountId = accountId;
	}
	
	public short getAccountTransactionTypeId() {
		return accountTransactionTypeId;
	}
	
	public void setAccountTransactionTypeId(short accountTransactionTypeId) {
		this.accountTransactionTypeId = accountTransactionTypeId;
	}
	
	public OffsetDateTime getTransactionDate() {
		return transactionDate;
	}
	
	public void setTransactionDate(OffsetDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	public BigDecimal getTransactionAmount() {
		return transactionAmount;
	}
	
	public void setTransactionAmount(BigDecimal transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	
	public BigDecimal getAccountBalance() {
		return accountBalance;
	}
	
	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}
}
