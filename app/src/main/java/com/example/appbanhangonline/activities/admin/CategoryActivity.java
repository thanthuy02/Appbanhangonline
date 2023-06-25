package com.example.appbanhangonline.activities.admin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.activities.login.LoginActivity;
import com.example.appbanhangonline.adapters.admin.CategoryAdminAdapter;
import com.example.appbanhangonline.adapters.admin.CustomerAdminAdapter;
import com.example.appbanhangonline.databinding.ActivityAdminCategoryBinding;
import com.example.appbanhangonline.databinding.ActivityAdminUserBinding;
import com.example.appbanhangonline.dbhandler.CategoryHandle;
import com.example.appbanhangonline.dbhandler.LoginHandler;
import com.example.appbanhangonline.models.Category;
import com.example.appbanhangonline.models.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends Activity {
    private CategoryAdminAdapter categoryAdminAdapter;
    private ActivityAdminCategoryBinding binding;
    private CategoryHandle categoryHandle;
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

        // Tạo danh sách dữ liệu
        categories = new ArrayList<>();
//        CategoryHandle.gI().add(new Category("Sach", 1));
//        CategoryHandle.gI().add(new Category("But", 2));
//        CategoryHandle.gI().add(new Category("Vo", 3));
        List<Category> categories = CategoryHandle.gI().getAll();


        // Thiết lập Adapter
        Log.d("TAG_categories", "onCreate: "+categories);
        categoryAdminAdapter = new CategoryAdminAdapter(categories);
        binding.rcvCategoryAdmin.setAdapter(categoryAdminAdapter);

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
                int categoryIdToRemove = category.getCategoryID(); // Id của phần tử cần xóa

                for (int i = 0; i < categories.size(); i++) {
                    Category category1 = categories.get(i);
                    if (category1.getCategoryID() == categoryIdToRemove) {
                        categories.remove(i);
                        break; // Thoát khỏi vòng lặp sau khi xóa phần tử
                    }
                }
                CategoryHandle.gI().delete(category.getCategoryID());
                // remove from db
                categoryAdminAdapter.notifyDataSetChanged();
                // update adapter but dont set dt
                Toast.makeText(CategoryActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
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
