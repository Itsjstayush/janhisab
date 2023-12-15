package com.ayushchauhan.x1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class enquiry extends AppCompatActivity {
    private EditText et_name1;
    private EditText et_mobile1;
    private EditText et_email1;
    private EditText et_message1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enquiry);
        getSupportActionBar().hide();
        et_name1 = (EditText) findViewById(R.id.et_name1);
        et_email1 = (EditText) findViewById(R.id.et_email1);
        et_mobile1 = (EditText) findViewById(R.id.et_mobile1);
        et_message1 = (EditText) findViewById(R.id.et_message1);
    }
    public void submitfeed(View view) {
        String name = et_name1.getText().toString();
        String mobile = et_mobile1.getText().toString();
        String email = et_email1.getText().toString();
        String message = et_message1.getText().toString();
        Toast.makeText(this, "Feedback Submited"+name, Toast.LENGTH_SHORT).show();
        Intent i = new Intent(enquiry.this,More.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(enquiry.this,More.class);
        startActivity(i);
        finish();
    }

    public void back5(View view) {
        Intent i = new Intent(enquiry.this,More.class);
        startActivity(i);
        finish();
    }


}