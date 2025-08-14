package com.devsu.customers.service.api.event;

import java.time.OffsetDateTime;
import java.util.UUID;

public record CustomerDeletedEvent(UUID customerId, OffsetDateTime occurredAt) {}