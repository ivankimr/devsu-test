package com.devsu.accounts.service.domain.repository;

import com.devsu.accounts.service.domain.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository {
	Page<Account> findAll(Pageable pageable);
    Optional<Account> findById(UUID id);
    Optional<Account> findByAccountNumber(String accountNumber);	 
    Account save(Account account);
}
