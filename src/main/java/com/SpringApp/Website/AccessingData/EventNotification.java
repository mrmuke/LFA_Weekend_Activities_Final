package com.SpringApp.Website.AccessingData;

public class EventNotification {
    private String message;
    private ScheduleEvent event;

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
