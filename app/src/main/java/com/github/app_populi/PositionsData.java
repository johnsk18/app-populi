package com.github.app_populi;

import org.json.JSONObject;

//Class to handle data for PositionFragment
public class PositionsData {
    private String issueName;
    private String issueDescription;

    //Constructor
    public PositionsData(String jsonStr) throws Exception {

        //Creates a JSON object out of JSON String argument
        JSONObject obj = new JSONObject(jsonStr);
        JSONObject jo = (JSONObject) obj;

        //Reads in data from JSON object
        this.issueName = (String) jo.get("name");
        System.out.println(this.issueName);
        this.issueDescription = ((String) jo.get("description"));
        this.issueDescription += "\n\n" + (String) jo.get("subname1");
        this.issueDescription += ":\n" + ((String) jo.get("subdescription1"));
        this.issueDescription += "\n\n" + (String) jo.get("subname2");
        this.issueDescription += ":\n" + ((String) jo.get("subdescription2"));

    }

    //Returns the Issue Name
    public String getIssueName() {
        return issueName;
    }

    //Returns the Issue Description
    public String getIssueDescription() {
        return issueDescription;
    }
}

