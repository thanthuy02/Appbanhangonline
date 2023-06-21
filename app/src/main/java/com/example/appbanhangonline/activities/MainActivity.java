package com.example.appbanhangonline.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.database.CategoryHelper;
import com.example.appbanhangonline.database.DBHelper;
import com.example.appbanhangonline.models.Category;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(this);
        // read

        List<Category> categories = CategoryHelper.gI().getAll();
        for (Category category_ : categories) {
            Toast.makeText(this, String.valueOf(category_.getCategoryID()), Toast.LENGTH_SHORT).show();
        }
    }

    public static DBHelper getDB() {
        return dbHelper;
    }

}