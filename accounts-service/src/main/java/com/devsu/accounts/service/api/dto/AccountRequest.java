package com.devsu.accounts.service.api.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record AccountRequest(
        UUID customerId,
        String accountNumber,
        short accountTypeId,
        BigDecimal initialBalance,
        short accountStatusId
) {}
