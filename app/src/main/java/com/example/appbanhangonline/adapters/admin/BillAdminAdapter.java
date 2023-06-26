package com.example.appbanhangonline.adapters.admin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhangonline.databinding.ItemBillAdminBinding;
import com.example.appbanhangonline.databinding.ItemCustomerBillBinding;
import com.example.appbanhangonline.models.Bill;

import java.util.ArrayList;
import java.util.List;

public class BillAdminAdapter extends RecyclerView.Adapter<BillAdminAdapter.ViewHolder>{
    private List<Bill> bills;
    private BillAdminAdapter.OnItemClickListener listener;
    public BillAdminAdapter() {
        bills = new ArrayList<>();
    }

    public void setBill(List<Bill> bills) {
        this.bills = bills;
        notifyDataSetChanged();
    }
    public interface OnItemClickListener {
        void onItemClick(Bill bill);
    }
    public void setOnItemClickListener(BillAdminAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public BillAdminAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemBillAdminBinding itemBillAdminBinding = ItemBillAdminBinding.inflate(
                LayoutInflater.from(parent.getContext()),parent,false
        );
        return new BillAdminAdapter.ViewHolder(itemBillAdminBinding);
    }

    @Override
    public void onBindViewHolder(final BillAdminAdapter.ViewHolder holder, final int position) {
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
        public ItemBillAdminBinding binding;

        public ViewHolder(ItemBillAdminBinding itemBillAdminBinding){
            super(itemBillAdminBinding.getRoot());
            binding = itemBillAdminBinding;
        }

        public void setDataBill(Bill bill) {
            binding.txtBillName.setText("Bill: #"+bill.getBillID());
            binding.txtBillTime.setText(bill.getCreatedAt());
        }
    }
}
