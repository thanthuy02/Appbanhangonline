package com.example.appbanhangonline.dbhandler;

import static com.example.appbanhangonline.database.DBHelper.DATABASE_NAME;
import static com.example.appbanhangonline.database.DBHelper.DATABASE_VERSION;

import android.content.Context;
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

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
