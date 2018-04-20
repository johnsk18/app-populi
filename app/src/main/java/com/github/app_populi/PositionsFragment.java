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

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by woolf on 3/21/2018.
 */
//Fragment for Candidate Positions Tab
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

        //Positions Data
        final ArrayList<PositionsData> positionsList = loadPositions();

        //Create Positions Adapter
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
                Description.setText(currentPosition.getIssueDescription());

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

    // Loads all the events from res/raw/positions.json
    public ArrayList<PositionsData> loadPositions() {

        ArrayList<PositionsData> positionsList = new ArrayList<>();

        // Load json file from resources, and convert to string
        InputStream is = getContext().getResources().openRawResource(R.raw.positions);
        String isString = convertStreamToString(is);
        JSONObject json;
        JSONArray positions;
        try {
            // Parse through file
            json = new JSONObject(isString);
            positions = json.getJSONArray("positions");

            for(int i = 0; i < positions.length(); ++i) {
                // Store position
                JSONObject event = positions.getJSONObject(i);
                positionsList.add(new PositionsData(event.toString()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return positionsList;
        }

        return positionsList;

    }

    // Helper function to convert an InputStream to String
    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
