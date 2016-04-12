package com.mobilne.filson.filippasternak_sensors;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Filson on 2016-04-12.
 */
public class WebViewActivity extends BaseActivity {

    private Button goBtn;
    private EditText urlField;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.applyView(this, R.layout.activity_webview);

        goBtn = (Button) this.findViewById(R.id.goBtn);
        urlField = (EditText) this.findViewById(R.id.urlField);
        webView = (WebView) this.findViewById(R.id.webView);

        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl(urlField.getText().toString());
            }
        });
    }
}
