package com.example.poi9438.myapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.poi9438.myapplication.R;

public class WebviewActivity extends Activity {
    WebView webview;
    TextView t;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        webview = (WebView)findViewById(R.id.webview);
        webview.setWebViewClient(new WebClient()); // 응룡프로그램에서 직접 url 처리
        WebSettings set = webview.getSettings();
        set.setJavaScriptEnabled(true);
        set.setBuiltInZoomControls(true);


        String getString = getIntent().getStringExtra("url");
        String title = getIntent().getStringExtra("title");
        t = (TextView)findViewById(R.id.webview_title);
        t.setText(title);

        webview.loadUrl(getString);

    }

    class WebClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
