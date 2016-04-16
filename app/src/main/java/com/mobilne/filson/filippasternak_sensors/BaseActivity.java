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

import com.mobilne.filson.filippasternak_sensors.about.AuthorActivity;
import com.mobilne.filson.filippasternak_sensors.device.CameraActivity;
import com.mobilne.filson.filippasternak_sensors.device.DeviceInfo;
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
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
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
                MenuItem selectedItem = (MenuItem)adapter.getItemAtPosition(position);
                Intent intent = null;
                if(!selectedItem.isHeader()) {
                    switch(selectedItem.getId()) {
                        case "sensors":
                            intent = new Intent(BaseActivity.this, SensorsListActivity.class);
                            break;
                        case "sms":
                            intent = new Intent(BaseActivity.this, SmsActivity.class);
                            break;
                        case "photo":
                            intent = new Intent(BaseActivity.this, CameraActivity.class);
                            break;
                        case "webview":
                            intent = new Intent(BaseActivity.this, WebViewActivity.class);
                            break;
                        case "phoneInfo":
                            intent = new Intent(BaseActivity.this, DeviceInfo.class);
                            break;
                        case "author":
                            intent = new Intent(BaseActivity.this, AuthorActivity.class);
                            break;
                    }

                    if(intent != null) {
                        startActivity(intent);
                    }
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
        listItems.add(new MenuItem("sensors", R.drawable.ic_track_changes_black_48dp, getResources().getString(R.string.sensors)));
        listItems.add(new MenuItem("webview", R.drawable.ic_public_black_48dp, "Web Page"));
        listItems.add(new MenuItem("Device"));
        listItems.add(new MenuItem("phoneInfo", R.drawable.ic_perm_device_information_black_48dp, "Phone info"));
        listItems.add(new MenuItem("sms", R.drawable.ic_mail_outline_black_48dp, "SMS"));
        listItems.add(new MenuItem("photo", R.drawable.ic_camera_black_48dp, getResources().getString(R.string.photo)));
        listItems.add(new MenuItem("video", R.drawable.ic_videocam_black_48dp,getResources().getString(R.string.video)));
    }


}