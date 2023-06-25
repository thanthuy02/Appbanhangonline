package com.example.appbanhangonline.activities.admin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.activities.login.LoginActivity;
import com.example.appbanhangonline.adapters.admin.CustomerAdminAdapter;
import com.example.appbanhangonline.databinding.ActivityAdminUserBinding;
import com.example.appbanhangonline.dbhandler.UserHandle;
import com.example.appbanhangonline.models.User;

import java.util.ArrayList;
import java.util.List;

public class CustomerActivity extends Activity {
    private CustomerAdminAdapter customerAdminAdapter;
    private ActivityAdminUserBinding binding;
    private List<User> users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Khởi tạo binding
        binding = ActivityAdminUserBinding.inflate(getLayoutInflater());
        // Lấy ra root view từ binding
        View rootView = binding.getRoot();
        // Gán layout cho Activity
        setContentView(rootView);

        // Thiết lập Layout Manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rcvUserAdmin.setLayoutManager(layoutManager);
        users = new ArrayList<>();

        List<User> users = UserHandle.gI(this).getAll();
        // Thiết lập Adapter
        customerAdminAdapter = new CustomerAdminAdapter();
        binding.rcvUserAdmin.setAdapter(customerAdminAdapter);
        customerAdminAdapter.setCategories(users);

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                startActivity(new Intent(getApplicationContext(), MenuAdminActivity.class));
                finish();
            }
        });
        customerAdminAdapter.setOnItemClickListener(new CustomerAdminAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(User user) {
                // Xử lý sự kiện khi người dùng nhấp vào mục ở đây
                startActivity(new Intent(getApplicationContext(), CustomerBillActivity.class));
                finish();
            }
        });
    }
}
