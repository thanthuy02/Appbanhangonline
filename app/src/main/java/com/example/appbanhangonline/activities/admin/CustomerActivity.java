package com.example.appbanhangonline.activities.admin;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.adapters.admin.CustomerAdminAdapter;
import com.example.appbanhangonline.databinding.ActivityAdminUserBinding;
import com.example.appbanhangonline.models.User;

import java.util.ArrayList;
import java.util.List;

public class CustomerActivity extends Activity {
    private CustomerAdminAdapter customerAdminAdapter;
    private ActivityAdminUserBinding binding;
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

        // Tạo danh sách dữ liệu
        List<User> users = new ArrayList<>();
        users.add(new User(0,"kiet kiet kiet kiet kiet v v v v v v v vkiet kiet kiet kiet kiet kiet v v v v v v v vkiet", "0987654321", "aush", "e", "ppp", "admin"));
        users.add(new User(0,"kiet kiet kiet kiet kiet v v v v v v v vkiet kiet kiet kiet kiet kiet v v v v v v v vkiet", "0987654321", "aush", "e", "ppp", "admin"));
        users.add(new User(0,"kiet kiet kiet kiet kiet v v v v v v v vkiet kiet kiet kiet kiet kiet v v v v v v v vkiet", "0987654321", "aush", "e", "ppp", "admin"));
        users.add(new User(0,"kiet kiet kiet kiet kiet v v v v v v v vkiet kiet kiet kiet kiet kiet v v v v v v v vkiet", "0987654321", "aush", "e", "ppp", "admin"));
        // Thiết lập Adapter
        customerAdminAdapter = new CustomerAdminAdapter(users);
        binding.rcvUserAdmin.setAdapter(customerAdminAdapter);
    }
}
