package com.example.appbanhangonline.activities.admin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.adapters.admin.BillAdminAdapter;
import com.example.appbanhangonline.adapters.admin.CustomerBillAdapter;
import com.example.appbanhangonline.databinding.ActivityAdminBillBinding;
import com.example.appbanhangonline.databinding.ActivityCustomerBillBinding;
import com.example.appbanhangonline.dbhandler.BillHandler;
import com.example.appbanhangonline.models.Bill;
import com.example.appbanhangonline.models.User;

import java.util.ArrayList;
import java.util.List;

public class BillActivity extends Activity {

    // Khai báo biến
    // billAdminAdapter: Đối tượng BillAdminAdapter để quản lý danh sách hóa đơn trong RecyclerView.
    private BillAdminAdapter billAdminAdapter;

    // binding: Đối tượng ActivityAdminBillBinding được tạo bởi Data Binding để liên kết các thành phần giao diện trong layout của activity.
    private ActivityAdminBillBinding binding;

    // Phương thức onCreate: Được gọi khi activity được tạo.
    // Trong phương thức này, các bước khởi tạo và thiết lập giao diện và dữ liệu được thực hiện.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Khởi tạo binding
        // Tạo đối tượng binding bằng cách inflate layout ActivityAdminBillBinding và gán cho biến binding.
        // Điều này liên kết layout XML "activity_admin_bill" với activity .
        binding = ActivityAdminBillBinding.inflate(getLayoutInflater());

        // Lấy ra root view từ binding
        View rootView = binding.getRoot();

        // Gán layout cho Activity
        // setContentView(rootView):
        // Thiết lập layout gốc cho activity bằng cách sử dụng root view.
        setContentView(rootView);

        // Khởi tạo LayoutManager cho RecyclerView:
        // Sử dụng LinearLayoutManager để quản lý cách hiển thị các item trong RecyclerView.
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rcvBill.setLayoutManager(layoutManager);

        // Lấy danh sách hóa đơn từ BillHandler thông qua phương thức getAllBill().
        List<Bill> bills = BillHandler.gI(this).getAllBill();

        // Thiết lập Adapter
        // Khởi tạo BillAdminAdapter và thiết lập adapter cho RecyclerView:
        // Tạo đối tượng billAdminAdapter và gán adapter cho RecyclerView thông qua binding.rcvBill.setAdapter(billAdminAdapter).
        // Sau đó, gọi billAdminAdapter.setBill(bills) để thiết lập danh sách hóa đơn cho adapter.
        billAdminAdapter = new BillAdminAdapter();
        binding.rcvBill.setAdapter(billAdminAdapter);
        billAdminAdapter.setBill(bills);

        // Xử lý sự kiện khi nút "Back" được click:
        // Gắn một OnClickListener cho nút "Back" (binding.btnBack).
        // Khi nút được click, một Intent được tạo để chuyển đến MenuAdminActivity.
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                startActivity(new Intent(getApplicationContext(), MenuAdminActivity.class));
                finish();
            }
        });

        // Xử lý sự kiện khi một hóa đơn được click:
        // Gắn một OnItemClickListener cho billAdminAdapter.
        // Khi một hóa đơn được click, phương thức onItemClick() được gọi và gọi phương thức showBillDetails() để hiển thị chi tiết hóa đơn.
        billAdminAdapter.setOnItemClickListener(new BillAdminAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Bill bill) {
                showBillDetails(rootView, bill);
            }
        });
    }

    // Phương thức showBillDetails():
    // Lưu thông tin của hóa đơn vào SharedPreferences và tạo một Intent để chuyển đến BillDetailsActivity.
    private void showBillDetails(View v, Bill bill) {
        // Lưu thông tin người dùng vào SharedPreferences
        SharedPreferences sharedPreferences = v.getContext().getSharedPreferences("BillPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("billId", bill.getBillID());
        editor.apply();

        Intent intent = new Intent(v.getContext(), BillDetailsActivity.class);
        v.getContext().startActivity(intent);
    }
}
