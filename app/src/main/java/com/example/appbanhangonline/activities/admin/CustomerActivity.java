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
import com.example.appbanhangonline.activities.login.LoginActivity;
import com.example.appbanhangonline.adapters.admin.CustomerAdminAdapter;
import com.example.appbanhangonline.databinding.ActivityAdminUserBinding;
import com.example.appbanhangonline.dbhandler.UserHandle;
import com.example.appbanhangonline.models.Category;
import com.example.appbanhangonline.models.User;

import java.util.ArrayList;
import java.util.List;

public class CustomerActivity extends Activity {

    // Khai báo biến customerAdminAdapter.
    // Đây là một adapter dùng để hiển thị danh sách khách hàng.
    private CustomerAdminAdapter customerAdminAdapter;

    // Khai báo biến binding.
    // Đây là đối tượng được tạo tự động bởi Data Binding để liên kết các thành phần giao diện trong layout của activity.
    private ActivityAdminUserBinding binding;

    // Phương thức onCreate: Được gọi khi activity được tạo.
    // Trong phương thức này, các bước khởi tạo và thiết lập giao diện và xử lý sự kiện được thực hiện.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Tạo đối tượng binding bằng cách inflate layout ActivityAdminUserBinding và gán cho biến binding.
        // Điều này liên kết layout XML "activity_admin_user" với activity.
        binding = ActivityAdminUserBinding.inflate(getLayoutInflater());

        // Lấy ra root view từ binding.
        // Root view là view gốc của layout activity.
        View rootView = binding.getRoot();

        // Thiết lập layout gốc cho activity bằng cách sử dụng root view.
        setContentView(rootView);

        // Thiết lập Layout Manager cho RecyclerView:
        // Tạo một LayoutManager mới và gán cho RecyclerView (binding.rcvUserAdmin) để quy định cách hiển thị danh sách.
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rcvUserAdmin.setLayoutManager(layoutManager);

        // Lấy danh sách khách hàng từ UserHandle thông qua phương thức getAll().
        // Danh sách này sẽ được hiển thị trong RecyclerView.
        List<User> users = UserHandle.gI(this).getAll();

        // Thiết lập Adapter cho RecyclerView:
        // Tạo một CustomerAdminAdapter mới và gán cho RecyclerView (binding.rcvUserAdmin).
        // Sau đó, gọi phương thức setCustomer() trên adapter để đặt danh sách khách hàng.
        customerAdminAdapter = new CustomerAdminAdapter();
        binding.rcvUserAdmin.setAdapter(customerAdminAdapter);
        customerAdminAdapter.setCustomer(users);

        // Xử lý sự kiện khi nút "Back" được click:
        // Gắn một OnClickListener cho nút "Back" (binding.btnBack).
        // Khi nút được click, một Intent được tạo để chuyển đến MenuAdminActivity, và sau đó kết thúc activity hiện tại.
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                startActivity(new Intent(getApplicationContext(), MenuAdminActivity.class));
                finish();
            }
        });

        // Xử lý sự kiện khi người dùng nhấp vào mục trong danh sách khách hàng:
        // Gắn một OnItemClickListener cho adapter (customerAdminAdapter).
        // Khi một mục được nhấp vào, phương thức onItemClick() sẽ được gọi và truyền thông tin người dùng được chọn.
        customerAdminAdapter.setOnItemClickListener(new CustomerAdminAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(User user) {
                // Xử lý sự kiện khi người dùng nhấp vào mục ở đây
                showUserDetails(rootView, user);

            }
        });
    }

    // Phương thức showUserDetails():
    // Nhận vào một view (v) và một đối tượng User để hiển thị chi tiết người dùng.
    // Trong phương thức này, thông tin người dùng được lưu vào SharedPreferences,
    // sau đó tạo một Intent để chuyển đến CustomerBillActivity và bắt đầu một activity mới.
    private void showUserDetails(View v, User user) {
        // Lưu thông tin người dùng vào SharedPreferences
        SharedPreferences sharedPreferences = v.getContext().getSharedPreferences("UserPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("userId", user.getUserID());
        editor.putString("userName", user.getUsername());
        editor.apply();

        // Chuyển đến CategoryActivity
        Intent intent = new Intent(v.getContext(), CustomerBillActivity.class);
        v.getContext().startActivity(intent);
    }
}
