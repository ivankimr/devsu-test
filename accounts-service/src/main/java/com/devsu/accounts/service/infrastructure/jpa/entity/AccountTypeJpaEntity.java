package com.devsu.accounts.service.infrastructure.jpa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "account_type", schema = "devsu")
public class AccountTypeJpaEntity {

	@Id
    @Column(name = "account_type_id")
    private Short accountTypeId;

    @Column(name = "account_type_description", nullable = false, length = 20)
    private String accountTypeDescription;
    
    public Short getAccountTypeId() {
		return accountTypeId;
	}

	public void setAccountTypeId(Short accountTypeId) {
		this.accountTypeId = accountTypeId;
	}

	public String getAccountTypeDescription() {
		return accountTypeDescription;
	}

	public void setAccountTypeDescription(String accountTypeDescription) {
		this.accountTypeDescription = accountTypeDescription;
	}
}
