package com.example.appbanhangonline.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.activities.login.LoginActivity;
import com.example.appbanhangonline.database.DBHelper;

public class MainActivity extends AppCompatActivity {

    private static DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);

//        ProductHandler productHandler = new ProductHandler(this);
//        String imagePath1 = "android.resource://" + getPackageName() + "/drawable/pd1";
//        String imagePath2 = "android.resource://" + getPackageName() + "/drawable/pd2";
//        Uri imageUri1 = Uri.parse(imagePath1);
//        Uri imageUri2 = Uri.parse(imagePath2);
//        String image1 = imageUri1.toString();
//        String image2 = imageUri2.toString();
//        ArrayList<Product> productList = new ArrayList<>();
//        productHandler.add("Bút bi", 1, 35, 6000, image1);
//        productHandler.add("Bút 3 màu", 1, 15, 15000, image2);
//
//        productList = productHandler.getAllProducts();
//        System.out.println(productList);

        Thread thread = new Thread() {
            public void run() {
                try {
                    sleep(1500);
                } catch (Exception e) {
                    // TODO: handle exception
                } finally {
                    Intent i = new Intent(getApplicationContext(),
                            LoginActivity.class);
                    startActivity(i);
                }
            }
        };
        thread.start();
    }

    public static DBHelper getDB() {
        return dbHelper;
    }


}