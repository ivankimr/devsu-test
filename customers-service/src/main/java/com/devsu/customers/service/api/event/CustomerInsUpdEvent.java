package com.devsu.customers.service.api.event;

import java.time.OffsetDateTime;
import java.util.UUID;

public record CustomerInsUpdEvent(
  UUID customerId,
  UUID personId,
  String personName,
  String identificationNumber,
  short customerStatusId,
  OffsetDateTime occurredAt
) {}
