package com.devsu.accounts.service.application;

import com.devsu.accounts.service.domain.model.AccountTransaction;
import com.devsu.accounts.service.domain.repository.AccountTransactionRepository;

import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class GetTransactionUseCase {
	private final AccountTransactionRepository atrAccountTransaction;
	
	public GetTransactionUseCase(AccountTransactionRepository atrAccountTransaction){ 
		this.atrAccountTransaction = atrAccountTransaction; 
	}
	
	public AccountTransaction handle(UUID id){
		return atrAccountTransaction
				.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Movimiento no encontrado"));
	}
}