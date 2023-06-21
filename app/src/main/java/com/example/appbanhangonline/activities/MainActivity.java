package com.example.appbanhangonline.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(i);
    }

    public static DBHelper getDB() {
        return dbHelper;
    }
}