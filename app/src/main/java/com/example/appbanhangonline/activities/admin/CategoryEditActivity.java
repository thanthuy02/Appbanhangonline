package com.example.appbanhangonline.activities.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.databinding.ActivityCategoryCreateBinding;
import com.example.appbanhangonline.databinding.ActivityCategoryEditBinding;
import com.example.appbanhangonline.dbhandler.CategoryHandle;
import com.example.appbanhangonline.models.Category;

public class CategoryEditActivity extends AppCompatActivity {
    private ActivityCategoryEditBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Khởi tạo binding
        binding = ActivityCategoryEditBinding.inflate(getLayoutInflater());
        // Lấy ra root view từ binding
        View rootView = binding.getRoot();
        // Gán layout cho Activity
        setContentView(rootView);

        // Lấy thông tin người dùng từ intent
        SharedPreferences sharedPreferences = getSharedPreferences("UserPreferences", Context.MODE_PRIVATE);
        int categoryId = sharedPreferences.getInt("categoryId", 0); // Giá trị mặc định 0 nếu không tìm thấy khóa
        String categoryName = sharedPreferences.getString("categoryName", ""); // Giá trị mặc định là chuỗi rỗng nếu không tìm thấy khóa


        Log.d("TAG_categoryId", "onCreate: "+categoryId);
        binding.etCategoryName.setText(categoryName);

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                startActivity(new Intent(getApplicationContext(), CategoryActivity.class));
                finish();
            }
        });

        binding.btnCategoryEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if(binding.etCategoryName.getText() == null){
                    Toast.makeText(CategoryEditActivity.this, "Không được để trống", Toast.LENGTH_SHORT).show();
                }
                Log.d("TAG_categoryId", "onClick: "+categoryId);
                String categoryName = String.valueOf(binding.etCategoryName.getText()).trim();
                Category newCategory = new Category(categoryName, categoryId);
                CategoryHandle.gI().update(newCategory);

                // Sua thanh cong
                Toast.makeText(CategoryEditActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), CategoryActivity.class));
                finish();
            }
        });
    }



}

