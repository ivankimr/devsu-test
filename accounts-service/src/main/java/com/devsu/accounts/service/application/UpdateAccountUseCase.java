package com.devsu.accounts.service.application;

import com.devsu.accounts.service.domain.model.Account;
import com.devsu.accounts.service.domain.repository.AccountRepository;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class UpdateAccountUseCase {
  private final AccountRepository arAccount;
  
  public UpdateAccountUseCase(AccountRepository arAccount){ 
	  this.arAccount = arAccount; 
  }

  public Account handle(UUID id, Account oAccountDataToUpdate){
    var oAccount = arAccount
    		.findById(id).orElseThrow(() -> new NoSuchElementException("Cuenta no encontrada"));
 
    oAccount.setAccountStatusId(oAccountDataToUpdate.getAccountStatusId());
    oAccount.setAccountTypeId(oAccountDataToUpdate.getAccountTypeId());

    return arAccount.save(oAccount);
  }
}