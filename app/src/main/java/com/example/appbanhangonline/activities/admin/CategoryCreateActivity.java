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

    // binding: Đối tượng ActivityCategoryCreateBinding được tạo bởi Data Binding
    // để liên kết các thành phần giao diện trong layout của activity.
    private ActivityCategoryCreateBinding binding;

    // Phương thức onCreate: Được gọi khi activity được tạo.
    // Trong phương thức này, các bước khởi tạo và thiết lập giao diện và xử lý sự kiện được thực hiện.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Khởi tạo binding
        // Tạo đối tượng binding bằng cách inflate layout ActivityCategoryCreateBinding và gán cho biến binding.
        // Điều này liên kết layout XML "activity_category_create" với activity.
        binding = ActivityCategoryCreateBinding.inflate(getLayoutInflater());

        // Lấy ra root view từ binding.
        // Root view là view gốc của layout activity.
        View rootView = binding.getRoot();

        // Thiết lập layout gốc cho activity bằng cách sử dụng root view.
        setContentView(rootView);

        // Xử lý sự kiện khi nút "Back" được click:
        // Gắn một OnClickListener cho nút "Back" (binding.btnBack).
        // Khi nút được click, một Intent được tạo để chuyển đến CategoryActivity, và sau đó kết thúc activity hiện tại.
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                startActivity(new Intent(getApplicationContext(), CategoryActivity.class));
                finish();
            }
        });

        // Xử lý sự kiện khi nút "Category Create" được click:
        // Gắn một OnClickListener cho nút "Category Create" (binding.btnCategoryCreate).
        binding.btnCategoryCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // Khi nút được click, thực hiện các bước sau:

                // Lấy giá trị của trường nhập liệu etCategoryName và chuyển đổi thành chuỗi (categoryName).
                String categoryName = String.valueOf(binding.etCategoryName.getText());

                // Tạo một đối tượng Category mới và đặt tên danh mục (categoryName) cho đối tượng.
                Category category = new Category();
                category.setCategoryName(categoryName);

                // Sử dụng CategoryHandler để thêm danh mục vào CSDL bằng phương thức add().
                CategoryHandler.gI(CategoryCreateActivity.this).add(category);

                // Hiển thị thông báo "Thêm thành công" lên màn hình.
                Toast.makeText(CategoryCreateActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();

                // Tạo một Intent để chuyển đến CategoryActivity và sau đó kết thúc activity hiện tại.
                startActivity(new Intent(getApplicationContext(), CategoryActivity.class));
                finish();
            }
        });
    }
}