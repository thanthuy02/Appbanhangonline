package com.example.appbanhangonline.activities.admin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.adapters.ProductAdapter;
import com.example.appbanhangonline.dbhandler.ProductHandler;
import com.example.appbanhangonline.models.Product;

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
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),
                        ProductCreateActivity.class));
            }
        });
    }

    public void showItem() {
       productHandler = new ProductHandler(this);
       ArrayList<Product> products = productHandler.getAllProduct();
       productAdapter = new ProductAdapter(this, R.layout.listview_product_item, products);
       listViewProduct.setAdapter(productAdapter);
    }
}
