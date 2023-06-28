package com.example.appbanhangonline.activities.user;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
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
import com.example.appbanhangonline.adapters.CartAdapter;
import com.example.appbanhangonline.adapters.ProductUserAdapter;
import com.example.appbanhangonline.dbhandler.CategoryHandler;
import com.example.appbanhangonline.dbhandler.ProductHandler;
import com.example.appbanhangonline.models.Category;
import com.example.appbanhangonline.models.Product;
import com.example.appbanhangonline.models.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class HomeUserActivity extends AppCompatActivity {
    TextView txtCategory, txtUsername, txtEmail;

    EditText editTextSearch;

    ImageButton btnSearch;

    RecyclerView rvProduct;

    ArrayList<Product> productList;

    ProductUserAdapter productUserAdapter;

    ProductRepository productRepository;

    ProductHandler productHandler;

    public static int user_id;

    public String user_email, user_name;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = editTextSearch.getText().toString().trim();
                filterProductByName(search);
            }
        });

        productUserAdapter = new ProductUserAdapter(this, productList);
        rvProduct.setAdapter(productUserAdapter);
    }

    // khởi tạo các đối tượng
    private void init() {
        editTextSearch = findViewById(R.id.editTextSearch);
        btnSearch = findViewById(R.id.btnSearch);

        txtCategory = findViewById(R.id.txtCategory);
        txtUsername = findViewById(R.id.txtUsername);
        txtEmail = findViewById(R.id.txtEmail);

        Intent intent = getIntent();
        user_email = intent.getStringExtra("user_email");
        user_name = intent.getStringExtra("user_name");
        user_id = intent.getIntExtra("user_id", -1);

        rvProduct = findViewById(R.id.rvProduct);

        productHandler  = new ProductHandler(this);

        productList = new ArrayList<>();
        productList = productHandler.getAllProducts();
        productRepository = new ProductRepository(productList);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        rvProduct.setLayoutManager(layoutManager);

    }

    // Hiển thị thông tin người dùng , gọi trong onclick
    public void toggleUserInfo(View view) {
        LinearLayout userInfoLayout = findViewById(R.id.userInfoLayout);

        txtUsername.setText(user_name);
        txtEmail.setText(user_email);

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

        // lấy all tên category từ DB
        CategoryHandler categoryHandler = CategoryHandler.gI(this);
        List<Category> categories = categoryHandler.getAll();

        // thêm item "Tất cả" để hiển thị tất cả sản phẩm
        popupMenu.getMenu().add("Tất cả");

        // thêm các danh mục vào menu
        for(Category category : categories){
            popupMenu.getMenu().add(category.getCategoryName());
        }

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String selectedCategoryName = item.getTitle().toString();
                if(selectedCategoryName.equals("Tất cả")) {
                    // hiển thị all sản phẩm
                    txtCategory.setText("Tất cả");
                    showAllProducts();
                } else {
                    // hiển thị sp theo từng danh mục
                    int selectedCategoryId = categoryHandler.getCategoryIdByName(selectedCategoryName);
                    txtCategory.setText(selectedCategoryName);
                    filterProductByCategory(selectedCategoryId);
                }
                return true;
            }
        });

        popupMenu.show();
    }

    // lọc sản phẩm theo category_id
    public void filterProductByCategory(int categoryId){
        ArrayList<Product> filter_id = new ArrayList<>();
        CategoryHandler categoryHandler = CategoryHandler.gI(this);
        for(Product p : productList){
            String categoryName = p.getCategoryName();
            int productCategoryId = categoryHandler.getCategoryIdByName(categoryName);
            if(productCategoryId == categoryId) {
                filter_id.add(p);
            }
        }
        productUserAdapter.setProductList(filter_id);
        productUserAdapter.notifyDataSetChanged();
    }

    public void filterProductByName(String productName){
        ArrayList<Product> filter_name = new ArrayList<>();
        for(Product p : productList){
            if(p.getProductName().toLowerCase().contains(productName.toLowerCase())) {
                filter_name.add(p);
            }
        }
        productUserAdapter.setProductList(filter_name);
        productUserAdapter.notifyDataSetChanged();
    }

    // hiển thị all sản phẩm
    public void showAllProducts(){
        productUserAdapter.setProductList(productList);
        productUserAdapter.notifyDataSetChanged();
    }

    // khi bấm vào button giỏ hàng để giảm sản phẩm trong đó, được gọi trong onclick
    public void onShowProductByOder(View v){
        Intent intent = new Intent(HomeUserActivity.this, CartActivity.class);
        startActivity(intent);
    }

}

