package com.github.app_populi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class EventAdapter extends ArrayAdapter<EventData> {
    private Context eventContext;
    private List<EventData> eventsList = new ArrayList<>();

    public EventAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<EventData> list){
        super(context,0,list);
        eventContext = context;
        eventsList = list;
    }

    @NonNull
    @Override
    public View getView(int positon, @Nullable View convertView, @NonNull ViewGroup parent){
        View listItem = convertView;
        if(listItem==null){
            listItem = LayoutInflater.from(eventContext).inflate(R.layout.events_item,parent,false);
        }
        EventData currentEvent = eventsList.get(positon);

        //Name
        TextView Name = (TextView) listItem.findViewById(R.id.Name);
        Name.setText(currentEvent.getEventName());

        //Description
        //TextView Description = (TextView) listItem.findViewById(R.id.Description);
        //Description.setText(currentEvent.getEventDescription());

        //Date
        DateFormat df = new SimpleDateFormat("MMM dd");
        TextView Date = (TextView) listItem.findViewById(R.id.Date);
        Date.setText(df.format(currentEvent.getEventDate()));

        return listItem;
    }
}
