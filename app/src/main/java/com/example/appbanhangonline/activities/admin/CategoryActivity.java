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

    // Khai báo biến categoryAdminAdapter.
    // Đây là adapter để hiển thị danh sách các danh mục.
    private CategoryAdminAdapter categoryAdminAdapter;

    // binding: Đối tượng ActivityAdminCategoryBinding.
    // Đây là đối tượng được tạo tự động bởi Data Binding để liên kết các thành phần giao diện trong layout của activity.
    private ActivityAdminCategoryBinding binding;

    // Phương thức onCreate: Được gọi khi activity được tạo.
    // Trong phương thức này, các bước khởi tạo và thiết lập giao diện và dữ liệu được thực hiện.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Tạo đối tượng binding bằng cách inflate layout ActivityAdminCategoryBinding và gán cho biến binding.
        // Liên kết layout XML "activity_admin_category" với activity.
        binding = ActivityAdminCategoryBinding.inflate(getLayoutInflater());

        // Lấy ra root view từ binding.
        // Root view là view gốc của layout activity.
        View rootView = binding.getRoot();

        // Thiết lập layout gốc cho activity bằng cách sử dụng root view.
        setContentView(rootView);

        // Thiết lập Layout Manager cho RecyclerView:
        // Tạo một LayoutManager mới (LinearLayoutManager) và thiết lập cho RecyclerView (binding.rcvCategoryAdmin).
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rcvCategoryAdmin.setLayoutManager(layoutManager);

        // Lấy danh sách các danh mục từ CategoryHandler:
        // Sử dụng CategoryHandler để lấy danh sách các danh mục từ CSDL.
        List<Category> categories = CategoryHandler.gI(this).getAll();

        // Thiết lập Adapter cho RecyclerView:
        // Khởi tạo categoryAdminAdapter và thiết lập nó cho RecyclerView (binding.rcvCategoryAdmin).
        // Sau đó, gọi phương thức setCategories() trên adapter để đưa danh sách các danh mục vào adapter.
        categoryAdminAdapter = new CategoryAdminAdapter();
        binding.rcvCategoryAdmin.setAdapter(categoryAdminAdapter);
        categoryAdminAdapter.setCategories(categories);

        // Xử lý sự kiện khi nút "Back" được click:
        // Gắn một OnClickListener cho nút "Back" (binding.btnBack).
        // Khi nút được click, một Intent được tạo để chuyển đến MenuAdminActivity, và sau đó kết thúc activity hiện tại.
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                startActivity(new Intent(getApplicationContext(), MenuAdminActivity.class));
                finish();
            }
        });

        // Xử lý sự kiện khi nút "Create Product" được click:
        // Gắn một OnClickListener cho nút "Create Product" (binding.btnCreateProduct).
        // Khi nút được click, một Intent được tạo để chuyển đến CategoryCreateActivity, và sau đó kết thúc activity hiện tại.
        binding.btnCreateProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                startActivity(new Intent(getApplicationContext(), CategoryCreateActivity.class));
                finish();
            }
        });

        // Xử lý sự kiện trong Adapter:
        // Sử dụng phương thức setListener() trên categoryAdminAdapter để thiết lập một đối tượng lắng nghe sự kiện (CategoryAdminAdapterListener).
        // Trong phương thức này, xử lý sự kiện khi nút "Delete" và nút "Edit" được click trong adapter.
        // Khi nút "Delete" được click, xóa danh mục tại vị trí tương ứng và cập nhật giao diện.
        // Khi nút "Edit" được click, hiển thị thông tin chi tiết của danh mục.
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
                showCategoryDetails(rootView, category);
            }
        });
    }

    // Phương thức showCategoryDetails():
    // Lưu thông tin của danh mục được click vào SharedPreferences
    // và sau đó chuyển đến CategoryEditActivity bằng cách tạo và khởi chạy một Intent.
    private void showCategoryDetails(View v, Category category) {
        // Lưu thông tin người dùng vào SharedPreferences
        SharedPreferences sharedPreferences = v.getContext().getSharedPreferences("CategoryPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("categoryId", category.getCategoryID());
        editor.putString("categoryName", category.getCategoryName());
        editor.apply();

        // Chuyển đến CategoryActivity
        Intent intent = new Intent(v.getContext(), CategoryEditActivity.class);
        v.getContext().startActivity(intent);
    }
}
