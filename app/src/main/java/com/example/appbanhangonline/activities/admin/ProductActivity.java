package com.example.appbanhangonline.activities.admin;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.adapters.ProductAdapter;
import com.example.appbanhangonline.dbhandler.ProductHandler;
import com.example.appbanhangonline.models.Product;
import com.example.appbanhangonline.utils.Logger;

import java.util.ArrayList;

public class ProductActivity extends Activity {
    ImageButton btnBack;
    Button btnCreate;
    ListView listViewProduct;
    ProductHandler productHandler;
    ProductAdapter productAdapter;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_product);
        btnBack = (ImageButton) findViewById(R.id.btnBack);
        btnCreate = (Button) findViewById(R.id.btnCreate);
        listViewProduct = (ListView) findViewById(R.id.listViewProduct);

        showItem();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),
                        MenuAdminActivity.class));
                finish();
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),
                        ProductCreateActivity.class));
            }
        });

        listViewProduct.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Product product = productAdapter.getItem(position);
                AlertDialog.Builder dialog = new AlertDialog.Builder(ProductActivity.this);
                dialog.setTitle("XÓA SẢN PHẨM");
                dialog.setMessage("Bạn muốn xóa sản phẩm này?");
                dialog.setPositiveButton("XÓA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        productHandler.delete(product.getProductID());
                        Toast.makeText(ProductActivity.this, "Đã xóa!", Toast.LENGTH_SHORT).show();
                        showItem();
                    }
                }).setNegativeButton("Hủy", null).show();
                return true;
            }
        });

        listViewProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ProductActivity.this, ProductEditActivity.class);
                intent.putExtra("ProductEdit", productAdapter.getItem(position));
                startActivity(intent);
            }
        });

    }


    public void showItem() {
        productHandler = new ProductHandler(this);
        ArrayList<Product> products = productHandler.getAllProducts();
        productAdapter = new ProductAdapter(this, R.layout.listview_product_item, products);
        listViewProduct.setAdapter(productAdapter);
    }
}
