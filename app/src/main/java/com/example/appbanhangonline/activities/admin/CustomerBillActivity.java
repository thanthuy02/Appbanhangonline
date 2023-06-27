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

    // Khai báo biến customerBillAdapter.
    // Đây là một adapter dùng để hiển thị danh sách hóa đơn của khách hàng.
    private CustomerBillAdapter customerBillAdapter;

    // Khai báo biến binding.
    // Đây là đối tượng được tạo tự động bởi Data Binding để liên kết các thành phần giao diện trong layout của activity.
    private ActivityCustomerBillBinding binding;

    // Phương thức onCreate: Được gọi khi activity được tạo.
    // Trong phương thức này, các bước khởi tạo và thiết lập giao diện và xử lý sự kiện được thực hiện.

    // khai báo biến userId, userName
    private int userId;
    private String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Tạo đối tượng binding bằng cách inflate layout ActivityCustomerBillBinding và gán cho biến binding.
        // Điều này liên kết layout XML "activity_customer_bill" với activity.
        binding = ActivityCustomerBillBinding.inflate(getLayoutInflater());

        // Lấy ra root view từ binding.
        // Root view là view gốc của layout activity.
        View rootView = binding.getRoot();

        // Thiết lập layout gốc cho activity bằng cách sử dụng root view.
        setContentView(rootView);

        // Lấy thông tin người dùng từ Intent:
        // Sử dụng SharedPreferences để lấy thông tin người dùng đã được lưu trữ từ activity trước đó.
        // Cụ thể, lấy userId, userName và userEmail từ SharedPreferences.
        SharedPreferences sharedPreferences = getSharedPreferences("UserPreferences", Context.MODE_PRIVATE);
        userId = sharedPreferences.getInt("userId", 0); // Giá trị mặc định 0 nếu không tìm thấy KEY "userId"
        userName = sharedPreferences.getString("userName", ""); // Giá trị mặc định là chuỗi rỗng nếu không tìm thấy KEY "userName"

        // Đặt tiêu đề trang (tvTitleCustomerBill) bằng userName.
        binding.tvTitleCustomerBill.setText(userName);

        // Thiết lập Layout Manager cho RecyclerView:
        // Tạo một LayoutManager mới và gán cho RecyclerView (binding.rcvCustomerBill) để quy định cách hiển thị danh sách.
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rcvCustomerBill.setLayoutManager(layoutManager);

        // Lấy danh sách hóa đơn của người dùng từ BillHandler thông qua phương thức getByUserID(userId).
        // Danh sách này sẽ được hiển thị trong RecyclerView.
        List<Bill> bills = BillHandler.gI(this).getByUserID(userId);

        // Thiết lập Adapter cho RecyclerView:
        // Tạo một CustomerBillAdapter mới và gán cho RecyclerView (binding.rcvCustomerBill).
        // Sau đó, gọi phương thức setCustomerBill() trên adapter để đặt danh sách hóa đơn.
        customerBillAdapter = new CustomerBillAdapter();
        binding.rcvCustomerBill.setAdapter(customerBillAdapter);
        customerBillAdapter.setCustomerBill(bills);

        // Xử lý sự kiện khi nút "Back" được click:
        // Gắn một OnClickListener cho nút "Back" (binding.btnBack).
        // Khi nút được click, một Intent được tạo để chuyển đến CustomerActivity, và sau đó kết thúc activity hiện tại.
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                startActivity(new Intent(getApplicationContext(), CustomerActivity.class));
                finish();
            }
        });

        // Xử lý sự kiện khi người dùng nhấp vào một hóa đơn trong danh sách:
        // Gắn một OnItemClickListener cho adapter (customerBillAdapter).
        // Khi một hóa đơn được nhấp vào, phương thức onItemClick() sẽ được gọi và truyền hóa đơn được chọn.
        customerBillAdapter.setOnItemClickListener(new CustomerBillAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Bill bill) {
                showBillDetails(rootView, bill);
            }
        });
    }

    // Phương thức showBillDetails():
    // Nhận vào một view (v) và một đối tượng Bill để hiển thị chi tiết hóa đơn.
    // Trong phương thức này, thông tin hóa đơn được lưu vào SharedPreferences,
    // sau đó tạo một Intent để chuyển đến BillDetailsActivity và bắt đầu một activity mới.
    private void showBillDetails(View v, Bill bill) {
        // Lưu thông tin người dùng vào SharedPreferences
        SharedPreferences sharedPreferences = v.getContext().getSharedPreferences("BillPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("billId", bill.getBillID());
        editor.putInt("userId", userId);
        editor.putString("userName", userName);
        editor.apply();

        Intent intent = new Intent(v.getContext(), CustomerBillDetailsActivity.class);
        v.getContext().startActivity(intent);
    }
}