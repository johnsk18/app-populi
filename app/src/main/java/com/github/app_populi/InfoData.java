package com.github.app_populi;

import java.util.ArrayList;
import org.json.JSONObject;
/**
 * Created by perriadams on 4/18/18.
 */

//Class to handle the data for InfoFragment
public class InfoData {

    //Class variables
    JSONObject jo; //JSON Object for parsing JSON
    ArrayList<String> emailList; //List of emails

    //Constructor
    public InfoData() throws Exception {
        //Initialize email list
        emailList = new ArrayList<String>();

        //Addresses
        String jsonStr = "{\n \"12209\": \"4443 Bertha St, Albany, NY 12209\",\n  \"12180\": \"158 3rd St, Troy, NY 12180\",\n \"12206\": \"20 Robin St, Albany, NY 12206\",\n \"12189\": \"775 Clinton St, Watervliet, NY\"\n}";

        //Convert to JSON object
        JSONObject obj = new JSONObject(jsonStr);
        jo = (JSONObject) obj;

    }

    //Takes a zipcode as a String
    //Returns an address as a String
    public String getAddress(String zip) throws Exception {
        String address = (String) jo.get(zip);
        System.out.println(address);
        return address;
    }

    //Adds an email to emailList
    public void addEmail(String email){
        emailList.add(email);
        //send emails to server
    }




}
