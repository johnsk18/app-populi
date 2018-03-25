package com.github.app_populi;

public class EventData {
    private String eventName;
    private String eventDescription;

    public EventData(String eventName, String eventDescription){
        this.eventName = eventName;
        this.eventDescription = eventDescription;
    };

    public String getEventName() {
        return eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }
}
