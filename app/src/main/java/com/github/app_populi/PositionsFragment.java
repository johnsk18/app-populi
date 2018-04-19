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
import java.util.ArrayList;


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
        //TODO: Add values file for events data just in fragment for testing purposes currently

        //Positions Data
        ArrayList<String> positionsData = new ArrayList<>();
        positionsData.add("{\n  \"name\": \"Fiscal Policy\",\n  \"description\": \"Description of fiscal policy positions\", \"subname1\": \"Tax Policy\",\n  \"subdescription1\": \"Description of tax positions\" , \"subname2\": \"Debt/Deficit\",\n  \"subdescription2\": \"Descriptions of positions on the debt and deficit\"\n}");
        positionsData.add("{\n  \"name\": \"Healthcare\",\n  \"description\": \"Broad healthcare goals\", \"subname1\": \"Government Role in Healthcare\",\n  \"subdescription1\": \"Position on government's role in healthcare\" , \"subname2\": \"Healthcare Policy Proposal\",\n  \"subdescription2\": \"Description of healthcare policy plan\"\n}");
        positionsData.add("{\n  \"name\": \"Foreign Policy\",\n  \"description\": \"Description of foreign policy positions\", \"subname1\": \"Middle East\",\n  \"subdescription1\": \"Positions on issues related to MENA and the U.S. role there\" , \"subname2\": \"America's Role In The World\",\n  \"subdescription2\": \"Position on America's role in the world\"\n}");
        positionsData.add("{\n  \"name\": \"Immigration\",\n  \"description\": \"Description of immigration positions and policy\", \"subname1\": \"Unauthorized Immigration\",\n  \"subdescription1\": \"Position on unauthorized immigration and policy concerning it\" , \"subname2\": \"Refugees\",\n  \"subdescription2\": \"Positions on what America's refugee policy should be\"\n}");
        positionsData.add("{\n  \"name\": \"Jobs\",\n  \"description\": \"Description of jobs policy supported by candidate\", \"subname1\": \"Trade Agreements\",\n  \"subdescription1\": \"Positions on how to address trade agreements\" , \"subname2\": \"Employment\",\n  \"subdescription2\": \"Policy proposed to ameliorate unemployment\"\n}");

        //Creates List out of Positions Data
        final ArrayList<PositionsData> positionsList = new ArrayList<>();
        for(int i = 0; i < positionsData.size(); i++) {
            try {
                positionsList.add(new PositionsData(positionsData.get(i)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

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
}
