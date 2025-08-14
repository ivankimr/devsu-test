// src/main/java/com/devsu/accounts/service/api/dto/TransactionResponse.java
package com.devsu.accounts.service.api.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record TransactionResponse(
        UUID accountTransactionId,
        UUID accountId,
        short accountTransactionTypeId,
        OffsetDateTime transactionDate,
        BigDecimal transactionAmount,
        BigDecimal accountBalance
) {}
