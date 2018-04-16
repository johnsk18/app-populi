package com.github.app_populi;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        Button donateButton = (Button) view.findViewById(R.id.donate_button);
        donateButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.paypal.com/us/home"));
                startActivity(browserIntent);
            }
        });


        return view;
    }
}
