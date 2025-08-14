package com.devsu.accounts.service.api.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record TransactionRequest(
        UUID accountId,
        short accountTransactionTypeId,
        BigDecimal transactionAmount,
        BigDecimal accountBalance,          
        OffsetDateTime transactionDate
) {}
