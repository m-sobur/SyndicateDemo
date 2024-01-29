package com.task05.model;

import java.util.Map;

public class EventRequestFromAPIGateway {
    private Integer principalId;
    private Map<String, String> content;

    public EventRequestFromAPIGateway(Integer principalId, Map<String, String> content) {
        this.principalId = principalId;
        this.content = content;
    }

    public Map<String, String> getContent() {
        return content;
    }

    public void setContent(Map<String, String> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "EventRequsetFromAPIGateway{" +
                "principalId=" + principalId +
                ", content=" + content +
                '}';
    }

    public Integer getPrincipalId() {
        return principalId;
    }

    public void setPrincipalId(Integer principalId) {
        this.principalId = principalId;
    }
}
