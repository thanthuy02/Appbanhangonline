package com.example.appbanhangonline.activities.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.adapters.admin.CustomerAdminAdapter;
import com.example.appbanhangonline.adapters.admin.CustomerBillAdapter;
import com.example.appbanhangonline.databinding.ActivityAdminUserBinding;
import com.example.appbanhangonline.databinding.ActivityCustomerBillBinding;
import com.example.appbanhangonline.dbhandler.BillHandler;
import com.example.appbanhangonline.dbhandler.UserHandle;
import com.example.appbanhangonline.models.Bill;
import com.example.appbanhangonline.models.User;

import java.util.ArrayList;
import java.util.List;

public class CustomerBillActivity extends AppCompatActivity {
    private CustomerBillAdapter customerBillAdapter;
    private ActivityCustomerBillBinding binding;
    private List<Bill> bills;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Khởi tạo binding
        binding = ActivityCustomerBillBinding.inflate(getLayoutInflater());
        // Lấy ra root view từ binding
        View rootView = binding.getRoot();
        // Gán layout cho Activity
        setContentView(rootView);
        // Lấy thông tin người dùng từ intent
        SharedPreferences sharedPreferences = getSharedPreferences("UserPreferences", Context.MODE_PRIVATE);
        int userId = sharedPreferences.getInt("userId", 0); // Giá trị mặc định 0 nếu không tìm thấy khóa
        String userName = sharedPreferences.getString("userName", ""); // Giá trị mặc định là chuỗi rỗng nếu không tìm thấy khóa
        String userEmail = sharedPreferences.getString("userEmail", ""); // Giá trị mặc định là chuỗi rỗng nếu không tìm thấy khóa

        binding.tvTitleCustomerBill.setText(userName);

        // Thiết lập Layout Manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rcvCustomerBill.setLayoutManager(layoutManager);
        bills = new ArrayList<>();

        List<Bill> bills = BillHandler.gI(this).getByUserID(userId);
        // Thiết lập Adapter
        customerBillAdapter = new CustomerBillAdapter();
        binding.rcvCustomerBill.setAdapter(customerBillAdapter);
        customerBillAdapter.setCustomerBill(bills);

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                startActivity(new Intent(getApplicationContext(), CustomerActivity.class));
                finish();
            }
        });
        customerBillAdapter.setOnItemClickListener(new CustomerBillAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Bill bill) {
                showBillDetails(rootView, bill);
            }
        });
    }

    private void showBillDetails(View v, Bill bill) {
        // Lưu thông tin người dùng vào SharedPreferences
        SharedPreferences sharedPreferences = v.getContext().getSharedPreferences("BillPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("billId", bill.getBillID());
        editor.apply();

        Intent intent = new Intent(v.getContext(), BillDetailsActivity.class);
        v.getContext().startActivity(intent);
    }
}