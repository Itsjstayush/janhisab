package com.ayushchauhan.x1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ayushchauhan.x1.Adapter.TransactionAdapter;
import com.ayushchauhan.x1.api.RetrofitClient;
import com.ayushchauhan.x1.model.ExtraData;
import com.ayushchauhan.x1.model.GetTransactionResponse;
import com.ayushchauhan.x1.model.Transaction;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class dashboard extends AppCompatActivity {

    public RecyclerView recyclerView;
    public ArrayList<Transaction> transactions;
    public int user_id;
    public TextView credit,debit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().hide();
        credit=(TextView)findViewById(R.id.credit);
        debit=(TextView)findViewById(R.id.debit);

        getSupportActionBar().hide();

        recyclerView = (RecyclerView) findViewById(R.id.rv_transactions);


        getTransactionData();

    }

    public void getTransactionData() {
        SharedPreferences sp = getSharedPreferences("user_data", MODE_PRIVATE);
        user_id = Integer.parseInt(sp.getString("uid", ""));


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
                if (response.isSuccessful()) {
                    GetTransactionResponse getTransactionResponse = response.body();
                    if (getTransactionResponse.isStatus()) {
                        Toast.makeText(dashboard.this, "" + getTransactionResponse.getMessage(), Toast.LENGTH_SHORT).show();


                        transactions = getTransactionResponse.getData();

                        ArrayList<com.ayushchauhan.x1.model.ExtraData>extraData=getTransactionResponse.getExtra_data();
                        String debitamount=extraData.get(0).getAmount();
                        String creditamount=extraData.get(1).getAmount();
                        credit.setText(creditamount);
                        debit.setText(debitamount);
                        int balance=Integer.parseInt(creditamount)+Integer.parseInt(debitamount);

                        TextView tv_balance=(TextView) findViewById(R.id.tv_balance);
                        tv_balance.setText(""+balance);


                        TransactionAdapter adapter = new TransactionAdapter(getApplicationContext(), transactions);

                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        recyclerView.setHasFixedSize(true);
                        progressDialog.dismiss();

                    } else {
                        Toast.makeText(dashboard.this, "" + getTransactionResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetTransactionResponse> call, Throwable t) {
                Toast.makeText(dashboard.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

                progressDialog.dismiss();
            }
        });
    }

        public void addcustomer (View view){
            Intent i = new Intent(dashboard.this, addcustomer.class);
            startActivity(i);
            finish();
        }


        public void gototransaction (View view){
            Intent i = new Intent(dashboard.this, GetAllTransactionActivity.class);
            startActivity(i);
            finish();
        }

        public void gotomore (View view){
            Intent i = new Intent(dashboard.this, More.class);
            startActivity(i);
            finish();
        }

        public void gotoprofile (View view){
            Intent i = new Intent(dashboard.this, profile.class);
            startActivity(i);
            finish();
        }

        public void Addtransaction (View view){
            Intent i = new Intent(dashboard.this, addtransaction.class);
            startActivity(i);
            finish();
        }
    }
