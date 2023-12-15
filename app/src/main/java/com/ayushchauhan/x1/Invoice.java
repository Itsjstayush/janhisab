package com.ayushchauhan.x1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.ayushchauhan.x1.Adapter.CustomerAdapter;
import com.ayushchauhan.x1.api.RetrofitClient;
import com.ayushchauhan.x1.model.Customer;
import com.ayushchauhan.x1.model.CustomerResponse;
import com.ayushchauhan.x1.model.GetTransactionResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Invoice extends AppCompatActivity{

    public RecyclerView recyclerView;
    public ArrayList<Customer> Customer;
    public int user_id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);
        getSupportActionBar().hide();

        recyclerView = (RecyclerView) findViewById(R.id.rv_customer);


        getCustomerData();

        }

    public void getCustomerData() {
        SharedPreferences sp = getSharedPreferences("user_data",MODE_PRIVATE);
        user_id = Integer.parseInt(sp.getString("uid",""));


        //Animation Loader
        //data in loader show
        //data out  dismiss

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Customer Loading...");
        progressDialog.show();

        Call<CustomerResponse> call = RetrofitClient.getInstance().getApi().getcustomer(user_id);
        call.enqueue(new Callback<CustomerResponse>() {
            @Override
            public void onResponse(Call<CustomerResponse> call, Response<CustomerResponse> response) {
                if(response.isSuccessful()){
                    CustomerResponse customerResponse = response.body();
                    if(customerResponse.isStatus()){
                        Toast.makeText(Invoice.this, ""+customerResponse.getMessage(), Toast.LENGTH_SHORT).show();


                        Customer = customerResponse.getData();
                        CustomerAdapter adapter = new CustomerAdapter(getApplicationContext(),Customer);

                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        recyclerView.setHasFixedSize(true);
                        progressDialog.dismiss();

                    }else{
                        Toast.makeText(Invoice.this, ""+customerResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<CustomerResponse> call, Throwable t) {
                Toast.makeText(Invoice.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

                progressDialog.dismiss();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(Invoice.this,More.class);
        startActivity(i);
        finish();
    }
}