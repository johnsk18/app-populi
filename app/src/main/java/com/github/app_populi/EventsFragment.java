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

        // Events data
        final ArrayList<EventData> eventsList = loadEvents();

        EventAdapter eventAdapter = new EventAdapter(getContext(),eventsList);
        listView.setAdapter(eventAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EventData currentEvent = eventsList.get(position);
                String description = currentEvent.getEventDescription();
                //Toast.makeText(getActivity(),currentEvent.getEventDescription(), Toast.LENGTH_LONG).show();
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View customView = inflater.inflate(R.layout.events_descriptionpopup,null);

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

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

    }

    // Loads all the events from res/raw/events.json
    public ArrayList<EventData> loadEvents() {

        ArrayList<EventData> eventsList = new ArrayList<>();

        // String to Date formatter
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

        // Load json file from resources, and convert to string
        InputStream is = getContext().getResources().openRawResource(R.raw.events);
        String isString = convertStreamToString(is);
        JSONObject json;
        JSONArray events;
        try {
            // Parse through file
            json = new JSONObject(isString);
            events = json.getJSONArray("events");

            for(int i = 0; i < events.length(); ++i) {
                JSONObject event = events.getJSONObject(i);

                // Pull out fields, and store in object
                String name = event.getString("name");
                String description = event.getString("description");
                Date date = formatter.parse(event.getString("date"));

                eventsList.add(new EventData(name, description, date));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return eventsList;
        }

        return eventsList;

    }

    // Helper function to convert an InputStream to String
    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
