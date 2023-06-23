package com.example.appbanhangonline.activities;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.adapters.ProductUserAdapter;
import com.example.appbanhangonline.database.CategoryHelper;
import com.example.appbanhangonline.database.DBHelper;
import com.example.appbanhangonline.models.Category;
import com.example.appbanhangonline.models.Product;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private CategoryHelper categoryHelper;

    private DBHelper dbHelper;
    private GridView gvProduct;
    private List<Category> productList;

    private TextView txtCategory;

    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        txtCategory = findViewById(R.id.txtCategory);

        ShowProductList();
//        gvProduct = findViewById(R.id.gvProduct);
//        categoryHelper = new CategoryHelper();
//        productList = categoryHelper.getAll();
//
//        ProductUserAdapter adapter = new ProductUserAdapter(this, productList);
//        gvProduct.setAdapter(adapter);
    }

    // Hiển thị thông tin người dùng
    public void toggleUserInfo(View view) {
        LinearLayout userInfoLayout = findViewById(R.id.userInfoLayout);
        if (userInfoLayout.getVisibility() == View.VISIBLE) {
            //Nếu đang hiển thị thì ẩn đi
            userInfoLayout.setVisibility(View.GONE);
        } else {
            // đang ẩn thì hiện lên
            userInfoLayout.setVisibility(View.VISIBLE);
        }
    }
    //Đăng xuất
    public void logoutClicked(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn có muốn đăng xuất không?")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // Hiển thị tên các danh mục vào popup menu
    public void onShowCategoryMenu(View view){
//        categoryHelper = new CategoryHelper();
//
//        List<Category> categoryList = categoryHelper.getAll();

        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item1:

                    case R.id.item2:

                    case R.id.item3:

                    case R.id.item4:
                        txtCategory.setText(item.getTitle().toString());
                        return true;
                }
                return false;
            }
        });

        popupMenu.inflate(R.menu.category_menu);

//        Menu menu = popupMenu.getMenu();
//        for(Category category : categoryList){
//            String categoryName = category.getCategoryName();
//            menu.add(R.id.categories, Menu.NONE, Menu.NONE, categoryName);
//        }

        //popupMenu.inflate(R.menu.category_menu);

        popupMenu.show();
    }

    public void ShowProductList(){
        List<Product> productList = new ArrayList<>();

        // Thêm sản phẩm 1
        byte[] img1 = convertDrawableToByteArray(R.drawable.pen1);
        Product product1 = new Product(1, "Bút bi", 1, 30,  5000, img1);
        productList.add(product1);

        // Thêm sản phẩm 2
        byte[] img2 = convertDrawableToByteArray(R.drawable.denhoc1);
        Product product2 = new Product(2, "Đèn học",2, 57,97000, img2);
        productList.add(product2);

        // Thêm sản phẩm 3
        byte[] img3 = convertDrawableToByteArray(R.drawable.pen2);
        Product product3 = new Product(3, "Bút nước",1, 153,3500, img3);
        productList.add(product3);

        // Gán danh sách sản phẩm vào GridView
        GridView gvProduct = findViewById(R.id.gvProduct);
        ProductUserAdapter adapter = new ProductUserAdapter(this, productList);
        gvProduct.setAdapter(adapter);
    }



    private byte[] convertDrawableToByteArray(int drawableId) {
        try {
            // Tạo đối tượng BitmapFactory để đọc tài nguyên drawable
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;

            // Chuyển đổi drawable thành đối tượng Bitmap
            Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), drawableId, options);

            // Tạo đối tượng ByteArrayOutputStream để ghi dữ liệu hình ảnh
            ByteArrayOutputStream stream = new ByteArrayOutputStream();

            // Nén hình ảnh và ghi dữ liệu vào stream
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);

            // Lấy mảng byte từ stream
            byte[] byteArray = stream.toByteArray();

            // Đóng stream
            stream.close();

            return byteArray;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }




}
