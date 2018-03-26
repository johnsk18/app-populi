package com.github.app_populi;

import java.util.Date;

public class PositionsData {
    private String issueName;
    private String issueDescription;

    public PositionsData(String eventName, String eventDescription, Date eventDate){
        this.issueName = issueName;
        this.issueDescription = issueDescription;
    };

    public String getEventName() {
        return issueName;
    }

    public String getEventDescription() {
        return issueDescription;
    }

}
