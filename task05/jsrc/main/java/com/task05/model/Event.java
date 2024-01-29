package com.task05.model;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.UUID;

public class Event {
    private UUID id;
    private int principalId;
    private String createdAt;
    private Map<String, String> body;

    public Event(int principalId, Map<String, String> body) {
        this.id = UUID.randomUUID();
        this.createdAt = ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT);
        this.principalId = principalId;
        this.body = body;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getPrincipalId() {
        return principalId;
    }

    public void setPrincipalId(int principalId) {
        this.principalId = principalId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Map<String, String> getBody() {
        return body;
    }

    public void setBody(Map<String, String> body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", principalId=" + principalId +
                ", createdAt='" + createdAt + '\'' +
                ", body=" + body +
                '}';
    }
}
