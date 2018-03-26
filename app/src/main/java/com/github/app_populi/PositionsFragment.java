package com.github.app_populi;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by woolf on 3/21/2018.
 */

public class PositionsFragment extends Fragment {
    public static PositionsFragment newInstance() {
        PositionsFragment fragment = new PositionsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final ArrayList<PopupWindow> openedWindows = new ArrayList<>();
        View view = inflater.inflate(R.layout.fragment_positions, container, false);


        ListView listView = (ListView) view.findViewById(R.id.positionsList);
        //TODO: Add values file for events data just in fragment for testing purposes currently
        final ArrayList<PositionsData> positionsList = new ArrayList<>();
        positionsList.add(new PositionsData("Position 1","This is a very useful description"));
        positionsList.add(new PositionsData("Position 2","Take some notes - also a useful description"));
       

        PositionsAdapter PositionsAdapter = new PositionsAdapter(getContext(),positionsList);
        listView.setAdapter(PositionsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PositionsData currentPosition = positionsList.get(position);
                String description = currentPosition.getIssueDescription();
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View customView = inflater.inflate(R.layout.positions_descriptionpopup,null);

                //Name
                TextView Name = (TextView) customView.findViewById(R.id.Name);
                Name.setText(currentPosition.getIssueName());
                //Description
                TextView Description = (TextView) customView.findViewById(R.id.Description);
                Description.setText("Issue Details: "+currentPosition.getIssueDescription());

                //close any previously opened popup windows
                for(PopupWindow x : openedWindows){
                    x.dismiss();
                }
                final PopupWindow mPopupWindow = new PopupWindow(
                        customView,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );
                openedWindows.add(mPopupWindow);
                if(Build.VERSION.SDK_INT>=21){
                    mPopupWindow.setElevation(5.0f);
                }
                ImageButton closeButton = (ImageButton) customView.findViewById(R.id.close);

                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Dismiss the popup window
                        mPopupWindow.dismiss();
                    }
                });
                mPopupWindow.showAtLocation(view, Gravity.CENTER,0,0);
            }
        });
        return view;
    }
}
