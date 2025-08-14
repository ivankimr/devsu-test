package com.devsu.accounts.service.api.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record AccountResponse(
		UUID accountId, 
		String accountNumber, 
		BigDecimal initialBalance
) {}