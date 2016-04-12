package com.mobilne.filson.filippasternak_sensors.device;

import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.TextView;

import com.mobilne.filson.filippasternak_sensors.BaseActivity;
import com.mobilne.filson.filippasternak_sensors.R;

/**
 * Created by Filson on 2016-04-12.
 */
public class DeviceInfo extends BaseActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.applyView(this, R.layout.activity_deviceinfo);

        textView = (TextView) findViewById(R.id.textView);

        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String IMEINumber = tm.getDeviceId();
        String subscriberID = tm.getDeviceId();
        String SIMSerialNumber = tm.getSimSerialNumber();
        String networkCountryISO = tm.getNetworkCountryIso();
        String SIMCountryISO = tm.getSimCountryIso();
        String softwareVersion = tm.getDeviceSoftwareVersion();
        String voiceMailNumber = tm.getVoiceMailNumber();

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

        String info = "Phone Details:\n";
        info += "\n IMEI Number:" + IMEINumber;
        info += "\n SubscriberID:" + subscriberID;
        info += "\n Sim Serial Number:" + SIMSerialNumber;
        info += "\n Network Country ISO:" + networkCountryISO;
        info += "\n SIM Country ISO:" + SIMCountryISO;
        info += "\n Software Version:" + softwareVersion;
        info += "\n Voice Mail Number:" + voiceMailNumber;
        info += "\n Phone Network Type:" + strPhoneType;
        info += "\n In Roaming? :" + isRoaming;

        textView.setText(info);
    }
}
