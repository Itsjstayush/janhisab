package com.ayushchauhan.x1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ayushchauhan.x1.Adapter.TransactionAdapter;
import com.ayushchauhan.x1.api.RetrofitClient;
import com.ayushchauhan.x1.model.GetTransactionResponse;
import com.ayushchauhan.x1.model.Transaction;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetAllTransactionActivity extends AppCompatActivity {



    public RecyclerView recyclerView;
    public ArrayList<Transaction> transactions;
    public int user_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all_transaction);
        getSupportActionBar().hide();

        recyclerView = (RecyclerView) findViewById(R.id.rv_transactions);


        getTransactionData();
    }
    public void getTransactionData() {
        SharedPreferences sp = getSharedPreferences("user_data",MODE_PRIVATE);
        user_id = Integer.parseInt(sp.getString("uid",""));


        //Animation Loader
        //data in loader show
        //data out  dismiss

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Transaction Loading...");
        progressDialog.show();

        Call<GetTransactionResponse> call = RetrofitClient.getInstance().getApi().getAllTransactiondata(user_id);
        call.enqueue(new Callback<GetTransactionResponse>() {
            @Override
            public void onResponse(Call<GetTransactionResponse> call, Response<GetTransactionResponse> response) {
                if(response.isSuccessful()){
                    GetTransactionResponse getTransactionResponse = response.body();
                    if(getTransactionResponse.isStatus()){
                        Toast.makeText(GetAllTransactionActivity.this, ""+getTransactionResponse.getMessage(), Toast.LENGTH_SHORT).show();


                        transactions = getTransactionResponse.getData();
                        TransactionAdapter adapter = new TransactionAdapter(getApplicationContext(),transactions);

                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        recyclerView.setHasFixedSize(true);
                        progressDialog.dismiss();

                    }else{
                        Toast.makeText(GetAllTransactionActivity.this, ""+getTransactionResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetTransactionResponse> call, Throwable t) {
                Toast.makeText(GetAllTransactionActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

                progressDialog.dismiss();
            }
        });

    }

    public void toaddtransaction(View view) {
        Intent i = new Intent(GetAllTransactionActivity.this, addtransaction.class);
        startActivity(i);
        finish();
    }

    public void back(View view) {
        Intent i = new Intent(GetAllTransactionActivity.this,dashboard.class);
        startActivity(i);
        finish();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(GetAllTransactionActivity.this,dashboard.class);
        startActivity(i);
        finish();
    }
    public void gotohome(View view) {
        Intent i = new Intent(GetAllTransactionActivity.this,dashboard.class);
        startActivity(i);
        finish();
    }

    public void gotoprofile(View view) {
        Intent i = new Intent(GetAllTransactionActivity.this,profile.class);
        startActivity(i);
        finish();
    }

    public void gotomore(View view) {
        Intent i = new Intent(GetAllTransactionActivity.this,More.class);
        startActivity(i);
        finish();
    }
}