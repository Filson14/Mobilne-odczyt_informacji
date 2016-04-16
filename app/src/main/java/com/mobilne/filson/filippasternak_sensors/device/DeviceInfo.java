package com.mobilne.filson.filippasternak_sensors.device;

import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.mobilne.filson.filippasternak_sensors.BaseActivity;
import com.mobilne.filson.filippasternak_sensors.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Filson on 2016-04-12.
 */
public class DeviceInfo extends BaseActivity {
    TextView textView;
    ArrayList<DeviceInfoItem> allInfo;
    private ArrayAdapter<DeviceInfoItem> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.applyView(this, R.layout.activity_deviceinfo);

        textView = (TextView) findViewById(R.id.textView);
        allInfo = new ArrayList<>();
        adapter = new DeviceInfoAdapter(this, allInfo);

        ListView listView = (ListView) findViewById(R.id.deviceListView);
        listView.setAdapter(adapter);


        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String IMEINumber = tm.getDeviceId();
        String subscriberID = tm.getDeviceId();
        String SIMSerialNumber = tm.getSimSerialNumber();
        String networkCountryISO = tm.getNetworkCountryIso();
        String SIMCountryISO = tm.getSimCountryIso();
        String SIMOperator = tm.getSimOperator();
        String SIMOperatorName = tm.getSimOperatorName();
        String softwareVersion = tm.getDeviceSoftwareVersion();
        String voiceMailNumber = tm.getVoiceMailNumber();
        String networkOperatorName = tm.getNetworkOperatorName();

        String strPhoneType = "";

        int intPhoneType = tm.getPhoneType();

        switch (intPhoneType) {
            case (TelephonyManager.PHONE_TYPE_CDMA):
                strPhoneType = "CDMA";
                break;
            case (TelephonyManager.PHONE_TYPE_GSM):
                strPhoneType = "GSM";
                break;
            case (TelephonyManager.PHONE_TYPE_NONE):
                strPhoneType = "NONE";
                break;
        }

        boolean isRoaming = tm.isNetworkRoaming();

        allInfo.add(new DeviceInfoItem("IMEI Number", IMEINumber));
        allInfo.add(new DeviceInfoItem("SubscriberID", subscriberID));
        allInfo.add(new DeviceInfoItem("Sim Serial Number", SIMSerialNumber));
        allInfo.add(new DeviceInfoItem("Network Country ISO", networkCountryISO));
        allInfo.add(new DeviceInfoItem("Network Operator Name", networkOperatorName));
        allInfo.add(new DeviceInfoItem("SIM Country ISO", SIMCountryISO));
        allInfo.add(new DeviceInfoItem("SIM Operator", SIMOperator));
        allInfo.add(new DeviceInfoItem("SIM Operator Name", SIMOperatorName));
        allInfo.add(new DeviceInfoItem("Software Version", softwareVersion));
        allInfo.add(new DeviceInfoItem("Voice Mail Number", voiceMailNumber));
        allInfo.add(new DeviceInfoItem("Phone Network Type", strPhoneType));
        allInfo.add(new DeviceInfoItem("In Roaming? ", isRoaming ? "True" : "False"));

        adapter.notifyDataSetChanged();
    }
}
