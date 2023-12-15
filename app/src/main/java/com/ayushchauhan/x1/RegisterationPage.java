package com.ayushchauhan.x1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ayushchauhan.x1.api.RetrofitClient;
import com.ayushchauhan.x1.model.CreateUserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterationPage extends AppCompatActivity {
    private EditText et_name;
    private EditText et_mobile;
    private EditText et_email;
    private EditText et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration_page);
        getSupportActionBar().hide();
        et_name = (EditText) findViewById(R.id.et_name);
        et_email = (EditText) findViewById(R.id.et_email);
        et_mobile = (EditText) findViewById(R.id.et_mobile);
        et_password = (EditText) findViewById(R.id.et_password);
    }

    public void Register(View view) {
        String name = et_name.getText().toString();
        String mobile = et_mobile.getText().toString();
        String email = et_email.getText().toString();
        String password = et_password.getText().toString();
        //checked for name
        if(name.isEmpty()){
            et_name.setError("Required");
            et_name.requestFocus();
            return;
        }
        //checked for email
        if(email.isEmpty()){
            et_email.setError("Required");
            et_email.requestFocus();
            return;
        }
        //checked for mobile
        if(mobile.isEmpty()){
            et_mobile.setError("Required");
            et_mobile.requestFocus();
            return;
        }
        //check for password
        if(password.isEmpty()){
            et_password.setError("Required");
            et_password.requestFocus();
            return;
        }

        //Retrofit


        Call<CreateUserResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .createUser(name,email,mobile,password);


        call.enqueue(new Callback<CreateUserResponse>() {
            @Override
            public void onResponse(Call<CreateUserResponse> call, Response<CreateUserResponse> response) {
                if(response.isSuccessful()){
                    CreateUserResponse userResponse = response.body();
                    Toast.makeText(RegisterationPage.this,""+userResponse.getMessage(),Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<CreateUserResponse> call, Throwable t) {
                Toast.makeText(RegisterationPage.this,""+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });






        Intent i = new Intent(RegisterationPage.this,LoginPage.class);
        startActivity(i);
        finish();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(RegisterationPage.this,LoginPage.class);
        startActivity(i);
        finish();
    }

    public void tologin(View view) {
        Intent i = new Intent(getApplicationContext(),LoginPage.class);
        startActivity(i);
        finish();
    }
}