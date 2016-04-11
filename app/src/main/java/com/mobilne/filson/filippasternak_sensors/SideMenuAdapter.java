package com.mobilne.filson.filippasternak_sensors;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Filson on 2016-04-11.
 */
public class SideMenuAdapter extends ArrayAdapter<MenuItem> {
    private final Context context;
    private final ArrayList<MenuItem> itemsList;

    public SideMenuAdapter(Context context, ArrayList<MenuItem> itemsList) {
        super(context, R.layout.menu_item, itemsList);

        this.context = context;
        this.itemsList = itemsList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater

        View rowView = null;
        if(!itemsList.get(position).isHeader()){
            rowView = inflater.inflate(R.layout.menu_item, parent, false);


            ImageView imgView = (ImageView) rowView.findViewById(R.id.item_icon);
            TextView titleView = (TextView) rowView.findViewById(R.id.item_title);

            imgView.setImageResource(itemsList.get(position).getIcon());
            titleView.setText(itemsList.get(position).getTitle());
        }
        else{
            rowView = inflater.inflate(R.layout.menu_header, parent, false);
            TextView titleView = (TextView) rowView.findViewById(R.id.header);
            titleView.setText(itemsList.get(position).getTitle());

        }

        // 5. retrn rowView
        return rowView;
    }
}
