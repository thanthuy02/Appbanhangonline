package com.example.appbanhangonline.activities.admin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.adapters.admin.BillAdminAdapter;
import com.example.appbanhangonline.adapters.admin.CustomerBillAdapter;
import com.example.appbanhangonline.databinding.ActivityAdminBillBinding;
import com.example.appbanhangonline.databinding.ActivityCustomerBillBinding;
import com.example.appbanhangonline.dbhandler.BillHandler;
import com.example.appbanhangonline.models.Bill;

import java.util.ArrayList;
import java.util.List;

public class BillActivity extends Activity {
    private BillAdminAdapter billAdminAdapter;
    private ActivityAdminBillBinding binding;
    private List<Bill> bills;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Khởi tạo binding
        binding = ActivityAdminBillBinding.inflate(getLayoutInflater());
        // Lấy ra root view từ binding
        View rootView = binding.getRoot();
        // Gán layout cho Activity
        setContentView(rootView);

        // Thiết lập Layout Manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rcvBill.setLayoutManager(layoutManager);
        bills = new ArrayList<>();

        List<Bill> bills = BillHandler.gI(this).getAllBill();
        // Thiết lập Adapter
        billAdminAdapter = new BillAdminAdapter();
        binding.rcvBill.setAdapter(billAdminAdapter);
        billAdminAdapter.setBill(bills);

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                startActivity(new Intent(getApplicationContext(), MenuAdminActivity.class));
                finish();
            }
        });
        billAdminAdapter.setOnItemClickListener(new BillAdminAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Bill bill) {
                // Xử lý sự kiện khi người dùng nhấp vào mục ở đây
                startActivity(new Intent(getApplicationContext(), BillAdminAdapter.class));
                finish();
            }
        });
    }
}
