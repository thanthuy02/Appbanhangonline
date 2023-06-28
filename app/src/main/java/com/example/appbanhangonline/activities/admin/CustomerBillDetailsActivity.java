package com.example.appbanhangonline.activities.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class CustomerBillDetailsActivity extends AppCompatActivity {

    // Khai báo biến binding.
    // Đây là đối tượng được tạo tự động bởi Data Binding để liên kết các thành phần giao diện trong layout của activity.
    private ActivityCustomerBillDetailsBinding binding;

    // Phương thức onCreate: Được gọi khi activity được tạo.
    // Trong phương thức này, các bước khởi tạo và thiết lập giao diện và xử lý sự kiện được thực hiện.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Tạo đối tượng binding bằng cách inflate layout ActivityCustomerBillDetailsBinding và gán cho biến binding.
        // Điều này liên kết layout XML "activity_customer_bill_details" với activity.
        binding = ActivityCustomerBillDetailsBinding.inflate(getLayoutInflater());

        // Lấy ra root view từ binding.
        // Root view là view gốc của layout activity.
        View rootView = binding.getRoot();

        // Thiết lập layout gốc cho activity bằng cách sử dụng root view.
        setContentView(rootView);

        // Lấy thông tin hóa đơn từ SharedPreferences:
        // Sử dụng SharedPreferences để lấy thông tin hóa đơn đã được lưu trữ từ activity trước đó.
        // Cụ thể, lấy billId từ SharedPreferences.
        SharedPreferences sharedPreferences = getSharedPreferences("BillPreferences", Context.MODE_PRIVATE);
        int billId = sharedPreferences.getInt("billId", 0); // Giá trị mặc định 0 nếu không tìm thấy KEY "billId"
        int userId = sharedPreferences.getInt("userId", 0); // Giá trị mặc định 0 nếu không tìm thấy KEY "userId"
        String userName = sharedPreferences.getString("userName", ""); // Giá trị mặc định chuỗi rỗng nếu không tìm thấy KEY "userName"

        // Đặt tiêu đề trang (tvTitleBill) với chuỗi "Bill: #" được nối với billId.
        binding.tvTitleBill.setText("Bill: #" + billId);

        // Hiển thị thông tin chi tiết đơn hàng
        displayBillDetails(billId);

        // Xử lý sự kiện khi nút "Back" được click:
        // Gắn một OnClickListener cho nút "Back" (binding.btnBack).
        // Khi nút được click, một Intent được tạo để chuyển đến CustomerBillActivity.
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Log.d("TAG_userId", "onClick: "+ userId);
                Log.d("TAG_userName", "onClick: "+ userName);

                // Lưu thông tin người dùng vào SharedPreferences
                SharedPreferences sharedPreferences = v.getContext().getSharedPreferences("UserPreferences", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("userId", userId);
                editor.putString("userName", userName);
                editor.apply();

                // Chuyển đến CategoryActivity
                startActivity(new Intent(getApplicationContext(), CustomerBillActivity.class));
                finish();
            }
        });
    }

    // Phương thức displayBillDetails():
    // Nhận vào một billId để hiển thị thông tin chi tiết của hóa đơn.
    private void displayBillDetails(int billId) {

        // Sử dụng BillHandler để lấy thông tin hóa đơn từ CSDL:
        // Tạo một đối tượng BillHandler và sử dụng phương thức getBillById(billId)
        // để lấy đối tượng Bill tương ứng với billId.
        BillHandler billHandler = new BillHandler(this);
        Bill bill = billHandler.getBillById(billId);

        // Kiểm tra xem bill có khác null hay không:
        // Nếu không null, tiếp tục hiển thị thông tin chi tiết hóa đơn lên giao diện.
        if (bill != null) {

            // Sử dụng UserHandler để lấy thông tin người dùng từ CSDL:
            // Tạo một đối tượng UserHandler và sử dụng phương thức getById()
            // để lấy thông tin người dùng (tương ứng với bill.getBillCustomerID()).
            UserHandle userHandler = new UserHandle(this);
            User user = userHandler.getById(bill.getBillCustomerID());

            // Định dạng giá tiền theo đơn vị tiền Việt
            NumberFormat currencyFormatTotalPrice = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
            String formattedTotalPrice = currencyFormatTotalPrice.format(bill.getBillTotalPrice());


            // Hiển thị thông tin chi tiết hóa đơn lên giao diện:
            // Sử dụng các phương thức setText() của binding để đặt giá trị cho các thành phần giao diện
            // (tvBillID, tvCustomerName, tvTotalPrice, tvCreatedAt) với thông tin tương ứng của hóa đơn và người dùng.
            binding.tvBillID.setText("Bill ID: #" + bill.getBillID());
            binding.tvCustomerName.setText("Tên khách hàng: " + user.getUsername());
            binding.tvTotalPrice.setText("Tổng tiền: " + formattedTotalPrice);
            binding.tvCreatedAt.setText("Đơn ngày: " + bill.getCreatedAt());

            // Sử dụng DetailBillHandler để lấy thông tin chi tiết hóa đơn từ CSDL:
            // Tạo một đối tượng DetailBillHandler và sử dụng phương thức getBillDetailsByBillID()
            // để lấy danh sách chi tiết hóa đơn (tương ứng với billId).
            DetailBillHandler detailBillHandler = new DetailBillHandler(this);
            List<DetailBill> detailBills = detailBillHandler.getBillDetailsByBillID(billId);

            // Hiển thị chi tiết hóa đơn (sản phẩm) lên giao diện:
            // Sử dụng một vòng lặp for để duyệt qua danh sách detailBills và lấy thông tin sản phẩm và chi tiết từ ProductHandler.
            // Sau đó, sử dụng StringBuilder để tạo một chuỗi details chứa thông tin chi tiết của tất cả các sản phẩm trong hóa đơn.
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

            // Đặt giá trị của details vào thành phần giao diện tvDetails để hiển thị danh sách chi tiết hóa đơn.
            binding.tvDetails.setText(details.toString());
        }
    }
}