package com.devsu.accounts.service.infrastructure.jpa.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "account", schema = "devsu")
public class AccountJpaEntity {
    @Id @GeneratedValue
    @Column(name = "account_id", columnDefinition = "UUID")
    private UUID accountId;

    @Column(name = "customer_id", nullable = false, columnDefinition = "UUID")
    private UUID customerId;

    @Column(name = "account_number", nullable = false, length = 34, unique = true)
    private String accountNumber;

    @Column(name = "account_type_id", nullable = false)
    private Short accountTypeId;

    @Column(name = "initial_balance", precision = 18, scale = 2, nullable = false)
    private BigDecimal initialBalance;

    @Column(name = "account_status_id", nullable = false)
    private Short accountStatusId;

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

	public Short getAccountTypeId() {
		return accountTypeId;
	}

	public void setAccountTypeId(Short accountTypeId) {
		this.accountTypeId = accountTypeId;
	}

	public BigDecimal getInitialBalance() {
		return initialBalance;
	}

	public void setInitialBalance(BigDecimal initialBalance) {
		this.initialBalance = initialBalance;
	}

	public Short getAccountStatusId() {
		return accountStatusId;
	}

	public void setAccountStatusId(Short accountStatusId) {
		this.accountStatusId = accountStatusId;
	}
}
