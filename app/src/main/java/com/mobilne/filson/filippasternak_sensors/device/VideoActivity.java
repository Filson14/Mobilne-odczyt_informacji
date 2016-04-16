package com.mobilne.filson.filippasternak_sensors.device;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mobilne.filson.filippasternak_sensors.BaseActivity;
import com.mobilne.filson.filippasternak_sensors.R;

import java.io.File;

/**
 * Created by Filson on 2016-04-16.
 */
public class VideoActivity extends BaseActivity {

    private static final int CAMERA_REQUEST = 2000;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.applyView(this, R.layout.video_activity);

        Button photoButton = (Button) this.findViewById(R.id.recordBtn);

        photoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TextView nameView = (TextView) findViewById(R.id.videoName);
                String fileName = nameView.getText().toString();

                if(fileName.trim().length() == 0) {
                    fileName = "myVideo";
                }

                File mediaFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + fileName +".mp4");
                Intent videoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

                Uri videoUri = Uri.fromFile(mediaFile);
                videoIntent.putExtra(MediaStore.EXTRA_OUTPUT, videoUri);
                startActivityForResult(videoIntent, CAMERA_REQUEST);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, getResources().getString(R.string.videoOk) + ":\n" +
                        data.getData(), Toast.LENGTH_LONG).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, getResources().getString(R.string.videoCancelled) ,
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, getResources().getString(R.string.videoFailed) ,
                        Toast.LENGTH_LONG).show();
            }
        }
    }

}
