package com.example.appbanhangonline.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.activities.login.LoginActivity;
import com.example.appbanhangonline.database.DBHelper;
import com.example.appbanhangonline.dbhandler.ProductHandler;
import com.example.appbanhangonline.models.Product;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static DBHelper dbHelper;

    public static int user_id = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);

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