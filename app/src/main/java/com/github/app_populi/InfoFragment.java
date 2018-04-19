package com.github.app_populi;

import java.util.AbstractMap;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
/**
 * Created by woolf on 3/21/2018.
 */

public class InfoFragment extends Fragment {
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

        Button donateButton = (Button) view.findViewById(R.id.donate_button);
        donateButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.paypal.com/us/home"));
                startActivity(browserIntent);
            }
        });

        Button submit = (Button) view.findViewById(R.id.infoSubmit);
        final TextView mText = (TextView) view.findViewById(R.id.textView2);
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
                pd.addEmail(email);
            }
        });

        Button submit2 = (Button) view.findViewById(R.id.infoSubmit2);
        final TextView mText2 = (TextView) view.findViewById(R.id.textView2);
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
                    mText2.setText(pd.getAddress(zip));
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return view;
    }
}