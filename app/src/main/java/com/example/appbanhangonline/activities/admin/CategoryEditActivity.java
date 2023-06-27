package com.example.appbanhangonline.activities.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.appbanhangonline.databinding.ActivityCategoryEditBinding;
import com.example.appbanhangonline.dbhandler.CategoryHandler;
import com.example.appbanhangonline.models.Category;

public class CategoryEditActivity extends AppCompatActivity {

    // binding: Đối tượng ActivityCategoryEditBinding được tạo bởi Data Binding
    // để liên kết các thành phần giao diện trong layout của activity.
    private ActivityCategoryEditBinding binding;

    // Phương thức onCreate: Được gọi khi activity được tạo.
    // Trong phương thức này, các bước khởi tạo và thiết lập giao diện và xử lý sự kiện được thực hiện.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Tạo đối tượng binding bằng cách inflate layout ActivityCategoryEditBinding và gán cho biến binding.
        // Điều này liên kết layout XML "activity_category_edit" với activity.
        binding = ActivityCategoryEditBinding.inflate(getLayoutInflater());

        // Lấy ra root view từ binding.
        // Root view là view gốc của layout activity.
        View rootView = binding.getRoot();

        // Thiết lập layout gốc cho activity bằng cách sử dụng root view.
        setContentView(rootView);

        // Lấy thông tin người dùng từ intent:
        // Sử dụng SharedPreferences để lấy thông tin đã được lưu trữ từ CategoryActivity.
        // Đối tượng SharedPreferences được tạo với tên "CategoryPreferences" và chế độ Context.MODE_PRIVATE.
        SharedPreferences sharedPreferences = getSharedPreferences("CategoryPreferences", Context.MODE_PRIVATE);
        int categoryId = sharedPreferences.getInt("categoryId", 0); // Giá trị mặc định 0 nếu không tìm thấy KEY "categoryId"
        String categoryName = sharedPreferences.getString("categoryName", ""); // Giá trị mặc định là chuỗi rỗng nếu không tìm thấy KEY "categoryName"


        Log.d("TAG_categoryId", "onCreate: " + categoryId);
        // Thiết lập giá trị của trường nhập liệu etCategoryName trong layout thành categoryName.
        binding.etCategoryName.setText(categoryName);

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

        // Xử lý sự kiện khi nút "Category Edit" được click:
        // Gắn một OnClickListener cho nút "Category Edit" (binding.btnCategoryEdit).
        binding.btnCategoryEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // Khi nút được click, thực hiện các bước sau:

                // Kiểm tra nếu trường nhập liệu etCategoryName là null,
                // hiển thị thông báo "Không được để trống" lên màn hình.
                if (binding.etCategoryName.getText() == null) {
                    Toast.makeText(CategoryEditActivity.this, "Không được để trống", Toast.LENGTH_SHORT).show();
                }

                Log.d("TAG_categoryId", "onClick: " + categoryId);
                // Lấy giá trị của trường nhập liệu etCategoryName và chuyển đổi thành chuỗi (categoryName).
                String categoryName = String.valueOf(binding.etCategoryName.getText()).trim();

                // Tạo một đối tượng Category mới với tên danh mục và ID danh mục đã được cập nhật.
                Category newCategory = new Category(categoryName, categoryId);

                // Sử dụng CategoryHandler để cập nhật danh mục trong CSDL bằng phương thức update().
                CategoryHandler.gI(CategoryEditActivity.this).update(newCategory);

                // Hiển thị thông báo "Sửa thành công" lên màn hình.
                Toast.makeText(CategoryEditActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();

                // Tạo một Intent để chuyển đến CategoryActivity và sau đó kết thúc activity hiện tại.
                startActivity(new Intent(getApplicationContext(), CategoryActivity.class));
                finish();
            }
        });
    }


}

