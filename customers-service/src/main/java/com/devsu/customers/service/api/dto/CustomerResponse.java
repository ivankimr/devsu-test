package com.devsu.customers.service.api.dto;

import java.util.UUID;
public record CustomerResponse(UUID customerId, UUID personId, String personName, String identificationNumber) {}
