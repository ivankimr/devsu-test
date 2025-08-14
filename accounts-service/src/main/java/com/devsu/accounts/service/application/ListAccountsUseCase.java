package com.devsu.accounts.service.application;

import com.devsu.accounts.service.domain.model.Account;
import com.devsu.accounts.service.domain.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class ListAccountsUseCase {
	private final AccountRepository arAccountRepository;
	
	public ListAccountsUseCase(AccountRepository arAccountRepository){ 
		this.arAccountRepository = arAccountRepository; 
	}
	
	public Page<Account> handle(Pageable pageable){ 
		return arAccountRepository.findAll(pageable); 
	}
}
