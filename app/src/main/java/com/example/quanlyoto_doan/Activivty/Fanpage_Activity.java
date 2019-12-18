package com.example.quanlyoto_doan.Activivty;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.quanlyoto_doan.R;

public class Fanpage_Activity extends AppCompatActivity {
    private Toolbar tollBarFanpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fanpage_);
        init();
    }


    private void init() {
        tollBarFanpage=findViewById(R.id.tollBarFanpage);
        setSupportActionBar(tollBarFanpage);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        tollBarFanpage.setNavigationIcon(R.drawable.back);
        tollBarFanpage.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        WebView webview=findViewById(R.id.webview);
        webview.loadUrl("https://www.facebook.com/huynh.tan.54390");
        webview.setWebViewClient(new WebViewClient());
    }
}
