package com.devsu.accounts.service.api.controller;

import com.devsu.accounts.service.api.dto.TransactionReportResponse;
import com.devsu.accounts.service.application.ListTransactionReportUseCase;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reportes")

public class TransactionReportController  {
    private final ListTransactionReportUseCase transactionList;

    public TransactionReportController(
    		ListTransactionReportUseCase transactionList) {
    	this.transactionList = transactionList;
	}

    @GetMapping
    public List<TransactionReportResponse> getTransactionListByClienteAndDateRange(
            @RequestParam UUID customerId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,  
            Pageable pageable) {

        return transactionList.handleReport(customerId, from, to, pageable)
                .stream()
                .map(this::toTransactionResponse)
                .toList();
    }     
    
    private TransactionReportResponse toTransactionResponse(TransactionReportResponse oAccountTransactionReport){
        return new TransactionReportResponse(
        		oAccountTransactionReport.fecha(), 
        		oAccountTransactionReport.cliente(), 
        		oAccountTransactionReport.numeroCuenta(), 
        		oAccountTransactionReport.tipoCuenta(), 
        		oAccountTransactionReport.saldoInicial(), 
        		oAccountTransactionReport.estado(), 
        		oAccountTransactionReport.movimiento(), 
        		oAccountTransactionReport.saldoDisponible()
        );
    }    
}
