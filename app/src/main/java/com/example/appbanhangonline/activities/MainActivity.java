package com.example.appbanhangonline.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.activities.login.LoginActivity;
import com.example.appbanhangonline.database.DBHelper;
import com.example.appbanhangonline.dbhandler.CategoryHandle;
import com.example.appbanhangonline.models.Category;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(this);
        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(i);
//        test();
    }

    public static DBHelper getDB() {
        return dbHelper;
    }

    public void test() {
        Category category = new Category();
        category.setCategoryName("máy tính bảng");
        CategoryHandle.gI().add(category);

        Category category_ = new Category();
        category_.setCategoryName("máy tính bảng 1");
        CategoryHandle.gI().add(category_);
        Category category__ = new Category();
        category__.setCategoryName("máy tính bảng 2");
        CategoryHandle.gI().add(category__);


        List<Category> categories = CategoryHandle.gI().getAll();
        for (Category category1 : categories) {
            Toast.makeText(this, category1.getCategoryName(), Toast.LENGTH_SHORT).show();
        }

    }
}