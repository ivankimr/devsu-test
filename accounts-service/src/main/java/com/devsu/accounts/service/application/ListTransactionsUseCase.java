package com.devsu.accounts.service.application;

import com.devsu.accounts.service.domain.model.AccountTransaction;
import com.devsu.accounts.service.domain.repository.AccountTransactionRepository;

import org.springframework.stereotype.Service;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class ListTransactionsUseCase {
	private final AccountTransactionRepository atrAccountTransaction;
	
	public ListTransactionsUseCase(AccountTransactionRepository atrAccountTransaction){ 
		this.atrAccountTransaction = atrAccountTransaction; 
	}
	
	public Page<AccountTransaction> handle(UUID accountId, Pageable pageable){
	    return atrAccountTransaction.findByAccountId(accountId, pageable);
	}
}