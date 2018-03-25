package com.github.app_populi;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

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
        View view = inflater.inflate(R.layout.fragment_events, container, false);


        ListView listView = (ListView) view.findViewById(R.id.eventsList);
        //TODO: Add values file for events data just in fragment for testing purposes currently
        ArrayList<EventData> eventsList = new ArrayList<>();
        eventsList.add(new EventData("Fundraiser","Give some money :)"));
        eventsList.add(new EventData("Meeting","Take some notes"));
        eventsList.add(new EventData("Debate","Debate some stuff"));
        eventsList.add(new EventData("Elections","Vote for me"));

        EventAdapter eventAdapter = new EventAdapter(getContext(),eventsList);
        listView.setAdapter(eventAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

    }

}
