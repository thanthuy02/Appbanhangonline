package com.example.appbanhangonline.activities.admin;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.adapters.admin.CategoryAdminAdapter;
import com.example.appbanhangonline.adapters.admin.CustomerAdminAdapter;
import com.example.appbanhangonline.databinding.ActivityAdminCategoryBinding;
import com.example.appbanhangonline.databinding.ActivityAdminUserBinding;
import com.example.appbanhangonline.models.Category;
import com.example.appbanhangonline.models.User;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends Activity {
    private CategoryAdminAdapter categoryAdminAdapter;
    private ActivityAdminCategoryBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Khởi tạo binding
        binding = ActivityAdminCategoryBinding.inflate(getLayoutInflater());
        // Lấy ra root view từ binding
        View rootView = binding.getRoot();
        // Gán layout cho Activity
        setContentView(rootView);

        // Thiết lập Layout Manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rcvCategoryAdmin.setLayoutManager(layoutManager);

        // Tạo danh sách dữ liệu
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("kiet kiet kiet kiet kiet v v v v v v v vkiet kiet kiet kiet kiet kiet v v v v v v v vkiet", 0));
        categories.add(new Category("kiet kiet kiet kiet kiet v v v v v v v vkiet kiet kiet kiet kiet kiet v v v v v v v vkiet", 0));
        categories.add(new Category("kiet kiet kiet kiet kiet v v v v v v v vkiet kiet kiet kiet kiet kiet v v v v v v v vkiet", 0));
        categories.add(new Category("kiet kiet kiet kiet kiet v v v v v v v vkiet kiet kiet kiet kiet kiet v v v v v v v vkiet", 0));
        // Thiết lập Adapter
        categoryAdminAdapter = new CategoryAdminAdapter(categories);
        binding.rcvCategoryAdmin.setAdapter(categoryAdminAdapter);
    }
}
