package com.example.appbanhangonline.activities.user;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.adapters.CartAdapter;
import com.example.appbanhangonline.dbhandler.BillHandler;
import com.example.appbanhangonline.dbhandler.DetailBillHandler;
import com.example.appbanhangonline.models.Bill;
import com.example.appbanhangonline.models.Cart;
import com.example.appbanhangonline.models.DetailBill;
import com.example.appbanhangonline.models.Product;
import com.example.appbanhangonline.models.ProductRepository;

import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

public class CartActivity extends AppCompatActivity {
    RecyclerView rvCart;
    TextView total;
    LinearLayout emptyCart;
    Button btnPay;
    ImageButton btnBack;
    Cart cart = new Cart();
    CartAdapter cartAdapter;

    ProductRepository productRepository;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // khởi tạo đối tượng
        init();

        // quay lại trang home
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, HomeUserActivity.class);
                // FLAG_ACTIVITY_CLEAR_TOP : xóa hết những activity trên cùng của HomeUserActivity
                // ra khỏi ngăn xếp chỉ để lại cái cuối cùng (activity ban đầu đc tạo)
                // FLAG_ACTIVITY_SINGLE_TOP : đảm bảo rằng nếu HomeUserActivity đã tồn tại ở trên cùng ngăn xếp,
                // phiên bản mới sẽ ko được tạo ra mà sẽ sd lại HomeUserActivity ban đầu
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
        NumberFormat currencyFormatPrice = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        String formattedPrice = currencyFormatPrice.format(this.cart.getTotal_price());
        total.setText("Thành tiền: " + formattedPrice);
    }

    // nếu ko có sp nào trong giỏ hàng thì hiển thị giỏ trống
    public void empty_cart(){
        if(Cart.cartList.isEmpty()){
            emptyCart.setVisibility(View.VISIBLE);
        }
    }

    // khởi tạo các đối tượng
    public void init(){
        total = findViewById(R.id.total);
        rvCart = findViewById(R.id.rvCart);
        btnPay = findViewById(R.id.btnPay);
        btnBack = findViewById(R.id.imgBtnBack);
        emptyCart = findViewById(R.id.emptyCart);
        productRepository = new ProductRepository();

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

    // xử lý việc thanh toán
    private void processPayment() {
        Bill bill = new Bill();
        bill.setBillCustomerID(HomeUserActivity.user_id);
        bill.setBillTotalPrice(cart.getTotal_price());
        Date now = new Date();
        bill.setCreatedAt(now.toString());

        BillHandler billHandle = new BillHandler(this);

        if (billHandle.insertBill(bill) == 1) {
            int bill_id = billHandle.getBillIdNew();
            // Thành công sẽ thêm hóa đơn chi tiết
            Toast.makeText(this, "Đặt hàng thành công!", Toast.LENGTH_SHORT).show();

            // Lặp qua các phần tử trong cartList để lấy sản phẩm
            for (Integer productId : cart.cartList.keySet()) {
                Product product = productRepository.getProductById(productId);
                int quantity = cart.cartList.getOrDefault(productId, 0);

                DetailBill detailBill = new DetailBill();
                detailBill.setBillID(bill_id);
                detailBill.setProductId(product.getProductID());
                detailBill.setQuantity(quantity);
                detailBill.setPrice(cart.getLinePrice(product));

                DetailBillHandler detailBillHandler = new DetailBillHandler(this);
                detailBillHandler.insertDetailBill(detailBill);
            }

            load();
        } else {
            Toast.makeText(this, "Lỗi đặt hàng! Hãy thử lại sau!", Toast.LENGTH_SHORT).show();
        }
    }

    //    xóa sp trong giỏ hàng và đặt lại tổng tiền = 0
    public void load() {
        cartAdapter.notifyDataSetChanged();
        cartAdapter.delete();
        cart.setTotal_price(0);
        NumberFormat currencyFormatPrice = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        String formattedPrice = currencyFormatPrice.format(this.cart.getTotal_price());
        total.setText("Thành tiền: " + formattedPrice);
        emptyCart.setVisibility(View.VISIBLE);
    }
}

