package com.example.appbanhangonline.activities.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.databinding.ActivityBillDetailsBinding;
import com.example.appbanhangonline.databinding.ActivityCustomerBillBinding;
import com.example.appbanhangonline.databinding.ActivityCustomerBillDetailsBinding;
import com.example.appbanhangonline.dbhandler.BillHandler;
import com.example.appbanhangonline.dbhandler.DetailBillHandler;
import com.example.appbanhangonline.dbhandler.ProductHandler;
import com.example.appbanhangonline.dbhandler.UserHandle;
import com.example.appbanhangonline.models.Bill;
import com.example.appbanhangonline.models.DetailBill;
import com.example.appbanhangonline.models.Product;
import com.example.appbanhangonline.models.User;

import java.util.List;

public class CustomerBillDetailsActivity extends AppCompatActivity {

    private ActivityCustomerBillDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Khởi tạo binding
        binding = ActivityCustomerBillDetailsBinding.inflate(getLayoutInflater());
        // Lấy ra root view từ binding
        View rootView = binding.getRoot();
        // Gán layout cho Activity
        setContentView(rootView);

        // Lấy thông tin người dùng từ SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("BillPreferences", Context.MODE_PRIVATE);
        int billId = sharedPreferences.getInt("billId", 0);

        binding.tvTitleBill.setText("Bill: #" + billId);
        // Hiển thị thông tin chi tiết đơn hàng
        displayBillDetails(billId);

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                startActivity(new Intent(getApplicationContext(), CustomerBillActivity.class));
                finish();
            }
        });
    }

    private void displayBillDetails(int billId) {
        // Sử dụng handler để lấy thông tin đơn hàng từ CSDL
        BillHandler billHandler = new BillHandler(this);
        Bill bill = billHandler.getBillById(billId);

        if (bill != null) {
            UserHandle userHandler = new UserHandle(this);
            User user = userHandler.getById(bill.getBillCustomerID());
            // Hiển thị thông tin đơn hàng lên giao diện
            binding.tvBillID.setText("Bill ID: #" + bill.getBillID());
            binding.tvCustomerName.setText("Tên khách hàng: " + user.getUsername());
            binding.tvTotalPrice.setText("Tổng tiền: " + bill.getBillTotalPrice());
            binding.tvCreatedAt.setText("Đơn ngày: " + bill.getCreatedAt());

            // Sử dụng handler để lấy chi tiết đơn hàng từ CSDL
            DetailBillHandler detailBillHandler = new DetailBillHandler(this);
            List<DetailBill> detailBills = detailBillHandler.getBillDetailsByBillID(billId);

            // Hiển thị chi tiết đơn hàng (sản phẩm)
            StringBuilder details = new StringBuilder();
            for (DetailBill detailBill : detailBills) {
                ProductHandler productHandler = new ProductHandler(this);
                Product product = productHandler.getProductById(detailBill.getProductId());
                String productName = product.getProductName();
                int quantity = detailBill.getQuantity();
                int price = detailBill.getPrice();

                String detail = "Sản phẩm: " + productName + "\n"
                        + "Số lượng: " + quantity + "\n"
                        + "Giá: " + price + "\n\n";

                details.append(detail);
            }
            binding.tvDetails.setText(details.toString());
        }
    }
}