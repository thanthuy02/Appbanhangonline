package com.example.appbanhangonline.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.activities.login.LoginActivity;
import com.example.appbanhangonline.database.DBHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(i);
    }
}