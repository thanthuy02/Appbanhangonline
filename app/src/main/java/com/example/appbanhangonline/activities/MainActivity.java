package com.example.appbanhangonline.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.activities.login.LoginActivity;
import com.example.appbanhangonline.activities.user.HomeUserActivity;
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

        Thread thread = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                } catch (Exception e) {
                    // TODO: handle exception
                } finally {
//                    Intent i = new Intent(getApplicationContext(),
//                            LoginActivity.class);
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