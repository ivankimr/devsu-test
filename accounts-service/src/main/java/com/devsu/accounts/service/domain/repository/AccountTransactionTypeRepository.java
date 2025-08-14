package com.devsu.accounts.service.domain.repository;

public interface AccountTransactionTypeRepository {
    short getBalanceOperation(short typeId);
}
