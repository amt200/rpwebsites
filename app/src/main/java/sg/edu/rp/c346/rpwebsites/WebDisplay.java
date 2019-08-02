package sg.edu.rp.c346.rpwebsites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebDisplay extends AppCompatActivity {
WebView web;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web);
        web = findViewById(R.id.webView);
        web.setWebViewClient(new WebViewClient());
        Intent it = getIntent();
        String url = it.getStringExtra("urlName");
        web.loadUrl(url);
    }
}
