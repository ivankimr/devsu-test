package com.devsu.customers.service.api.dto;

public record CustomerRequest(
        String personName,
        short genderId,
        short age,
        short identificationTypeId,
        String identificationNumber,
        String address,
        String phone,
        String passwordHash,
        short customerStatusId
) {}
