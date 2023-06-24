package com.example.appbanhangonline.activities.user;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.activities.login.LoginActivity;
import com.example.appbanhangonline.adapters.CartAdapter;
import com.example.appbanhangonline.models.Cart;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    private RecyclerView rvCart;

    private TextView total;

    private Button btnPay;

    private Cart cart = new Cart();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        total = findViewById(R.id.total);
        rvCart = findViewById(R.id.rvCart);
        btnPay = findViewById(R.id.btnPay);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
        rvCart.setLayoutManager(layoutManager);

        CartAdapter adapter = new CartAdapter(this, this.cart);
        rvCart.setAdapter(adapter);

        total.setText("Thành tiền: " + this.cart.getTotal_price());

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPaymentDialog();
            }
        });
    }

    public void updateData() {
        total.setText("Thành tiền: " + this.cart.getTotal_price());
    }

    private void showPaymentDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thanh toán");
        builder.setMessage("Thanh toán khi nhận hàng.")
                .setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showSuccessToast();
                    }
                })
                .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showSuccessToast() {
        Toast.makeText(this, "Đặt hàng thành công!", Toast.LENGTH_SHORT).show();
    }
}


