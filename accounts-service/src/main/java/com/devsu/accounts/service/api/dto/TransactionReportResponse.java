package com.devsu.accounts.service.api.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record TransactionReportResponse (
		@com.fasterxml.jackson.annotation.JsonFormat(shape = com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
		OffsetDateTime fecha,
		String cliente,
		String numeroCuenta,
		String tipoCuenta,
		BigDecimal saldoInicial,
		boolean estado,
		BigDecimal movimiento,
		BigDecimal saldoDisponible
) {}
