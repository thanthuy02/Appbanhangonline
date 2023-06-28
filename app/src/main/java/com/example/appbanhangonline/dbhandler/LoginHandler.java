package com.example.appbanhangonline.dbhandler;

import static com.example.appbanhangonline.database.DBHelper.DATABASE_NAME;
import static com.example.appbanhangonline.database.DBHelper.DATABASE_VERSION;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.appbanhangonline.database.DBHelper;

public class LoginHandler extends SQLiteOpenHelper {
    SQLiteDatabase db;

    private Context context;

    DBHelper dbHelper;
    SharedPreferences sharedPreferences;

    public LoginHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
        sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
    }


    public String checkLogin(String email, String password) {
        // Thực hiện truy vấn và kiểm tra email và mật khẩu
        Cursor cursor = db.rawQuery("SELECT id, role FROM users WHERE email = ? AND password = ?", new String[]{email, password});

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            @SuppressLint("Range")
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String role = cursor.getString(cursor.getColumnIndex("role"));
            Log.d("TAG_id", "checkLogin: "+id);
            cursor.close();

            // Lưu thông tin khách hàng vào SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("email", email);
            editor.putString("role", role);
            editor.putInt("id", id);
            editor.apply();
            return role;
        } else {
            cursor.close();
            return null;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
