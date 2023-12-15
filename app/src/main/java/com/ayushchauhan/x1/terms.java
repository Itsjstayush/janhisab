package com.ayushchauhan.x1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class terms extends AppCompatActivity {
    private String terms_url="https://docs.google.com/gview?embedded=true&url=https://myhisab.seeksolution.in/terms-and-conditions/terms.pdf";
    private WebView wv_terms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);

        wv_terms=(WebView) findViewById(R.id.wv_terms);
        wv_terms.setWebChromeClient(new WebChromeClient());
        wv_terms.loadUrl(terms_url);


        //javascript enable
        wv_terms.getSettings().setJavaScriptEnabled(true);
        wv_terms.getSettings().setSupportZoom(true);
    }

    public void back(View view) {
        Intent i = new Intent(terms.this,More.class);
        startActivity(i);
        finish();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(terms.this,More.class);
        startActivity(i);
        finish();
    }
}