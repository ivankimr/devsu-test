package com.devsu.accounts.service.api.controller;

import com.devsu.accounts.service.api.dto.AccountRequest;
import com.devsu.accounts.service.api.dto.AccountResponse;
import com.devsu.accounts.service.application.CreateAccountUseCase;
import com.devsu.accounts.service.application.GetAccountUseCase;
import com.devsu.accounts.service.application.ListAccountsUseCase;
import com.devsu.accounts.service.application.UpdateAccountUseCase;
import com.devsu.accounts.service.domain.model.Account;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cuentas")
public class AccountController {
    private final CreateAccountUseCase createAccount;
    private final GetAccountUseCase account;
    private final ListAccountsUseCase accountList;
    private final UpdateAccountUseCase updateAccount;    

    public AccountController(
    		CreateAccountUseCase createAccount, 
    		GetAccountUseCase account,
    		ListAccountsUseCase accountList, 
    		UpdateAccountUseCase updateAccount) { 
    	this.createAccount = createAccount; 
    	this.account = account;
    	this.accountList = accountList;
    	this.updateAccount = updateAccount;
	}

    @PostMapping
    public ResponseEntity<AccountResponse> create(@RequestBody AccountRequest oAccountRequest){
        Account oAccount = new Account();
        
        oAccount.setCustomerId(oAccountRequest.customerId());
        oAccount.setAccountNumber(oAccountRequest.accountNumber());
        oAccount.setAccountTypeId(oAccountRequest.accountTypeId());
        oAccount.setInitialBalance(oAccountRequest.initialBalance());
        oAccount.setAccountStatusId(oAccountRequest.accountStatusId());
        
        oAccount = createAccount.handle(oAccount);
        
        return ResponseEntity
        		.status(HttpStatus.CREATED)
        		.body(new AccountResponse(
        				oAccount.getAccountId(), 
        				oAccount.getAccountNumber(), 
        				oAccount.getInitialBalance()
    				)
				);
    }
    
    @GetMapping 
    public Page<Account> getAccountList(Pageable pageable){ 
    	return accountList.handle(pageable); 
	}
    
    @GetMapping("/{id}") 
    public Account getACcount(@PathVariable UUID id){ 
    	return account.handle(id); 
	}

    @PutMapping("/{id}")
    public Account updateAccount(@PathVariable UUID id, @RequestBody AccountRequest oAccountRequest){
      var oAccount = new Account();
      
      oAccount.setAccountStatusId(oAccountRequest.accountStatusId());
      oAccount.setAccountTypeId(oAccountRequest.accountTypeId());
 
      return updateAccount.handle(id, oAccount);
    }    
}
