package com.ayushchauhan.x1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ayushchauhan.x1.api.RetrofitClient;
import com.ayushchauhan.x1.model.User;
import com.ayushchauhan.x1.model.UserLoginResponse;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPage extends AppCompatActivity {
    private TextInputEditText email1,password1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        getSupportActionBar().hide();
        email1=(TextInputEditText) findViewById(R.id.et_email1);
        password1=(TextInputEditText) findViewById(R.id.et_password1);

        SharedPreferences sp = getSharedPreferences("user_data",MODE_PRIVATE);
        String spemail = sp.getString("uemail","");
        if(!spemail.equals("")){
            Intent i = new Intent(LoginPage.this,dashboard.class);
            startActivity(i);
            finish();
        }

    }
    public void Login(View view){

        String email = email1.getText().toString().trim();
        String password = password1.getText().toString().trim();
        if(email.isEmpty()) {
            email1.setError("Required");
            email1.requestFocus();
            return;

        }
        if(password.isEmpty()) {
            password1.setError("Required");
            password1.requestFocus();
            return;
        }



        Call<UserLoginResponse> call= RetrofitClient.getInstance().getApi().login(email,password);
        call.enqueue(new Callback<UserLoginResponse>() {
            @Override
            public void onResponse(Call<UserLoginResponse> call, Response<UserLoginResponse> response) {
                if (response.isSuccessful()){
                    UserLoginResponse userLoginResponse = response.body();
                    Toast.makeText(LoginPage.this, ""+userLoginResponse.getMessage(), Toast.LENGTH_SHORT).show();


                    ArrayList<User> users = userLoginResponse.getData();


                    SharedPreferences sp =getSharedPreferences("user_data",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putString("uid",users.get(0).getId());
                    editor.putString("uname",users.get(0).getName());
                    editor.putString("uemail",users.get(0).getEmail());
                    editor.putString("upassword",users.get(0).getPassword());
                    editor.putString("umobile",users.get(0).getMobile());
                    editor.putString("udate_time",users.get(0).getDate_time());
                    editor.putString("uis_login",users.get(0).getIs_login());
                    editor.putString("ustatus",users.get(0).getStatus());
                    editor.putString("utoken",users.get(0).getToken());
                    editor.commit();


                    Intent i = new Intent(LoginPage.this, dashboard.class);
                    startActivity(i);
                    finish();

                }
            }

            @Override
            public void onFailure(Call<UserLoginResponse> call, Throwable t) {
                Toast.makeText(LoginPage.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }









    public void toregister(View view) {
        Intent i = new Intent(LoginPage.this,RegisterationPage.class);
        startActivity(i);
        finish();
    }
}