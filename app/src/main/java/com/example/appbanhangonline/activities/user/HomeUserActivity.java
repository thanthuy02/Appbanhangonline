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
import android.widget.Toast;

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
import java.util.List;
import java.util.Random;

public class HomeUserActivity extends AppCompatActivity {
    TextView txtCategory;
    RecyclerView rvProduct;

    ArrayList<Product> productList;

    ProductUserAdapter productUserAdapter;

    ProductRepository productRepository;

    ProductHandler productHandler;

    CategoryHandle categoryHandle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Anhxa();
        productUserAdapter = new ProductUserAdapter(this, productRepository.getProductList());
        rvProduct.setAdapter(productUserAdapter);
    }

    private void Anhxa() {
        txtCategory = findViewById(R.id.txtCategory);
        rvProduct = findViewById(R.id.rvProduct);

        productList = new ArrayList<>();

        //productHandler = new ProductHandler(this);

        String imagePath1 = "android.resource://" + getPackageName() + "/drawable/pd1";
        String imagePath2 = "android.resource://" + getPackageName() + "/drawable/pd2";
        String imagePath3 = "android.resource://" + getPackageName() + "/drawable/pd3";
        String imagePath4 = "android.resource://" + getPackageName() + "/drawable/pd4";
        String imagePath5 = "android.resource://" + getPackageName() + "/drawable/pd5";
        String imagePath6 = "android.resource://" + getPackageName() + "/drawable/pd6";
        String imagePath7 = "android.resource://" + getPackageName() + "/drawable/pd7";
        String imagePath8 = "android.resource://" + getPackageName() + "/drawable/pd8";
        String imagePath9 = "android.resource://" + getPackageName() + "/drawable/pd9";
        String imagePath10 = "android.resource://" + getPackageName() + "/drawable/pd10";
        String imagePath11 = "android.resource://" + getPackageName() + "/drawable/pd11";

        Uri imageUri1 = Uri.parse(imagePath1);
        Uri imageUri2 = Uri.parse(imagePath2);
        Uri imageUri3 = Uri.parse(imagePath3);
        Uri imageUri4 = Uri.parse(imagePath4);
        Uri imageUri5 = Uri.parse(imagePath5);
        Uri imageUri6 = Uri.parse(imagePath6);
        Uri imageUri7 = Uri.parse(imagePath7);
        Uri imageUri8 = Uri.parse(imagePath8);
        Uri imageUri9 = Uri.parse(imagePath9);
        Uri imageUri10 = Uri.parse(imagePath10);
        Uri imageUri11 = Uri.parse(imagePath11);

        String image1 = imageUri1.toString();
        String image2 = imageUri2.toString();
        String image3 = imageUri3.toString();
        String image4 = imageUri4.toString();
        String image5 = imageUri5.toString();
        String image6 = imageUri6.toString();
        String image7 = imageUri7.toString();
        String image8 = imageUri8.toString();
        String image9 = imageUri9.toString();
        String image10 = imageUri10.toString();
        String image11 = imageUri11.toString();
//
//        productHandler.add("Bút bi", 1, 35, 6000, image1);
//        productHandler.add("Bút 3 màu", 1, 15, 15000, image2);
//        productHandler.add("Đèn học trắng", 4, 21, 85000, image3);
//        productHandler.add("Vở bìa cứng", 2, 10, 28000, image4);
//        productHandler.add("Vở 200 trang", 2, 4, 20000, image5);
//        productHandler.add("Vở lò xo", 2, 12, 56000, image6);
//        productHandler.add("Máy tính 570", 3, 15, 427000, image7);
//        productHandler.add("Máy tính 580", 3, 6, 510000, image8);
//        productHandler.add("Đèn học chân cao", 4, 5, 90000, image9);
//        productHandler.add("Đèn học cao cấp", 4, 14, 150000, image10);
//        productHandler.add("Bút chì 2B", 1, 56, 4000, image11);
//
//        productList = productHandler.getAll();

        //System.out.println(productList);

//        for (int i = 0; i < 50; i++) {
//            int productID = i;
//            String productName = "Product " + i;
//            int categoryID = 2;
//            int quantity = 15;
//            int minPrice = 3000;
//            int maxPrice = 10000;
//            int price = new Random().nextInt(maxPrice - minPrice + 1) + minPrice;
//
//            String imagePath = "android.resource://" + getPackageName() + "/drawable/pd" + (i % 11 + 1);
//            Uri imageUri = Uri.parse(imagePath);
//            String image = imageUri.toString();
//
//            Product p = new Product(productID, productName, categoryID, quantity, price, image);
//
//            productList.add(p);
//        }

        Product p1 = new Product(1, "Bút bi", 1, 35, 6000, image1);
        productList.add(p1);

        Product p2 = new Product(2, "Bút 3 màu", 1, 15, 15000, image2);
        productList.add(p2);

        Product p3 = new Product(3, "Đèn học trắng", 4, 21, 85000, image3);
        productList.add(p3);

        Product p4 = new Product(4, "Vở bìa cứng", 2, 10, 28000, image4);
        productList.add(p4);

        Product p5 = new Product(5, "Vở 200 trang", 2, 4, 20000, image5);
        productList.add(p5);

        Product p6 = new Product(6, "Vở lò xo", 2, 12, 56000, image6);
        productList.add(p6);

        Product p7 = new Product(7, "Máy tính 570", 3, 15, 427000, image7);
        productList.add(p7);

        Product p8 = new Product(8, "Máy tính 580", 3, 6, 510000, image8);
        productList.add(p8);

        Product p9 = new Product(9, "Đèn học chân cao", 4, 5, 90000, image9);
        productList.add(p9);

        Product p10 = new Product(10, "Đèn học cao cấp", 4, 14, 150000, image10);
        productList.add(p10);

        Product p11 = new Product(11, "Bút chì 2B", 1, 56, 4000, image11);
        productList.add(p11);

        productRepository = new ProductRepository(productList);

            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
            rvProduct.setLayoutManager(layoutManager);
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
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item1:
                        txtCategory.setText(item.getTitle().toString());
                        filterProductByCategory(1);
                        return true;

                    case R.id.item2:
                        txtCategory.setText(item.getTitle().toString());
                        filterProductByCategory(2);
                        return true;

                    case R.id.item3:
                        txtCategory.setText(item.getTitle().toString());
                        filterProductByCategory(3);
                        return true;

                    case R.id.item4:
                        txtCategory.setText(item.getTitle().toString());
                        filterProductByCategory(4);
                        return true;

                    case R.id.item5:
                        txtCategory.setText(item.getTitle().toString());
                        productUserAdapter.setProductList(productList);
                        productUserAdapter.notifyDataSetChanged();
                        return true;
                }
                return false;
            }
        });

        popupMenu.inflate(R.menu.category_menu);
        popupMenu.show();
    }

    public void filterProductByCategory(int categoryId){
        ArrayList<Product> filter = new ArrayList<>();
        for(Product p : productList){
            if(p.getCategoryID() == categoryId) {
                filter.add(p);
            }
        }
        productUserAdapter.setProductList(filter);
        productUserAdapter.notifyDataSetChanged();
    }


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


