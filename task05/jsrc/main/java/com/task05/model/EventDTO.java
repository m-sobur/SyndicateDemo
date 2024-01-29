package com.task05.model;

public class EventDTO {
    private Integer statusCode;
    private Event event;

    public EventDTO(Integer statusCode, Event event) {
        this.statusCode = statusCode;
        this.event = event;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "EventDTO{" +
                "statusCode='" + statusCode + '\'' +
                ", event=" + event +
                '}';
    }
}
