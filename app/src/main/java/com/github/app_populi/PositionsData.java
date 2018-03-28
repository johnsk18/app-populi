package com.github.app_populi;

import java.io.*;
import java.util.Iterator;
import java.util.Map;
import android.content.Context;
import android.app.Activity;

import android.content.res.AssetManager;
import org.json.JSONArray;
import org.json.JSONObject;


public class PositionsData {
    private String issueName;
    private String issueDescription;

    public PositionsData(String jsonStr) throws Exception {
        JSONObject obj = new JSONObject(jsonStr);

        JSONObject jo = (JSONObject) obj;

        this.issueName = (String) jo.get("name");
        System.out.println(this.issueName);
        this.issueDescription = ((String) jo.get("description"));
        this.issueDescription += "\n\n" + (String) jo.get("subname1");
        this.issueDescription += ":\n" + ((String) jo.get("subdescription1"));
        this.issueDescription += "\n\n" + (String) jo.get("subname2");
        this.issueDescription += ":\n" + ((String) jo.get("subdescription2"));

    }

    public String getIssueName() {
        return issueName;
    }

    public String getIssueDescription() {
        return issueDescription;
    }
}

