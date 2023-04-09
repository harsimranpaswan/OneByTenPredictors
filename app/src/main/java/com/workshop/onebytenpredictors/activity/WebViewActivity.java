
package com.workshop.onebytenpredictors.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.workshop.onebytenpredictors.R;

public class WebViewActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webView=findViewById(R.id.webview);
        Intent intent=getIntent();
        String url=intent.getStringExtra("url");

        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }
}