package com.example.appbanhangonline.activities.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.appbanhangonline.databinding.ActivityCategoryCreateBinding;
import com.example.appbanhangonline.dbhandler.CategoryHandler;
import com.example.appbanhangonline.models.Category;

import java.util.List;

public class CategoryCreateActivity extends AppCompatActivity {

    private ActivityCategoryCreateBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Khởi tạo binding
        binding = ActivityCategoryCreateBinding.inflate(getLayoutInflater());
        // Lấy ra root view từ binding
        View rootView = binding.getRoot();
        // Gán layout cho Activity
        setContentView(rootView);
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                startActivity(new Intent(getApplicationContext(), CategoryActivity.class));
                finish();
            }
        });

        binding.btnCategoryCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String categoryName = String.valueOf(binding.etCategoryName.getText());
                Category category = new Category();
                category.setCategoryName(categoryName);
                CategoryHandler.gI(CategoryCreateActivity.this).add(category);

                // Them thanh cong
                Toast.makeText(CategoryCreateActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), CategoryActivity.class));
                finish();
            }
        });
    }
}