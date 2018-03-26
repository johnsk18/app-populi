package com.github.app_populi;

import android.content.Context;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class EventsFragment extends Fragment{
    public static EventsFragment newInstance() {
        EventsFragment fragment = new EventsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final ArrayList<PopupWindow> openedWindows = new ArrayList<>();
        View view = inflater.inflate(R.layout.fragment_events, container, false);


        ListView listView = (ListView) view.findViewById(R.id.eventsList);
        //TODO: Add values file for events data just in fragment for testing purposes currently
        final ArrayList<EventData> eventsList = new ArrayList<>();
        eventsList.add(new EventData("Fundraiser","Give some money :)",new Date(2018,3,12)));
        eventsList.add(new EventData("Meeting","Take some notes",new Date(2018,3,17)));
        eventsList.add(new EventData("Debate","Debate some stuff",new Date(2018,3,20)));
        eventsList.add(new EventData("Elections","Vote for me",new Date(2018,3,28)));
        eventsList.add(new EventData("Elections","Vote for me",new Date(2018,3,28)));
        eventsList.add(new EventData("Elections","Vote for me",new Date(2018,3,28)));
        eventsList.add(new EventData("Elections","Vote for me",new Date(2018,3,28)));

        eventsList.add(new EventData("Fundraiser","Give some money :)",new Date(2018,3,12)));
        eventsList.add(new EventData("Meeting","Take some notes",new Date(2018,3,17)));
        eventsList.add(new EventData("Debate","Debate some stuff",new Date(2018,3,20)));
        eventsList.add(new EventData("Elections","Vote for me",new Date(2018,3,28)));
        eventsList.add(new EventData("Elections","Vote for me",new Date(2018,3,28)));
        eventsList.add(new EventData("Elections","Vote for me",new Date(2018,3,28)));
        eventsList.add(new EventData("Elections","Vote for me",new Date(2018,3,28)));
        eventsList.add(new EventData("Fundraiser","Give some money :)",new Date(2018,3,12)));
        eventsList.add(new EventData("Meeting","Take some notes",new Date(2018,3,17)));
        eventsList.add(new EventData("Debate","Debate some stuff",new Date(2018,3,20)));
        eventsList.add(new EventData("Elections","Vote for me",new Date(2018,3,28)));
        eventsList.add(new EventData("Elections","Vote for me",new Date(2018,3,28)));
        eventsList.add(new EventData("Elections","Vote for me",new Date(2018,3,28)));
        eventsList.add(new EventData("Elections","Vote for me",new Date(2018,3,28)));

        EventAdapter eventAdapter = new EventAdapter(getContext(),eventsList);
        listView.setAdapter(eventAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EventData currentEvent = eventsList.get(position);
                String description = currentEvent.getEventDescription();
                //Toast.makeText(getActivity(),currentEvent.getEventDescription(), Toast.LENGTH_LONG).show();
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View customView = inflater.inflate(R.layout.descriptionpopup,null);

                //Name
                TextView Name = (TextView) customView.findViewById(R.id.Name);
                Name.setText(currentEvent.getEventName());
                //Description
                TextView Description = (TextView) customView.findViewById(R.id.description);
                Description.setText("Event Details: "+currentEvent.getEventDescription());
                //Date
                DateFormat df = new SimpleDateFormat("MMM dd");
                TextView Date = (TextView) customView.findViewById(R.id.Date);
                Date.setText(df.format(currentEvent.getEventDate()));


                //close any previously opened popup windows
                for(PopupWindow x : openedWindows){
                    x.dismiss();
                }
                final PopupWindow mPopupWindow = new PopupWindow(
                        customView,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
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

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

    }
}
