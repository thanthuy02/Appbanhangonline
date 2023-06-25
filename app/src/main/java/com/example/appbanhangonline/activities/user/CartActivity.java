package com.example.appbanhangonline.activities.user;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.activities.MainActivity;
import com.example.appbanhangonline.activities.admin.MenuAdminActivity;
import com.example.appbanhangonline.activities.login.LoginActivity;
import com.example.appbanhangonline.adapters.CartAdapter;
import com.example.appbanhangonline.dbhandler.BillHandle;
import com.example.appbanhangonline.dbhandler.DetailBillHandler;
import com.example.appbanhangonline.models.Bill;
import com.example.appbanhangonline.models.Cart;
import com.example.appbanhangonline.models.DetailBill;
import com.example.appbanhangonline.models.Product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    RecyclerView rvCart;
    TextView total, emptyCart;
    Button btnPay;
    ImageButton btnBack;
    Cart cart = new Cart();
    CartAdapter cartAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Anhxa();

        // quay lại trang home
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, HomeUserActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        // Nếu giỏ hàng ko có sp nào thì hiển thị là Giỏ hàng trống
        empty_cart();

        // đổ dữ liệu vào rvCart
        cartAdapter = new CartAdapter(this, this.cart);
        rvCart.setAdapter(cartAdapter);

        // tổng tiền hóa đơn
        updateData();

        //Khi user bấm vào nút thanh toán
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPaymentDialog();
            }
        });
    }

    // cập nhật lại tổng tiền của giỏ hàng
    public void updateData() {
        total.setText("Thành tiền: " + this.cart.getTotal_price());
    }

    // nếu ko có sp nào trong giỏ hàng thì hiển thị giỏ trống
    public void empty_cart(){
        if(Cart.cartList.isEmpty()){
            emptyCart.setVisibility(View.VISIBLE);
        }
    }

    // ánh xạ các đối tượng
    public void Anhxa(){
        total = findViewById(R.id.total);
        rvCart = findViewById(R.id.rvCart);
        btnPay = findViewById(R.id.btnPay);
        btnBack = findViewById(R.id.imgBtnBack);
        emptyCart = findViewById(R.id.emptyCart);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
        rvCart.setLayoutManager(layoutManager);


    }

    // Xử lý sự kiện khi user bấm nút thanh toán
    private void showPaymentDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thanh toán");

        if(Cart.cartList.isEmpty()){
            builder.setMessage("Không có sản phẩm nào trong giỏ hàng.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
        } else {
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
        }
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // thông báo đặt hàng thành công
    private void showSuccessToast() {
        Toast.makeText(this, "Đặt hàng thành công!", Toast.LENGTH_SHORT).show();
        ((CartActivity) this).load();
    }

//    xóa sp trong giỏ hàng và đặt lại tổng tiền = 0
    public void load() {
        cartAdapter.notifyDataSetChanged();
        cartAdapter.updateUI();
        cart.setTotal_price(0);
        total.setText("Thành tiền: " + this.cart.getTotal_price());
        emptyCart.setVisibility(View.VISIBLE);
    }
}


