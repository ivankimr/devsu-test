package com.devsu.accounts.service.application;

import com.devsu.accounts.service.domain.model.Account;
import com.devsu.accounts.service.domain.repository.AccountRepository;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class GetAccountUseCase {
  private final AccountRepository arAccount;
  
  public GetAccountUseCase(AccountRepository arAccount){ 
	  this.arAccount = arAccount; 
  }
  
  public Account handle(UUID id){
    return arAccount
    		.findById(id)
    		.orElseThrow(() -> new NoSuchElementException("Cuenta no encontrada"));
  }
}