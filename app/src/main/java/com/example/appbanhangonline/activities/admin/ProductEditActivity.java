package com.example.appbanhangonline.activities.admin;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.appbanhangonline.R;
import com.example.appbanhangonline.adapters.ProductAdapter;
import com.example.appbanhangonline.dbhandler.ProductHandler;
import com.example.appbanhangonline.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductEditActivity extends Activity {
    Product product;
    ProductHandler productHandler;
    Spinner spinnerCategory;
    ImageButton ibnUpload;
    ImageView imgProductUpload;
    int categoryId;
    Button btnSave;
    EditText txtQuantity;
    EditText txtPrice;
    EditText txtProductName;
    int REQUEST_CODE_FOLDER = 456;
    String selectedImagePath = "";
    int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_product_edit);
        imgProductUpload = (ImageView) findViewById(R.id.imgProductUpload);
        btnSave = (Button) findViewById(R.id.btnSave);
        spinnerCategory = (Spinner) findViewById(R.id.spinnerCategory);
        ibnUpload = (ImageButton) findViewById(R.id.imgUpload);
        txtPrice = (EditText) findViewById(R.id.txtPrice);
        txtProductName = (EditText) findViewById(R.id.txtProductName);
        txtQuantity = (EditText) findViewById(R.id.txtQuantity);
        productHandler = new ProductHandler(this);
        loadSpinner();
        getData();

        ibnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent in = new Intent(Intent.ACTION_PICK);
                in.setType("image/*");
                startActivityForResult(in, REQUEST_CODE_FOLDER);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                categoryId = productHandler.getCategoryIdByName(spinnerCategory.getSelectedItem().toString().trim());
                productHandler.edit(id, txtProductName.getText().toString(), categoryId, Integer.parseInt(txtQuantity.getText().toString()), Integer.parseInt(txtPrice.getText().toString()), selectedImagePath);
                Toast.makeText(ProductEditActivity.this, "Sửa sản phẩm thành công!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), ProductActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    public void loadSpinner() {
        List<String> category = productHandler.getAllNameCategory();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, category);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerCategory.setAdapter(dataAdapter);

        // hiển thị category của product
        product = (Product) getIntent().getSerializableExtra("ProductEdit");
        int position = dataAdapter.getPosition(String.valueOf(product.getCategoryName()));
        spinnerCategory.setSelection(position);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK & data != null) {
            Uri selectedImageUri = data.getData();
            selectedImagePath = selectedImageUri.toString();
            imgProductUpload.setImageURI(selectedImageUri);
        }
    }

    public void getData() {
        if (getIntent().getExtras() != null) {
            product = (Product) getIntent().getSerializableExtra("ProductEdit");
            id = product.getProductID();
            txtProductName.setText(product.getProductName());
            txtQuantity.setText(String.valueOf(product.getQuantity()));
            txtPrice.setText(String.valueOf(product.getPrice()));
            selectedImagePath = product.getImage();
            Glide.with(ProductEditActivity.this)
                    .load(Uri.parse(selectedImagePath))
                    .into(imgProductUpload);
        }
    }


}
