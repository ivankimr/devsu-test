package com.devsu.accounts.service.api.controller;

import com.devsu.accounts.service.api.dto.TransactionCreateRequest;
import com.devsu.accounts.service.api.dto.TransactionRequest;
import com.devsu.accounts.service.api.dto.TransactionResponse;
import com.devsu.accounts.service.application.CreateTransactionUseCase;
import com.devsu.accounts.service.application.GetTransactionUseCase;
import com.devsu.accounts.service.application.ListTransactionsUseCase;
import com.devsu.accounts.service.application.UpdateTransactionUseCase;
import com.devsu.accounts.service.domain.model.AccountTransaction;
import jakarta.validation.Valid;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movimientos")
public class TransactionController {
    private final GetTransactionUseCase transaction;
    private final ListTransactionsUseCase transactionList;
    private final CreateTransactionUseCase createTransaction;
    private final UpdateTransactionUseCase updateTransaction;    

    public TransactionController(
    		GetTransactionUseCase transaction, 
    		ListTransactionsUseCase transactionList,
            CreateTransactionUseCase createTransaction,
            UpdateTransactionUseCase updateTransaction) {
    	this.transaction = transaction;
    	this.transactionList = transactionList;
    	this.createTransaction = createTransaction;
    	this.updateTransaction = updateTransaction;
	}

    @PostMapping
    public ResponseEntity<TransactionResponse> createTransaction(@Valid @RequestBody TransactionCreateRequest oTransactionRequest){
        var oTransaction = createTransaction.handle(oTransactionRequest.accountId(), oTransactionRequest.accountTransactionTypeId(), oTransactionRequest.transactionAmount());
        
        return ResponseEntity.status(HttpStatus.CREATED).body(toTransactionResponse(oTransaction));
    }
    
    private TransactionResponse toTransactionResponse(AccountTransaction oAccountTransaction){
        return new TransactionResponse(
        		oAccountTransaction.getAccountTransactionId(), oAccountTransaction.getAccountId(), 
        		oAccountTransaction.getAccountTransactionTypeId(), oAccountTransaction.getTransactionDate(), 
        		oAccountTransaction.getTransactionAmount(), oAccountTransaction.getAccountBalance()
        );
    }
    
    @GetMapping("/cuenta/{accountId}") 
    public Page<AccountTransaction>  getTransactionListByAccountId(@PathVariable UUID accountId,  Pageable pageable){
    	return transactionList.handle(accountId, pageable);
	}
    
    @GetMapping("/{id}") 
    public AccountTransaction getTransaction(@PathVariable UUID id){ 
    	return transaction.handle(id); 
	}

    @PutMapping("/{id}")
    public AccountTransaction updateTransaction(@PathVariable UUID id, @RequestBody TransactionRequest oTransactionRequest) {
      var oAccountTransaction = new AccountTransaction();
      
      oAccountTransaction.setAccountTransactionTypeId(oTransactionRequest.accountTransactionTypeId());
      oAccountTransaction.setTransactionAmount(oTransactionRequest.transactionAmount());
      oAccountTransaction.setTransactionDate(oTransactionRequest.transactionDate());
      oAccountTransaction.setAccountBalance(oTransactionRequest.accountBalance());
      
      return updateTransaction.handle(id, oAccountTransaction);
    }    
}
