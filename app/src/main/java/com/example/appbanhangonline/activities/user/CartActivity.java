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
import com.example.appbanhangonline.adapters.CartAdapter;
import com.example.appbanhangonline.dbhandler.BillHandler;
import com.example.appbanhangonline.dbhandler.DetailBillHandler;
import com.example.appbanhangonline.models.Bill;
import com.example.appbanhangonline.models.Cart;
import com.example.appbanhangonline.models.DetailBill;
import com.example.appbanhangonline.models.Product;

import java.util.Date;

public class CartActivity extends AppCompatActivity {
    RecyclerView rvCart;
    TextView total, emptyCart;
    Button btnPay;
    ImageButton btnBack;
    Cart cart;
    CartAdapter cartAdapter;

    BillHandler billHandle;

    DetailBillHandler detailBillHandler;

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
        cart = new Cart();
        emptyCart = findViewById(R.id.emptyCart);
        billHandle = BillHandler.gI(this);
        detailBillHandler = new DetailBillHandler(this);


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
                            processPayment();
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

    // Xử lý thanh toán và lưu thông tin vào bill và bill detail
    private void processPayment() {
        Bill bill = new Bill();
        bill.setBillCustomerID(MainActivity.user_id);
        bill.setBillTotalPrice(cart.getTotal_price());
        Date now = new Date();
        bill.setCreatedAt(now.toString());

        if (billHandle.insertBill(bill) == 1) {
            int bill_id = billHandle.getBillIdNew();
            Toast.makeText(this, "Đặt hàng thành công!", Toast.LENGTH_SHORT).show();

            // Lặp qua các phần tử trong cartList để lấy sản phẩm
            for (Integer productId : cart.cartList.keySet()) {
                Product product = cart.getProductByOrder(productId);
                int quantity = cart.cartList.getOrDefault(productId, 0);

                DetailBill detailBill = new DetailBill();
                detailBill.setBillID(bill_id);
                detailBill.setProductId(product.getProductID());
                detailBill.setQuantity(quantity);
                detailBill.setPrice(product.getPrice());

                detailBillHandler.insertDetailBill(detailBill);
            }

            // Xóa toàn bộ sản phẩm trong giỏ hàng
            Cart.cartList.clear();

            // Cập nhật giao diện giỏ hàng
            load();
        } else {
            Toast.makeText(this, "Lỗi đặt hàng! Hãy thử lại sau!", Toast.LENGTH_SHORT).show();
        }
    }

    // Cập nhật giao diện giỏ hàng
    public void load() {
        cartAdapter.notifyDataSetChanged();
        cartAdapter.updateUI();
        cart.setTotal_price(0);
        total.setText("Thành tiền: " + this.cart.getTotal_price());
        emptyCart.setVisibility(View.VISIBLE);
    }
}


