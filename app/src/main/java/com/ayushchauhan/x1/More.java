package com.ayushchauhan.x1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class More extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        getSupportActionBar().hide();
    }

    public void gotohome(View view) {
        Intent i = new Intent(More.this,dashboard.class);
        startActivity(i);
        finish();
    }

    public void gototransaction(View view) {
        Intent i = new Intent(More.this, GetAllTransactionActivity.class);
        startActivity(i);
        finish();
    }

    public void gotoprofile(View view) {
        Intent i = new Intent(More.this,profile.class);
        startActivity(i);
        finish();
    }
    public void terms(View view) {
        Intent i = new Intent(More.this,terms.class);
        startActivity(i);
        finish();
    }

    public void enquiry(View view) {
        Intent i = new Intent(More.this,enquiry.class);
        startActivity(i);
        finish();
    }

    public void invoice(View view) {
        Intent i = new Intent(More.this,Invoice.class);
        startActivity(i);
        finish();
    }

    public void admin(View view) {

        Intent i = new Intent(More.this,Admin.class);
        startActivity(i);
        finish();
    }

    public void back(View view) {
        Intent i = new Intent(More.this,dashboard.class);
        startActivity(i);
        finish();
    }


    public void back4(View view) {
        Intent i = new Intent(More.this,dashboard.class);
        startActivity(i);
        finish();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(More.this,dashboard.class);
        startActivity(i);
        finish();
    }
}