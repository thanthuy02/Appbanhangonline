package com.example.appbanhangonline.activities.admin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.activities.login.LoginActivity;

public class MenuAdminActivity extends Activity {
    ImageButton btnLogout;
    Button btnCategory;
    Button btnProduct;
    Button btnCustomer;
    Button btnBill;
    Button btnStatistic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        btnLogout = (ImageButton) findViewById(R.id.btnLogout);
        btnCategory = (Button) findViewById(R.id.btnCategoryAdmin);
        btnProduct = (Button) findViewById(R.id.btnProductAdmin);
        btnCustomer = (Button) findViewById(R.id.btnCustomerAdmin);
        btnBill = (Button) findViewById(R.id.btnBillAdmin);
        btnStatistic = (Button) findViewById(R.id.btnStatisticAdmin);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });

        btnCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CategoryActivity.class);
                startActivity(i);
            }
        });

        btnProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ProductActivity.class));
            }
        });

        btnCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CustomerActivity.class);
                startActivity(i);
            }
        });

        btnBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), BillActivity.class);
                startActivity(i);
            }
        });

        btnStatistic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), RevenueActivity.class);
                startActivity(i);
            }
        });

    }
}
