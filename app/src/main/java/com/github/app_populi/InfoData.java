package com.github.app_populi;

import java.util.ArrayList;
import org.json.JSONObject;
/**
 * Created by perriadams on 4/18/18.
 */


public class InfoData {
    JSONObject jo;
    ArrayList<String> emailList;

    public InfoData() throws Exception {


        String jsonStr = "{\n \"95014\": \"1 Infinite Loop, Cupertino, California, 95014\",\n  \"98115\": \"7715 Latona Ave NE Seattle WA, 98115\",\n \"12180\": \"2427 21ST ST Troy, NY, 12180\",\n \"20500\": \"1600 Pennsylvania Ave NW, Washington, DC\"\n}";
        JSONObject obj = new JSONObject(jsonStr);
        jo = (JSONObject) obj;
        emailList = new ArrayList<String>();

    }

    public String getAddress(String zip) throws Exception {
        String address = (String) jo.get(zip);
        System.out.println(address);
        return address;
    }

    public void addEmail(String email){
        emailList.add(email);
        //send emails to server
    }




}
