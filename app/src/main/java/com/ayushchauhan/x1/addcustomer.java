package com.ayushchauhan.x1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ayushchauhan.x1.api.RetrofitClient;
import com.ayushchauhan.x1.model.AddCustomerResponse;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class addcustomer extends AppCompatActivity {
    private EditText et_customername;
    private EditText et_customeremail;
    private EditText et_customermobile;
    private EditText et_customeraddress;

    private String user_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcustomer);
        getSupportActionBar().hide();
        et_customername = (TextInputEditText) findViewById(R.id.et_customername);
        et_customeremail= (TextInputEditText) findViewById(R.id.et_customeremail);
        et_customeraddress = (TextInputEditText) findViewById(R.id.et_customeraddress);
        et_customermobile = (TextInputEditText) findViewById(R.id.et_customermobile);

        SharedPreferences sp =getSharedPreferences("user_data",MODE_PRIVATE);
        user_id = sp.getString("uid","");
    }

    public void addnewcustomer(View view) {
        String cname = et_customername.getText().toString().trim();
        String cemail =et_customeremail.getText().toString().trim();
        String cmobile =et_customermobile.getText().toString().trim();
        String caddress =et_customeraddress.getText().toString().trim();
        if(cname.isEmpty()){
            et_customername.setError("Required");
            et_customername.requestFocus();
        }
        if(cemail.isEmpty()){
            et_customeremail.setError("Required");
            et_customeremail.requestFocus();
        }
        if(cmobile.isEmpty()){
            et_customermobile.setError("Required");
            et_customermobile.requestFocus();
        }
        if(caddress.isEmpty()){
            et_customeraddress.setError("Required");
            et_customeraddress.requestFocus();
        }
        Call<AddCustomerResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .addCustomer(cname,cemail,cmobile,caddress,user_id);
        call.enqueue(new Callback<AddCustomerResponse>() {
            @Override
            public void onResponse(Call<AddCustomerResponse> call, Response<AddCustomerResponse> response) {
                if(response.isSuccessful()){
                    AddCustomerResponse addCustomerResponse = response.body();
                    if(addCustomerResponse.isStatus()){
                        Toast.makeText(addcustomer.this, ""+addCustomerResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(addcustomer.this, ""+addCustomerResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<AddCustomerResponse> call, Throwable t) {
                Toast.makeText(addcustomer.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }

    public void back3(View view) {
        Intent i = new Intent(addcustomer.this,dashboard.class);
        startActivity(i);
        finish();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(addcustomer.this,dashboard.class);
        startActivity(i);
        finish();
    }
}