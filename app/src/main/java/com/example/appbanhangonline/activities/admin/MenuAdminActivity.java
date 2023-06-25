package com.example.appbanhangonline.activities.admin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.activities.login.LoginActivity;
import com.example.appbanhangonline.databinding.ActivityAdminCategoryBinding;
import com.example.appbanhangonline.databinding.ActivityAdminMainBinding;

public class MenuAdminActivity extends Activity {
    private ActivityAdminMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Khởi tạo binding
        binding = ActivityAdminMainBinding.inflate(getLayoutInflater());
        // Lấy ra root view từ binding
        View rootView = binding.getRoot();
        // Gán layout cho Activity
        setContentView(rootView);

        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });

        binding.cardCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CategoryActivity.class);
                startActivity(i);
            }
        });

        binding.cardProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ProductActivity.class));
            }
        });

        binding.cardCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CustomerActivity.class);
                startActivity(i);
            }
        });

        binding.cardBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), BillActivity.class);
                startActivity(i);
            }
        });

        binding.cardChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), RevenueActivity.class);
                startActivity(i);
            }
        });

    }
}
