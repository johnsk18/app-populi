package com.github.app_populi;

import java.util.Date;

public class PositionsData {
    private String eventName;
    private String eventDescription;
    private Date eventDate;

    public PositionsData(String eventName, String eventDescription, Date eventDate){
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventDate = eventDate;
    };

    public String getEventName() {
        return eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public Date getEventDate() {
        return eventDate;
    }
}
