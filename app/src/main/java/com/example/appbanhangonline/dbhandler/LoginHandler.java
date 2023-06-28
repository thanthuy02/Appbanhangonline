package com.example.appbanhangonline.dbhandler;

import static com.example.appbanhangonline.database.DBHelper.DATABASE_NAME;
import static com.example.appbanhangonline.database.DBHelper.DATABASE_VERSION;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.example.appbanhangonline.database.DBHelper;
import com.example.appbanhangonline.models.User;

public class LoginHandler extends SQLiteOpenHelper {
    SQLiteDatabase db;

    private Context context;

    DBHelper dbHelper;

    public LoginHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }


    public User checkLogin(String email, String password) {
        // Thực hiện truy vấn và kiểm tra email và mật khẩu
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE email = ? AND password = ?", new String[]{email, password});

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            @SuppressLint("Range")
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String phone = cursor.getString(2);
            String address = cursor.getString(3);
            String role = cursor.getString(6);

            cursor.close();
            return new User(id, name, phone, address, email, password, role);
        } else {
            cursor.close();
            return null;
        }
    }

    public boolean register(String name, String phone, String address, String email, String password) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "Insert into users (name, phone, address, email, password, role) values (?, ?, ?, ?, ?, 'customer')";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, name);
        statement.bindString(2, phone);
        statement.bindString(3, address);
        statement.bindString(4, email);
        statement.bindString(5, password);
        long rowId = statement.executeInsert();
        db.close();
        return (rowId != -1); // Trả về true nếu rowId khác -1 (insert thành công), ngược lại trả về false
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
