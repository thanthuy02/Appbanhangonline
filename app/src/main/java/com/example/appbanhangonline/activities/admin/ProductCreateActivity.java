package com.example.appbanhangonline.activities.admin;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.dbhandler.ProductHandler;

import java.util.List;

public class ProductCreateActivity extends Activity {

    ProductHandler productHandler;
    Spinner spinnerCategory;
    ImageButton ibnUpload;
    ImageView imgProductUpload;
    int categoryId;
    Button btnSave;
    ImageButton btnBack;
    EditText txtQuantity;
    EditText txtPrice;
    EditText txtProductName;
    int PICK_IMAGE_REQUEST = 456;
    String selectedImagePath = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_product_create);
        imgProductUpload = (ImageView) findViewById(R.id.imgProductUpload);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnBack = (ImageButton) findViewById(R.id.btnBack);
        spinnerCategory = (Spinner) findViewById(R.id.spinnerCategory);
        ibnUpload = (ImageButton) findViewById(R.id.imgUpload);
        txtPrice = (EditText) findViewById(R.id.txtPrice);
        txtProductName = (EditText) findViewById(R.id.txtProductName);
        txtQuantity = (EditText) findViewById(R.id.txtQuantity);
        productHandler = new ProductHandler(this);
        loadSpinner();

        ibnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                categoryId = productHandler.getCategoryIdByName(spinnerCategory.getSelectedItem().toString().trim());
                productHandler.add(txtProductName.getText().toString(), categoryId, Integer.parseInt(txtQuantity.getText().toString()), Integer.parseInt(txtPrice.getText().toString()), selectedImagePath);
                Toast.makeText(ProductCreateActivity.this, "Thêm sản phẩm thành công!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), ProductActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(getApplicationContext(), ProductActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void loadSpinner() {
        List<String> category = productHandler.getAllNameCategory();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, category);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerCategory.setAdapter(dataAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK & data != null) {
            Uri selectedImageUri = data.getData();
            selectedImagePath = selectedImageUri.toString();
            imgProductUpload.setImageURI(selectedImageUri);
        }
    }

}
