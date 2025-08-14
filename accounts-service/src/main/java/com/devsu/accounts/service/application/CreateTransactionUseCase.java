package com.devsu.accounts.service.application;

import com.devsu.accounts.service.api.exception.BusinessException;
import com.devsu.accounts.service.api.exception.NotFoundException;
import com.devsu.accounts.service.domain.model.AccountTransaction;
import com.devsu.accounts.service.domain.repository.AccountRepository;
import com.devsu.accounts.service.domain.repository.AccountTransactionRepository;
import com.devsu.accounts.service.domain.repository.AccountTransactionTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class CreateTransactionUseCase {
    private final AccountRepository arAccount;
    private final AccountTransactionRepository atrAccountTransaction;
    private final AccountTransactionTypeRepository attrAccountTransactionType;

    public CreateTransactionUseCase(AccountRepository arAccount,
                                    AccountTransactionRepository atrAccountTransaction,
                                    AccountTransactionTypeRepository attrAccountTransactionType) {
        this.arAccount = arAccount; 
        this.atrAccountTransaction = atrAccountTransaction; 
        this.attrAccountTransactionType = attrAccountTransactionType;
    }

    @Transactional
    public AccountTransaction handle(UUID accountId, short typeId, BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException("El monto debe ser positivo");
        }

        var account = arAccount.findById(accountId)
                .orElseThrow(() -> new NotFoundException("Cuenta no encontrada"));

        var lastTransaction = atrAccountTransaction.findLastByAccountId(accountId)
                .map(AccountTransaction::getAccountBalance)
                .orElse(account.getInitialBalance());

        short operationSign = attrAccountTransactionType.getBalanceOperation(typeId);
        var newAmount = amount.multiply(BigDecimal.valueOf(operationSign));

        var balanceAfterTransaction = lastTransaction.add(newAmount);
        
        if (balanceAfterTransaction.compareTo(BigDecimal.ZERO) < 0) {
            throw new BusinessException("Saldo no disponible");
        }

        var oAccountTransaction = new AccountTransaction();
        
        oAccountTransaction.setAccountId(accountId);
        oAccountTransaction.setAccountTransactionTypeId(typeId);
        oAccountTransaction.setTransactionAmount(amount);
        oAccountTransaction.setAccountBalance(balanceAfterTransaction);
        oAccountTransaction.setTransactionDate(null);

        return atrAccountTransaction.save(oAccountTransaction);
    }
}
