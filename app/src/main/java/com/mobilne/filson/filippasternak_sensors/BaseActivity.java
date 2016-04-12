package com.mobilne.filson.filippasternak_sensors;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mobilne.filson.filippasternak_sensors.device.SmsActivity;

import java.util.ArrayList;

/**
 * Created by Filson on 2016-04-11.
 */
public class BaseActivity extends Activity {

    protected DrawerLayout mDrawer;
    private ArrayList<MenuItem> listItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.drawer_layout);

        /*Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);*/

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        fillNavigationMenu();


        SideMenuAdapter navigationListAdapter = new SideMenuAdapter(this, listItems);
        ListView mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerList.setAdapter(navigationListAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
                System.out.println(position);
                MenuItem selectedItem = (MenuItem)adapter.getItemAtPosition(position);
                System.out.println("Title: " + selectedItem.getTitle());
                Intent intent;
                if(!selectedItem.isHeader()) {
                    if (selectedItem.getId() == "sensors") {
                        intent = new Intent(BaseActivity.this, SensorsListActivity.class);
                    } else if(selectedItem.getId() == "sms") {
                        intent = new Intent(BaseActivity.this, SmsActivity.class);
                    } else {
                        intent = new Intent(BaseActivity.this, MainActivity.class);
                    }

                    startActivity(intent);
                    mDrawer.closeDrawers();
                }
            }
        });

    }

    protected void applyView(Activity activity, int layout) {
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(layout, null, false);
        mDrawer.addView(contentView, 0);
    }


    private void fillNavigationMenu() {
        listItems = new ArrayList<MenuItem>();
        listItems.add(new MenuItem("Choose option"));
        listItems.add(new MenuItem("sensors", R.drawable.ic_action_phone, getResources().getString(R.string.sensors)));
        listItems.add(new MenuItem("Device"));
        listItems.add(new MenuItem("sms", R.drawable.ic_action_phone, "SMS"));
        listItems.add(new MenuItem("About"));
        listItems.add(new MenuItem("author", R.drawable.ic_action_phone, getResources().getString(R.string.aboutAuthor)));
    }


}