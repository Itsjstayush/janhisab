package com.ayushchauhan.x1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.ayushchauhan.x1.api.RetrofitClient;
import com.ayushchauhan.x1.model.AddTransactionResponse;
import com.ayushchauhan.x1.model.Customer;
import com.ayushchauhan.x1.model.CustomerResponse;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class addtransaction extends AppCompatActivity implements AdapterView.OnItemSelectedListener {



    ArrayAdapter<Customer> ad;
    private Spinner sp_customer;
    private TextInputEditText et_trans_title,et_trans_amount,et_trans_description;
    private int customer_id;
    private RadioGroup rg_amount;
    private String amount_type;
    private int user_id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtransaction);
        getSupportActionBar().hide();

        sp_customer = (Spinner) findViewById(R.id.spn_customer);
        rg_amount = (RadioGroup) findViewById(R.id.rg_amount);
        et_trans_title = (TextInputEditText) findViewById(R.id.et_title);
        et_trans_amount =(TextInputEditText) findViewById(R.id.et_amountgiven);
        et_trans_description = (TextInputEditText) findViewById(R.id.et_description);


        getCustomer();



    }

    private void getCustomer() {
        SharedPreferences sp = getSharedPreferences("user_data",MODE_PRIVATE);
        user_id = Integer.parseInt(sp.getString("uid",""));
        Toast.makeText(this, ""+user_id, Toast.LENGTH_SHORT).show();


        Call<CustomerResponse> call = RetrofitClient.getInstance().getApi().getcustomer(user_id);
        call.enqueue(new Callback<CustomerResponse>() {
            @Override
            public void onResponse(Call<CustomerResponse> call, Response<CustomerResponse> response) {
                if(response.isSuccessful()){
                    CustomerResponse customerResponse = response.body();
                    if(customerResponse.isStatus()){
                        ArrayList<Customer> customers = customerResponse.getData();
                        ad = new ArrayAdapter<Customer>(addtransaction.this, android.R.layout.simple_spinner_dropdown_item,customers);
                        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        sp_customer.setAdapter(ad);
                        sp_customer.setOnItemSelectedListener(addtransaction.this);

                    }else{
                        Toast.makeText(addtransaction.this, ""+customerResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<CustomerResponse> call, Throwable t) {

            }
        });
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        customer_id = ad.getItem(i).getId();
        Toast.makeText(this, ""+customer_id, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    public void setType(View view) {
        int radioid = rg_amount.getCheckedRadioButtonId();
        RadioButton radioButton= (RadioButton) findViewById(radioid);
        amount_type = radioButton.getText().toString().trim();
        Toast.makeText(this, ""+amount_type, Toast.LENGTH_SHORT).show();
    }

    public void toaddtrans(View view) {

        String title = et_trans_title.getText().toString().trim();
        String amount = et_trans_amount.getText().toString().trim();
        String description = et_trans_description.getText().toString().trim();



        Call<AddTransactionResponse> call= RetrofitClient.getInstance().getApi().addtransaction(
                user_id,customer_id,title,amount,amount_type,description
        );
        call.enqueue(new Callback<AddTransactionResponse>() {
            @Override
            public void onResponse(Call<AddTransactionResponse> call, Response<AddTransactionResponse> response) {
                if(response.isSuccessful()){
                    AddTransactionResponse addTransactionResponse = response.body();
                    if(addTransactionResponse.isStatus()){
                        Toast.makeText(addtransaction.this, ""+addTransactionResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(addtransaction.this,GetAllTransactionActivity.class);
                        startActivity(i);
                        finish();
                    }else{
                        Toast.makeText(addtransaction.this, ""+addTransactionResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<AddTransactionResponse> call, Throwable t) {
                Toast.makeText(addtransaction.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }













    public void back1(View view) {
        Intent i = new Intent(addtransaction.this,dashboard.class);
        startActivity(i);
        finish();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(addtransaction.this, dashboard.class);
        startActivity(i);
        finish();
    }


}