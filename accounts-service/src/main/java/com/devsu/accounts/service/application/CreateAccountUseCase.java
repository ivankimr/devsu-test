package com.devsu.accounts.service.application;

import com.devsu.accounts.service.domain.model.Account;
import com.devsu.accounts.service.domain.repository.AccountRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class CreateAccountUseCase {
    private final AccountRepository accountRepo;
    
    public CreateAccountUseCase(AccountRepository accountRepo){ 
    	this.accountRepo = accountRepo; 
	}

    public Account handle(Account oAccount){
    	accountRepo
    		.findByAccountNumber(oAccount.getAccountNumber())
    		.ifPresent(x -> { throw new IllegalArgumentException("Número de cuenta ya existe"); });
    	
        if (oAccount.getInitialBalance() == null) 
        	oAccount.setInitialBalance(BigDecimal.ZERO);
        
        return accountRepo.save(oAccount);
    }
}
