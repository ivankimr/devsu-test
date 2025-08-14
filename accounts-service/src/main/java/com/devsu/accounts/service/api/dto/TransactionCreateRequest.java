package com.devsu.accounts.service.api.dto;

import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.UUID;

public record TransactionCreateRequest(
        UUID accountId,
        short accountTransactionTypeId,
        @Positive BigDecimal transactionAmount
) {}