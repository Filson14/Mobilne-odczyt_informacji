package com.mobilne.filson.filippasternak_sensors.device;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobilne.filson.filippasternak_sensors.BaseActivity;
import com.mobilne.filson.filippasternak_sensors.R;

import java.util.ArrayList;

/**
 * Created by Filson on 2016-04-12.
 */
public class SmsActivity extends BaseActivity {
    EditText phoneNoEdit;
    EditText messageEdit;
    Button sendBtn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.applyView(this, R.layout.activity_sms);

        sendBtn = (Button) findViewById(R.id.sendBtn);
        phoneNoEdit = (EditText) findViewById(R.id.phoneNoField);
        messageEdit = (EditText) findViewById(R.id.messageField);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = phoneNoEdit.getText().toString();
                String msg = messageEdit.getText().toString();
                if(number.length() > 0 && msg.length() > 0) {
                    sendMessage(number, msg);
                }
            }
        });
    }

    private void sendMessage(String number, String message) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            ArrayList<String> messageParts = smsManager.divideMessage(message);

            for(int i = 0; i < messageParts.size(); i++) {
                smsManager.sendTextMessage(number, null, messageParts.get(i), null, null);
            }

            Toast.makeText(getApplicationContext(), getResources().getString(R.string.smsOK), Toast.LENGTH_LONG).show();
            phoneNoEdit.setText("");
            messageEdit.setText("");
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.smsFailed), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
