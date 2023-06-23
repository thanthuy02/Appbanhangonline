package com.example.appbanhangonline.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import android.widget.Toast;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.database.CategoryHelper;
import com.example.appbanhangonline.activities.login.LoginActivity;
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
        dbHelper = new DBHelper(MainActivity.this);

//        trang login, đang ví dụ minh hoạ để làm logout
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        // read

//        List<Category> categories = CategoryHelper.gI().getAll();
//        for (Category category_ : categories) {
//            Toast.makeText(this, String.valueOf(category_.getCategoryID()), Toast.LENGTH_SHORT).show();
//        }
//        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
//        startActivity(i);
    }

    public static DBHelper getDB() {
        return dbHelper;
    }
}