package com.example.appbanhangonline.activities.user;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.activities.login.LoginActivity;
import com.example.appbanhangonline.adapters.ProductUserAdapter;
import com.example.appbanhangonline.dbhandler.CategoryHandle;
import com.example.appbanhangonline.dbhandler.ProductHandler;
import com.example.appbanhangonline.models.Category;
import com.example.appbanhangonline.models.Product;
import com.example.appbanhangonline.models.ProductRepository;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;

public class HomeUserActivity extends AppCompatActivity {
    TextView txtCategory;
    RecyclerView rvProduct;
    ProductRepository productRepository;

    ProductUserAdapter productUserAdapter;

    ProductHandler productHandler;

    CategoryHandle categoryHandle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        txtCategory = findViewById(R.id.txtCategory);

//        productList();

        rvProduct = findViewById(R.id.rvProduct);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        rvProduct.setLayoutManager(layoutManager);

        productUserAdapter = new ProductUserAdapter(this, productRepository.getProductList());
        rvProduct.setAdapter(productUserAdapter);
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
        builder.setTitle("Đăng xuất");
        builder.setMessage("Bạn có muốn đăng xuất không?")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(HomeUserActivity.this, LoginActivity.class);
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

//    public void productList(){
//        ArrayList<Product> products = new ArrayList<>();
//        for (int i = 0; i < 50; i++) {
//
//            int minPrice = 3000;
//            int maxPrice = 10000;
//            int randomPrice = new Random().nextInt(maxPrice - minPrice + 1) + minPrice;
//            String price = String.valueOf(randomPrice);
//            price = price.replace(",", ".");
//            float productPrice = Float.parseFloat(price);
//            Product p = new Product(i, "Product " + i, 2,20, productPrice);
//
//            int resID = getResId("pd" + (i%11 + 1), R.drawable.class);
//            Uri imgUri = getUri(resID);
//            p.setImage(imgUri);
//            p.setPrice(productPrice);
//            products.add(p);
//        }
//        productRepository = new ProductRepository(products);
//    }
    public Uri getUri (int resId){
        return Uri.parse("android.resource://"  + this.getPackageName().toString() + "/" + resId);
    }

    public static int getResId(String resName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void onShowProductByOder(View v){
        Intent intent = new Intent(HomeUserActivity.this, CartActivity.class);
        startActivity(intent);
    }
}
