package com.example.appbanhangonline.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.database.DBHelper;

public class MainActivity extends AppCompatActivity {

    public static DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(this);
    }
}