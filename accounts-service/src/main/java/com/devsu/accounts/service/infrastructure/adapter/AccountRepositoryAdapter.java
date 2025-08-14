package com.devsu.accounts.service.infrastructure.adapter;

import com.devsu.accounts.service.domain.model.Account;
import com.devsu.accounts.service.domain.repository.AccountRepository;
import com.devsu.accounts.service.infrastructure.jpa.entity.AccountJpaEntity;
import com.devsu.accounts.service.infrastructure.jpa.repository.SDAccountRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;
import java.util.UUID;

@Repository
public class AccountRepositoryAdapter implements AccountRepository {
    private final SDAccountRepository accountRepo;
    
    public AccountRepositoryAdapter(SDAccountRepository accountRepo){ 
    	this.accountRepo = accountRepo; 
	}
    
    @Override
    public Page<Account> findAll(Pageable pageable){
      return accountRepo.findAll(pageable).map(m -> {
        var oAccount = new Account();
        
        oAccount.setAccountId(m.getAccountId());
        oAccount.setCustomerId(m.getCustomerId());
        oAccount.setAccountNumber(m.getAccountNumber());
        oAccount.setAccountTypeId(m.getAccountTypeId());
        oAccount.setInitialBalance(m.getInitialBalance());
        oAccount.setAccountStatusId(m.getAccountStatusId());
        
        return oAccount;
      });
    }
    

    @Override
    public Account save(Account oAccount) {
        AccountJpaEntity jeAccount = new AccountJpaEntity();
        
        jeAccount.setAccountId(oAccount.getAccountId());
        jeAccount.setCustomerId(oAccount.getCustomerId());
        jeAccount.setAccountNumber(oAccount.getAccountNumber());
        jeAccount.setAccountTypeId(oAccount.getAccountTypeId());
        jeAccount.setInitialBalance(oAccount.getInitialBalance());
        jeAccount.setAccountStatusId(oAccount.getAccountStatusId());
        
        jeAccount = accountRepo.save(jeAccount);
        
        oAccount.setAccountId(jeAccount.getAccountId());
        
        return oAccount;
    }

    @Override
    public Optional<Account> findById(UUID id) {
        return accountRepo.findById(id).map(m -> {
            Account oAccount = new Account();
            
            oAccount.setAccountId(m.getAccountId());
            oAccount.setCustomerId(m.getCustomerId());
            oAccount.setAccountNumber(m.getAccountNumber());
            oAccount.setAccountTypeId(m.getAccountTypeId());
            oAccount.setInitialBalance(m.getInitialBalance());
            oAccount.setAccountStatusId(m.getAccountStatusId());
            
            return oAccount;
        });
    }

    @Override
    public Optional<Account> findByAccountNumber(String num) {
        return accountRepo.findByAccountNumber(num).map(m -> {
            Account oAccount = new Account();
            
            oAccount.setAccountId(m.getAccountId());
            oAccount.setCustomerId(m.getCustomerId());
            oAccount.setAccountNumber(m.getAccountNumber());
            oAccount.setAccountTypeId(m.getAccountTypeId());
            oAccount.setInitialBalance(m.getInitialBalance());
            oAccount.setAccountStatusId(m.getAccountStatusId());
            
            return oAccount;
        });
    }
}
