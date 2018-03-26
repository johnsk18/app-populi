package com.github.app_populi;

public class PositionsData {
    private String issueName;
    private String issueDescription;

    public PositionsData(String issueName, String issueDescription){
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
