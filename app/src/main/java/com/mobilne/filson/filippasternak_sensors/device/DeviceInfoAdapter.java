package com.mobilne.filson.filippasternak_sensors.device;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobilne.filson.filippasternak_sensors.MenuItem;
import com.mobilne.filson.filippasternak_sensors.R;

import java.util.ArrayList;

/**
 * Created by Filson on 2016-04-16.
 */
public class DeviceInfoAdapter extends ArrayAdapter<DeviceInfoItem> {
    private final Context context;
    private final ArrayList<DeviceInfoItem> itemsList;

    public DeviceInfoAdapter(Context context, ArrayList<DeviceInfoItem> itemsList) {
        super(context, android.R.layout.simple_list_item_2, itemsList);

        this.context = context;
        this.itemsList = itemsList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(android.R.layout.simple_list_item_2, parent, false);

            TextView mainText  = (TextView) rowView.findViewById(android.R.id.text1);
            TextView smallText = (TextView) rowView.findViewById(android.R.id.text2);

            mainText.setText(itemsList.get(position).getValue());
            smallText.setText(itemsList.get(position).getLabel());

        return rowView;
    }
}
