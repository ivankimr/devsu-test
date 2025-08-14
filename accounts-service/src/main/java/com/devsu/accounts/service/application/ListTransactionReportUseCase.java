// src/main/java/com/devsu/accounts/service/application/ListMovementReportUseCase.java
package com.devsu.accounts.service.application;

import com.devsu.accounts.service.api.dto.TransactionReportResponse;
import com.devsu.accounts.service.infrastructure.jpa.entity.AccountJpaEntity;
import com.devsu.accounts.service.infrastructure.jpa.entity.AccountTransactionJpaEntity;
import com.devsu.accounts.service.infrastructure.jpa.entity.AccountTypeJpaEntity;
import com.devsu.accounts.service.infrastructure.jpa.repository.*;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.UUID;

@Service
public class ListTransactionReportUseCase {

    private final SDAccountTransactionRepository atrAccountTransaction;
    private final SDAccountRepository accountRepo;
    private final SDAccountTypeRepository accountTypeRepo;
    private final SDAccountTransactionTypeRepository accountTransactionTypeRepo;
    private final SDCustomerProjectionRepository customerRepo;

    public ListTransactionReportUseCase(
    		SDAccountTransactionRepository atrAccountTransaction,
    		SDAccountRepository accountRepo,
    		SDAccountTypeRepository accountTypeRepo,
    		SDAccountTransactionTypeRepository accountTransactionTypeRepo,
    		SDCustomerProjectionRepository customerRepo) {
        this.atrAccountTransaction = atrAccountTransaction;
        this.accountRepo = accountRepo;
        this.accountTypeRepo = accountTypeRepo;
        this.accountTransactionTypeRepo = accountTransactionTypeRepo;
        this.customerRepo = customerRepo;
    }

    @Transactional(readOnly = true)
    public List<TransactionReportResponse> handleReport(UUID customerId, LocalDate from, LocalDate to, Pageable pageable) {
        var start = from.atStartOfDay(ZoneOffset.UTC).toOffsetDateTime();
        var end   = to.plusDays(1).atStartOfDay(ZoneOffset.UTC).toOffsetDateTime();

        var accountTransactionList = atrAccountTransaction.findByAccount_CustomerIdAndTransactionDateBetweenOrderByTransactionDateAsc(customerId, start, end, pageable);
        
        if (accountTransactionList.isEmpty()) 
        	return List.of();

        var accountIds = accountTransactionList.stream().map(AccountTransactionJpaEntity::getAccountId).collect(Collectors.toSet());
        var accounts = accountRepo.findByAccountIdIn(accountIds).stream()
                .collect(Collectors.toMap(AccountJpaEntity::getAccountId, Function.identity()));

        var typeNameOperation = new ConcurrentHashMap<Short, String>();
        var signOperation = new ConcurrentHashMap<Short, Short>();

        var customerName = customerRepo.findById(customerId)
                .map(v -> v.getPersonName())
                .orElse("N/A");

        var result = new ArrayList<TransactionReportResponse>(accountTransactionList.getSize());
        
        for (var accountTransaction : accountTransactionList) {
            var acc = accounts.get(accountTransaction.getAccountId());

            var typeName = typeNameOperation.computeIfAbsent(
            	    acc.getAccountTypeId(),
            	    id -> accountTypeRepo.findById(id)
            	            .map(AccountTypeJpaEntity::getAccountTypeDescription)
            	            .orElse(null)
            	);

            var sign = signOperation.computeIfAbsent(accountTransaction.getAccountTransactionTypeId(),
                    id -> accountTransactionTypeRepo.findById(id)
                    		.map(t -> t.getBalanceOperation())
                    		.orElse((short) 0));

            var movimientoConSigno = accountTransaction.getTransactionAmount().multiply(BigDecimal.valueOf(sign));
            boolean activa = acc.getAccountStatusId() != null && acc.getAccountStatusId() == 1; 

            result.add(new TransactionReportResponse(
            		accountTransaction.getTransactionDate(),
                    customerName,
                    acc.getAccountNumber(),
                    typeName,
                    accountTransaction.getAccountBalance().subtract(movimientoConSigno),
                    activa,
                    movimientoConSigno,
                    accountTransaction.getAccountBalance()
            ));
        }
        
        return result;
    }
}
