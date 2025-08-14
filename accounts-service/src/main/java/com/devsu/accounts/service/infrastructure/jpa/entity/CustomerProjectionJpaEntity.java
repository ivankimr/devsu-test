package com.devsu.accounts.service.infrastructure.jpa.entity;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "customer_projection", schema = "devsu")
public class CustomerProjectionJpaEntity {
	@Id
    @Column(name = "customer_id", columnDefinition = "UUID")
    private UUID customerId;

    @Column(name = "person_name", nullable = false, length = 100)
    private String personName;

    @Column(name = "source_event_id", nullable = false)
    private Short sourceEventId;

    @Column(name = "source_event_type", nullable = false, length = 80)
    private String sourceEventType;

    @Column(name = "received_at", insertable = false, updatable = false)
    private OffsetDateTime receivedAt;

    public UUID getCustomerId() {
		return customerId;
	}

	public void setCustomerId(UUID customerId) {
		this.customerId = customerId;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public Short getSourceEventId() {
		return sourceEventId;
	}

	public void setSourceEventId(Short sourceEventId) {
		this.sourceEventId = sourceEventId;
	}

	public String getSourceEventType() {
		return sourceEventType;
	}

	public void setSourceEventType(String sourceEventType) {
		this.sourceEventType = sourceEventType;
	}

	public OffsetDateTime getReceivedAt() {
		return receivedAt;
	}

	public void setReceivedAt(OffsetDateTime receivedAt) {
		this.receivedAt = receivedAt;
	}
}