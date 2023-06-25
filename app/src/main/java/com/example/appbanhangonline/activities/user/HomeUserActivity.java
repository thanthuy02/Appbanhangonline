package com.example.appbanhangonline.activities.user;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
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
import com.example.appbanhangonline.dbhandler.ProductHandler;
import com.example.appbanhangonline.models.Product;
import com.example.appbanhangonline.models.ProductRepository;
import java.util.ArrayList;
import java.util.Random;

public class HomeUserActivity extends AppCompatActivity {
    TextView txtCategory;
    RecyclerView rvProduct;

    ArrayList<Product> productList;

    ProductUserAdapter productUserAdapter;

    ProductRepository productRepository;

    ProductHandler productHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        productHandler  = new ProductHandler(this);
        Anhxa();
        productUserAdapter = new ProductUserAdapter(this, productRepository.getProductList());
        rvProduct.setAdapter(productUserAdapter);
    }

    // ánh xạ các đối tượng
    private void Anhxa() {
        txtCategory = findViewById(R.id.txtCategory);
        rvProduct = findViewById(R.id.rvProduct);
        productList = new ArrayList<>();
//        Các mảng chứa thông tin sản phẩm
        String[] productName = {"Bút bi","Bút 3 màu","Đèn học trắng","Vở bìa cứng","Vở 200 trang","Vở lò xo","Máy tính 570","Máy tính 580",
                "Đèn học chân cao","Đèn học cao cấp","Bút chì 2B"};

        int[] category_id = {1,1,4,2,2,2,3,3,4,4,1};

        int[] price = {6000, 15000, 85000, 28000, 20000, 56000, 427000, 510000, 90000, 150000, 4000};

        Random random = new Random();
        int minQuantity = 2;
        int maxQuantity = 30;


        for (int i = 1; i <= 11; i++) {
            String imagePath= "android.resource://" + getPackageName() + "/drawable/pd" + i;
            Uri imageUri = Uri.parse(imagePath);
            String image = imageUri.toString();
            Product p = new Product(i, productName[i-1], productHandler.getCategoryName(category_id[i-1]), random.nextInt(maxQuantity - minQuantity + 1) + minQuantity , price[i-1], image);
            productList.add(p);
        }

        productRepository = new ProductRepository(productList);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        rvProduct.setLayoutManager(layoutManager);
    }

    // Hiển thị thông tin người dùng , gọi trong onclick
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

    //Đăng xuất , gọi trong onclick
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

    // Hiển thị tên các danh mục vào popup menu, gọi trong onclick
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

    // lọc sản phẩm theo category_id
    public void filterProductByCategory(int categoryId){
        ArrayList<Product> filter = new ArrayList<>();
        for(Product p : productList){
            if(productHandler.getCategoryIdByName(p.getCategoryName()) == categoryId) {
                filter.add(p);
            }
        }
        productUserAdapter.setProductList(filter);
        productUserAdapter.notifyDataSetChanged();
    }

    // khi bấm vào button giỏ hàng để giảm sản phẩm trong đó, được gọi trong onclick
    public void onShowProductByOder(View v){
        Intent intent = new Intent(HomeUserActivity.this, CartActivity.class);
        startActivity(intent);
    }

}


