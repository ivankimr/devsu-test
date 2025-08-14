// src/main/java/com/devsu/accounts/service/infrastructure/adapter/AccountTransactionRepositoryAdapter.java
package com.devsu.accounts.service.infrastructure.adapter;

import com.devsu.accounts.service.domain.model.AccountTransaction;
import com.devsu.accounts.service.domain.repository.AccountTransactionRepository;
import com.devsu.accounts.service.infrastructure.jpa.entity.AccountTransactionJpaEntity;
import com.devsu.accounts.service.infrastructure.jpa.repository.SDAccountTransactionRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;
import java.util.UUID;

@Repository
public class AccountTransactionRepositoryAdapter implements AccountTransactionRepository {
    private final SDAccountTransactionRepository accountTransactionRepo;

    public AccountTransactionRepositoryAdapter(SDAccountTransactionRepository accountTransactionRepo) { 
    	this.accountTransactionRepo = accountTransactionRepo; 
	}
    
    @Override
    public Page<AccountTransaction> findByAccountId(UUID idAccount, Pageable pageable){
    	var page = accountTransactionRepo.findByAccountId(idAccount, pageable);   
        
    	return page.map(this::toAccountTransaction);
    }
    
    @Override
    public Optional<AccountTransaction> findLastByAccountId(UUID accountId){
        var page = accountTransactionRepo.findByAccountId(
                accountId,
                PageRequest.of(0, 1, Sort.by(
                        Sort.Order.desc("transactionDate")
                ))
        );
        
        return page.getContent().stream().findFirst().map(this::toAccountTransaction);
    }    
    
    private AccountTransaction toAccountTransaction(AccountTransactionJpaEntity jeAccountTransaction){
        var oAccountTransaction = new AccountTransaction();
        
        oAccountTransaction.setAccountTransactionId(jeAccountTransaction.getAccountTransactionId());
        oAccountTransaction.setAccountId(jeAccountTransaction.getAccountId());
        oAccountTransaction.setAccountTransactionTypeId(jeAccountTransaction.getAccountTransactionTypeId());
        oAccountTransaction.setTransactionDate(jeAccountTransaction.getTransactionDate());
        oAccountTransaction.setTransactionAmount(jeAccountTransaction.getTransactionAmount());
        oAccountTransaction.setAccountBalance(jeAccountTransaction.getAccountBalance());
        
        return oAccountTransaction;
    }    

    @Override
    public AccountTransaction save(AccountTransaction oAccountTransaction) {
        AccountTransactionJpaEntity jeAccountTransaction = new AccountTransactionJpaEntity();
        
        jeAccountTransaction.setAccountTransactionId(oAccountTransaction.getAccountTransactionId());
        jeAccountTransaction.setAccountId(oAccountTransaction.getAccountId());
        jeAccountTransaction.setAccountTransactionTypeId(oAccountTransaction.getAccountTransactionTypeId());
        jeAccountTransaction.setTransactionDate(oAccountTransaction.getTransactionDate());
        jeAccountTransaction.setTransactionAmount(oAccountTransaction.getTransactionAmount());
        jeAccountTransaction.setAccountBalance(oAccountTransaction.getAccountBalance());
        
        jeAccountTransaction = accountTransactionRepo.save(jeAccountTransaction);
        
        oAccountTransaction.setAccountTransactionId(jeAccountTransaction.getAccountTransactionId());
        
        return oAccountTransaction;
    }

    @Override
    public Optional<AccountTransaction> findById(UUID id) {
        return accountTransactionRepo.findById(id).map(m -> {
            AccountTransaction oAccountTransaction = new AccountTransaction();
            
            oAccountTransaction.setAccountTransactionId(m.getAccountTransactionId());
            oAccountTransaction.setAccountId(m.getAccountId());
            oAccountTransaction.setAccountTransactionTypeId(m.getAccountTransactionTypeId());
            oAccountTransaction.setTransactionDate(m.getTransactionDate());
            oAccountTransaction.setTransactionAmount(m.getTransactionAmount());
            oAccountTransaction.setAccountBalance(m.getAccountBalance());
            
            return oAccountTransaction;
        });
    }
}