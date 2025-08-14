package com.devsu.accounts.service.application;

import com.devsu.accounts.service.domain.model.AccountTransaction;
import com.devsu.accounts.service.domain.repository.AccountTransactionRepository;

import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class UpdateTransactionUseCase {
	private final AccountTransactionRepository atrAccountTransaction;
	
	public UpdateTransactionUseCase(AccountTransactionRepository atrAccountTransaction) { 
		this.atrAccountTransaction = atrAccountTransaction; 
	}
	public AccountTransaction handle(UUID id, AccountTransaction oAccountTransactionDataToUpdate) {
	 var oAccountTransaction = atrAccountTransaction
			 .findById(id)
			 .orElseThrow(() -> new NoSuchElementException("Movimiento no encontrado"));
	 
	 oAccountTransaction.setAccountTransactionTypeId(oAccountTransactionDataToUpdate.getAccountTransactionTypeId());
	 oAccountTransaction.setTransactionAmount(oAccountTransactionDataToUpdate.getTransactionAmount());
	 oAccountTransaction.setTransactionDate(oAccountTransactionDataToUpdate.getTransactionDate());
	 oAccountTransaction.setAccountBalance(oAccountTransactionDataToUpdate.getAccountBalance());
	 
	 return atrAccountTransaction.save(oAccountTransaction);
	}
}
