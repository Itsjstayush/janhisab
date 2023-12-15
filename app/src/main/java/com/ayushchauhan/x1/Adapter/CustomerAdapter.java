package com.ayushchauhan.x1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ayushchauhan.x1.R;
import com.ayushchauhan.x1.model.Customer;
import com.ayushchauhan.x1.model.Transaction;

import java.util.ArrayList;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder>{


    public CustomerAdapter(Context context, ArrayList<Customer> customers) {
        this.context = context;
        this.customers = customers;
    }

    public Context context;
    public ArrayList<Customer> customers;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_customer_list,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_cust_name.setText(customers.get(position).getName());
        holder.tv_cust_email.setText(customers.get(position).getEmail());
        holder.tv_cust_mobile.setText(customers.get(position).getMobile());
        holder.tv_cust_address.setText(customers.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return customers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_cust_name,tv_cust_email,tv_cust_mobile,tv_cust_address;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_cust_name = (TextView) itemView.findViewById(R.id.tv_cust_name1);
            tv_cust_email = (TextView) itemView.findViewById(R.id.tv_cust_email);
            tv_cust_mobile = (TextView) itemView.findViewById(R.id.tv_cust_mobile1);
            tv_cust_address = (TextView) itemView.findViewById(R.id.tv_cust_address);


        }
    }
}
