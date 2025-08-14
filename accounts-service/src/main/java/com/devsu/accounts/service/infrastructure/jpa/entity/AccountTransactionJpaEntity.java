package com.devsu.accounts.service.infrastructure.jpa.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "account_transaction", schema = "devsu")
public class AccountTransactionJpaEntity {
    @Id @GeneratedValue
    @Column(name = "account_transaction_id", columnDefinition = "UUID")
    private UUID accountTransactionId;
    
    @Column(name = "account_id", nullable = false, columnDefinition = "UUID")
    private UUID accountId;

    @Column(name = "account_transaction_type_id", nullable = false)
    private Short accountTransactionTypeId;

    @Column(name = "transaction_date", insertable = false, updatable = false)
    private OffsetDateTime transactionDate;

    @Column(name = "transaction_amount", precision = 18, scale = 2, nullable = false)
    private BigDecimal transactionAmount;

    @Column(name = "account_balance", precision = 18, scale = 2, nullable = false)
    private BigDecimal accountBalance;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", insertable = false, updatable = false)
    private AccountJpaEntity account;    

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

	public Short getAccountTransactionTypeId() {
		return accountTransactionTypeId;
	}

	public void setAccountTransactionTypeId(Short accountTransactionTypeId) {
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
