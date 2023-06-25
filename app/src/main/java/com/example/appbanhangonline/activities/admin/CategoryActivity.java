package com.example.appbanhangonline.activities.admin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhangonline.adapters.admin.CategoryAdminAdapter;
import com.example.appbanhangonline.databinding.ActivityAdminCategoryBinding;
import com.example.appbanhangonline.dbhandler.CategoryHandler;
import com.example.appbanhangonline.models.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends Activity {
    private CategoryAdminAdapter categoryAdminAdapter;
    private ActivityAdminCategoryBinding binding;
    private CategoryHandler categoryHandle;
    private List<Category> categories;

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
        categories = new ArrayList<>();

        List<Category> categories = CategoryHandler.gI(this).getAll();

        // Thiết lập Adapter
        categoryAdminAdapter = new CategoryAdminAdapter();
        binding.rcvCategoryAdmin.setAdapter(categoryAdminAdapter);
        categoryAdminAdapter.setCategories(categories);

        // onClick
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                startActivity(new Intent(getApplicationContext(), MenuAdminActivity.class));
                finish();
            }
        });

        binding.btnCreateProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                startActivity(new Intent(getApplicationContext(), CategoryCreateActivity.class));
                finish();
            }
        });

        categoryAdminAdapter.setListener(new CategoryAdminAdapter.CategoryAdminAdapterListener() {
            @Override
            public void onDeleteClicked(int position, Category category) {
                // Xử lý sự kiện khi nút xóa được nhấn
                // xoá mục tại vị trí position
//                int categoryIdToRemove = category.getCategoryID(); // Id của phần tử cần xóa
//                for (int i = 0; i < categories.size(); i++) {
//                    Category category1 = categories.get(i);
//                    if (category1.getCategoryID() == categoryIdToRemove) {
//                        categories.remove(i);
//                        break; // Thoát khỏi vòng lặp sau khi xóa phần tử
//                    }
//                }
                // remove db
                boolean isSuccess = CategoryHandler.gI(CategoryActivity.this).delete(category.getCategoryID());
                // if remove success
                if (isSuccess) {
                    // update giao dien
                    categories.remove(category);
                    categoryAdminAdapter.setCategories(categories);
                    Toast.makeText(CategoryActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CategoryActivity.this, "Co loi xay ra", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onEditClicked(int position, Category category) {
                // Xử lý sự kiện khi nút chỉnh sửa được nhấn
                // Ví dụ: mở cửa sổ chỉnh sửa mục tại vị trí position
                showUserDetails(rootView, category);
            }
        });
    }

    private void showUserDetails(View v, Category category) {
        // Lưu thông tin người dùng vào SharedPreferences
        SharedPreferences sharedPreferences = v.getContext().getSharedPreferences("UserPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("categoryId", category.getCategoryID());
        editor.putString("categoryName", category.getCategoryName());
        editor.apply();

        // Chuyển đến CategoryActivity
        Intent intent = new Intent(v.getContext(), CategoryEditActivity.class);
        v.getContext().startActivity(intent);
    }
}
