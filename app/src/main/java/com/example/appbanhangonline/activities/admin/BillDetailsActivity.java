package com.example.appbanhangonline.activities.admin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.databinding.ActivityAdminBillBinding;
import com.example.appbanhangonline.databinding.ActivityBillDetailsBinding;
import com.example.appbanhangonline.dbhandler.BillHandler;
import com.example.appbanhangonline.dbhandler.DetailBillHandler;
import com.example.appbanhangonline.dbhandler.ProductHandler;
import com.example.appbanhangonline.dbhandler.UserHandle;
import com.example.appbanhangonline.models.Bill;
import com.example.appbanhangonline.models.DetailBill;
import com.example.appbanhangonline.models.Product;
import com.example.appbanhangonline.models.User;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class BillDetailsActivity extends AppCompatActivity {

    // Khai báo biến binding của kiểu ActivityBillDetailsBinding.
    // Đây là đối tượng được tạo tự động bởi Data Binding để liên kết các thành phần giao diện trong layout của activity.
    private ActivityBillDetailsBinding binding;

    // Phương thức onCreate: Được gọi khi activity được tạo.
    // Trong phương thức này, các bước khởi tạo và thiết lập giao diện và dữ liệu được thực hiện.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Khởi tạo binding
        // Tạo đối tượng binding bằng cách inflate layout ActivityBillDetailsBinding và gán cho biến binding.
        // Điều này liên kết layout XML "activity_bill_details" với activity.
        binding = ActivityBillDetailsBinding.inflate(getLayoutInflater());

        // Lấy ra root view từ binding.
        // Root view là view gốc của layout activity.
        View rootView = binding.getRoot();

        // Gán layout cho Activity
        // Thiết lập layout gốc cho activity bằng cách sử dụng root view.
        setContentView(rootView);

        // Lấy thông tin hóa đơn từ SharedPreferences:
        // Sử dụng SharedPreferences để lấy giá trị billId được lưu trữ trước đó.
        SharedPreferences sharedPreferences = getSharedPreferences("BillPreferences", Context.MODE_PRIVATE);
        int billId = sharedPreferences.getInt("billId", 0); // Giá trị mặc định 0 nếu không tìm thấy KEY "billId"

        // Đặt tiêu đề của hóa đơn lên TextView tvTitleBill.
        binding.tvTitleBill.setText("Bill: #" + billId);

        // Gọi phương thức displayBillDetails() để hiển thị thông tin chi tiết của hóa đơn.
        displayBillDetails(billId);

        // Xử lý sự kiện khi nút "Back" được click:
        // Gắn một OnClickListener cho nút "Back" (binding.btnBack).
        // Khi nút được click, một Intent được tạo để chuyển đến BillActivity, và sau đó kết thúc activity hiện tại.
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                startActivity(new Intent(getApplicationContext(), BillActivity.class));
                finish();
            }
        });
    }

    // Phương thức displayBillDetails(int billId):
    // Hiển thị chi tiết của hóa đơn trên giao diện.
    private void displayBillDetails(int billId) {

        // Lấy thông tin hóa đơn từ CSDL:
        // Sử dụng BillHandler để lấy thông tin của hóa đơn dựa trên billId.
        BillHandler billHandler = new BillHandler(this);
        Bill bill = billHandler.getBillById(billId);

        // Kiểm tra xem hóa đơn có tồn tại hay không (if (bill != null)).
        if (bill != null) {

            // Lấy thông tin khách hàng từ CSDL:
            // Sử dụng UserHandler để lấy thông tin khách hàng dựa trên billCustomerID của hóa đơn.
            UserHandle userHandler = new UserHandle(this);
            User user = userHandler.getById(bill.getBillCustomerID());

            // Định dạng giá tiền theo đơn vị tiền Việt
            NumberFormat currencyFormatTotalPrice = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
            String formattedTotalPrice = currencyFormatTotalPrice.format(bill.getBillTotalPrice());

            // Hiển thị thông tin hóa đơn lên giao diện:
            // Thiết lập các TextView (tvBillID, tvCustomerName, tvTotalPrice, tvCreatedAt)
            // để hiển thị thông tin hóa đơn và thông tin khách hàng.
            binding.tvBillID.setText("Bill ID: #" + bill.getBillID());
            binding.tvCustomerName.setText("Tên khách hàng: " + user.getUsername());
            binding.tvTotalPrice.setText("Tổng tiền: " + formattedTotalPrice);
            binding.tvCreatedAt.setText("Đơn ngày: " + bill.getCreatedAt());

            // Lấy chi tiết hóa đơn từ CSDL:
            // Sử dụng DetailBillHandler để lấy danh sách chi tiết hóa đơn dựa trên billId.
            DetailBillHandler detailBillHandler = new DetailBillHandler(this);
            List<DetailBill> detailBills = detailBillHandler.getBillDetailsByBillID(billId);

            // Hiển thị chi tiết hóa đơn (sản phẩm) lên giao diện:
            // Sử dụng vòng lặp để lấy thông tin của từng chi tiết hóa đơn và hiển thị lên TextView tvDetails.
            StringBuilder details = new StringBuilder();
            for (DetailBill detailBill : detailBills) {
                ProductHandler productHandler = new ProductHandler(this);
                Product product = productHandler.getProductById(detailBill.getProductId());
                String productName = product.getProductName();
                int quantity = detailBill.getQuantity();
                int price = detailBill.getPrice();

                // Định dạng giá tiền theo đơn vị tiền Việt
                NumberFormat currencyFormatPrice = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
                String formattedPrice = currencyFormatPrice.format(price);

                String detail = "Sản phẩm: " + productName + "\n"
                        + "Số lượng: " + quantity + "\n"
                        + "Giá: " + formattedPrice + "\n\n";

                details.append(detail);
            }
            binding.tvDetails.setText(details.toString());
        }
    }
}
