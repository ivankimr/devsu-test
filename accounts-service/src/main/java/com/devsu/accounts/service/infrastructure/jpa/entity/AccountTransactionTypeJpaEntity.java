package com.devsu.accounts.service.infrastructure.jpa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "account_transaction_type")
public class AccountTransactionTypeJpaEntity {

    @Id
    @Column(name = "account_transaction_type_id")
    private Short id;

	@Column(name = "account_transaction_type_description")
    private String description;

    @Column(name = "balance_operation", nullable = false)
    private Short balanceOperation;
    
    public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Short getBalanceOperation() {
		return balanceOperation;
	}

	public void setBalanceOperation(Short balanceOperation) {
		this.balanceOperation = balanceOperation;
	}    
}