package com.github.app_populi;

import java.util.Date;

//Class to handle data for EventFragment
public class EventData {
    //Class Variables
    private String eventName;
    private String eventDescription;
    private Date eventDate;

    //Constructor
    public EventData(String eventName, String eventDescription, Date eventDate){
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventDate = eventDate;
    };

    //Returns Event Name
    public String getEventName() {
        return eventName;
    }

    //Returns Event Description
    public String getEventDescription() {
        return eventDescription;
    }

    //Returns Event Date
    public Date getEventDate() {
        return eventDate;
    }
}
