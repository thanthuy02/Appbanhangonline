package com.example.appbanhangonline.adapters.admin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhangonline.databinding.ItemCustomerBillBinding;
import com.example.appbanhangonline.databinding.ItemUserAdminBinding;
import com.example.appbanhangonline.models.Bill;
import com.example.appbanhangonline.models.User;

import java.util.ArrayList;
import java.util.List;

public class CustomerBillAdapter extends RecyclerView.Adapter<CustomerBillAdapter.ViewHolder>{
    private List<Bill> bills;
    private CustomerBillAdapter.OnItemClickListener listener;
    public CustomerBillAdapter() {
        bills = new ArrayList<>();
    }

    public void setCustomerBill(List<Bill> bills) {
        this.bills = bills;
        notifyDataSetChanged();
    }
    public interface OnItemClickListener {
        void onItemClick(Bill bill);
    }
    public void setOnItemClickListener(CustomerBillAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public CustomerBillAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemCustomerBillBinding itemCustomerBillBinding = ItemCustomerBillBinding.inflate(
                LayoutInflater.from(parent.getContext()),parent,false
        );
        return new CustomerBillAdapter.ViewHolder(itemCustomerBillBinding);
    }

    @Override
    public void onBindViewHolder(final CustomerBillAdapter.ViewHolder holder, final int position) {
        Bill bill = bills.get(position);
        holder.setDataBill(bill);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(bill);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return bills.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ItemCustomerBillBinding binding;

        public ViewHolder(ItemCustomerBillBinding itemCustomerBillBinding){
            super(itemCustomerBillBinding.getRoot());
            binding = itemCustomerBillBinding;
        }

        public void setDataBill(Bill bill) {
            binding.txtCustomerBillName.setText("Bill: #"+bill.getBillID());
            binding.txtCustomerBillTime.setText(bill.getCreatedAt());
        }
    }
}
