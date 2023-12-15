package com.ayushchauhan.x1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ayushchauhan.x1.api.RetrofitClient;
import com.ayushchauhan.x1.model.LogoutUserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class profile extends AppCompatActivity {
        private TextView tv_user_name,tv_user_email,tv_user_mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();
        tv_user_name = (TextView) findViewById(R.id.tv_user_name);
        tv_user_email = (TextView) findViewById(R.id.tv_user_email);
        tv_user_mobile = (TextView) findViewById(R.id.tv_user_mobile);


        SharedPreferences sp = getSharedPreferences("user_data",MODE_PRIVATE);
        tv_user_name.setText(sp.getString("uname",""));
        tv_user_email.setText(sp.getString("uemail",""));
        tv_user_mobile.setText(sp.getString("umobile",""));



    }








    public void back2(View view) {
        Intent i = new Intent(profile.this, dashboard.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(profile.this, dashboard.class);
        startActivity(i);
        finish();
    }
    public void gotohome(View view) {
        Intent i = new Intent(profile.this,dashboard.class);
        startActivity(i);
        finish();
    }

    public void gototransaction(View view) {
        Intent i = new Intent(profile.this, GetAllTransactionActivity.class);
        startActivity(i);
        finish();
    }

    public void gotomore(View view) {
        Intent i = new Intent(profile.this,More.class);
        startActivity(i);
        finish();
    }

    public void Logout(View view) {

    SharedPreferences sp= getSharedPreferences("user_data",MODE_PRIVATE);
    String spToken = sp.getString("utoken","");

        if (!spToken.equals("")) {
            Call<LogoutUserResponse> call = RetrofitClient.getInstance().getApi().logout(spToken);
            call.enqueue(new Callback<LogoutUserResponse>() {
                @Override
                public void onResponse(Call<LogoutUserResponse> call, Response<LogoutUserResponse> response) {
                    if (response.isSuccessful()) {
                        LogoutUserResponse logoutUserResponse = response.body();
                        Toast.makeText(profile.this, "" + logoutUserResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        SharedPreferences.Editor editor = sp.edit();

                        editor.clear();
                        editor.commit();
                        Intent i = new Intent(profile.this, LoginPage.class);
                        startActivity(i);
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<LogoutUserResponse> call, Throwable t) {
                    Toast.makeText(profile.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });


        }
    }

    public void Changepassword(View view) {
        Intent i = new Intent(profile.this,changepassword.class);
        startActivity(i);
        finish();

    }
}