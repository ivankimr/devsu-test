package com.devsu.accounts.service.domain.repository;

import com.devsu.accounts.service.domain.model.AccountTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface AccountTransactionRepository {
	Page<AccountTransaction> findByAccountId(UUID idAccount, Pageable pageable);
	Optional<AccountTransaction> findLastByAccountId(UUID accountId);
	Optional<AccountTransaction> findById(UUID id);
    AccountTransaction save(AccountTransaction oAccountTransaction);
//    Page<AccountTransaction> findByCustomerAndDateRange(UUID customerId, OffsetDateTime from, OffsetDateTime to, Pageable pageable);
}
