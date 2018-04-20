package com.github.app_populi;

import android.content.Intent;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import java.util.Locale;
/**
 * Created by woolf on 3/21/2018.
 */

//Fragment for Info Tab
public class InfoFragment extends Fragment {
    //Creates a new instance of this class
    public static InfoFragment newInstance() {
        InfoFragment fragment = new InfoFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_info, container, false);

        //Donate Button
        Button donateButton = (Button) view.findViewById(R.id.donate_button);
        donateButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.paypal.com/us/home"));
                startActivity(browserIntent);
            }
        });

        //Email Submit
        Button submit = (Button) view.findViewById(R.id.infoSubmit);
        final TextView mText = (TextView) view.findViewById(R.id.textView2);
        //On submission of email address
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                EditText emailEdit = (EditText) view.findViewById(R.id.userEmail);
                String email = emailEdit.getText().toString();
                InfoData pd;
                try {
                    pd = new InfoData();
                }catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
                //Adds Email to List
                pd.addEmail(email);
            }
        });

        //Polling Location Finder
        Button submit2 = (Button) view.findViewById(R.id.infoSubmit2);
        final TextView mText2 = (TextView) view.findViewById(R.id.textView2);
        //On submission of zipcode
        submit2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                EditText zipEdit = (EditText) view.findViewById(R.id.userZipcode);
                String zip = zipEdit.getText().toString();
                InfoData pd;
                try {
                    pd = new InfoData();
                }catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
                try{
                    //Looks up address based on zipcode
                    //If found, returns address and displays it
                    String address = pd.getAddress(zip);
                    mText2.setText(address);

                    // Open google maps with the found address
                    String uri = String.format(Locale.ENGLISH, "geo:0,0?q=%s", address);
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                    getContext().startActivity(intent);
                }catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });

        // Countdown Clock
        final TextView count_down_clock = (TextView) view.findViewById(R.id.countdown);
        Calendar start_cal = Calendar.getInstance();
        Calendar end_cal = Calendar.getInstance();
        end_cal.set(2018,4,26);
        long start_in_milli = start_cal.getTimeInMillis();
        long end_in_milli = end_cal.getTimeInMillis();
        long total_in_milli = end_in_milli - start_in_milli;

        CountDownTimer timer = new CountDownTimer(total_in_milli,1000) {
            @Override
            public void onTick(long time_left_in_milli) {
                //count_down_clock.setText((int) TimeUnit.MILLISECONDS.toDays(time_left_in_milli));
            }

            @Override
            public void onFinish() {
                //count_down_clock.setText("Election Day!");
            }
        };
        timer.start();
        return view;
    }
}