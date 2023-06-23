package com.example.appbanhangonline.dbhandler;

import static com.example.appbanhangonline.database.DBHelper.DATABASE_NAME;
import static com.example.appbanhangonline.database.DBHelper.DATABASE_VERSION;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.appbanhangonline.database.DBHelper;

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

    public boolean login(String email, String password) {
        String sql = String.format("SELECT * FROM %s where %s='%s' AND %s ='%s'", DBHelper.USERS, DBHelper.USER_EMAIL, DBHelper.USER_PASSWORD, email, password);
        Cursor rs = dbHelper.getData(sql);
        byte count = 0;
        while (rs.moveToNext()) {
            if (rs.getString(rs.getColumnIndexOrThrow(DBHelper.USER_EMAIL)) != null && rs.getString(rs.getColumnIndexOrThrow(DBHelper.USER_PASSWORD)) != null) {
                count++;
            }
        }
        return count == 1;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
