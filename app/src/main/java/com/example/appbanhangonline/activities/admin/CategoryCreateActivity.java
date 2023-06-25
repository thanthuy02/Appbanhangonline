package com.example.appbanhangonline.activities.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.databinding.ActivityAdminCategoryBinding;
import com.example.appbanhangonline.databinding.ActivityCategoryCreateBinding;
import com.example.appbanhangonline.dbhandler.CategoryHandle;
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
        List<Category> categories = CategoryHandle.gI().getAll();
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
                int maxCategoryId = 0;
                for (Category category : categories) {
                    if (category.getCategoryID() > maxCategoryId) {
                        maxCategoryId = category.getCategoryID();
                    }
                }
                int newCategoryId = maxCategoryId + 1;
                String categoryName = String.valueOf(binding.etCategoryName.getText());

                Log.d("TAG_newCategory", "onClick: "+newCategoryId);
                CategoryHandle.gI().add(new Category(categoryName, newCategoryId));

                // Them thanh cong
                Toast.makeText(CategoryCreateActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), CategoryActivity.class));
                finish();
            }
        });
    }
}