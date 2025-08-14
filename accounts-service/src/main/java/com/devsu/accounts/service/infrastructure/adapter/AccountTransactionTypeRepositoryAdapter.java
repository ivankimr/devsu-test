package com.devsu.accounts.service.infrastructure.adapter;

import com.devsu.accounts.service.api.exception.NotFoundException;
import com.devsu.accounts.service.domain.repository.AccountTransactionTypeRepository;
import com.devsu.accounts.service.infrastructure.jpa.repository.SDAccountTransactionTypeRepository;
import org.springframework.stereotype.Repository;

@Repository
public class AccountTransactionTypeRepositoryAdapter implements AccountTransactionTypeRepository {
    private final SDAccountTransactionTypeRepository AccountTransactionTypeRepo;

    public AccountTransactionTypeRepositoryAdapter(SDAccountTransactionTypeRepository AccountTransactionTypeRepo) {
        this.AccountTransactionTypeRepo = AccountTransactionTypeRepo;
    }

    @Override
    public short getBalanceOperation(short typeId) {
        return AccountTransactionTypeRepo.findById(typeId)
                .map(e -> e.getBalanceOperation())
                .orElseThrow(() -> new NotFoundException("Tipo de movimiento no existe"));
    }
}
