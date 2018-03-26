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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class PositionsAdapter extends ArrayAdapter<PositionsData> {
    private Context positionsContext;
    private List<PositionsData> positionsList = new ArrayList<>();

    public PositionsAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<PositionsData> list){
        super(context,0,list);
        positionsContext = context;
        positionsList = list;
    }

    @NonNull
    @Override
    public View getView(int positon, @Nullable View convertView, @NonNull ViewGroup parent){
        View listItem = convertView;
        if(listItem==null){
            listItem = LayoutInflater.from(positionsContext).inflate(R.layout.positions_item,parent,false);
        }
        PositionsData currentPosition = positionsList.get(positon);

        //Name
        TextView Name = (TextView) listItem.findViewById(R.id.Name);
        Name.setText(currentPosition.getEventName());

        //Description
        //TextView Description = (TextView) listItem.findViewById(R.id.Description);
        //Description.setText(currentPosition.getEventDescription());

        return listItem;
    }
}
