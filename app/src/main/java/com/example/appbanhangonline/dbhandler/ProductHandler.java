package com.example.appbanhangonline.dbhandler;

import static com.example.appbanhangonline.database.DBHelper.DATABASE_NAME;
import static com.example.appbanhangonline.database.DBHelper.DATABASE_VERSION;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.example.appbanhangonline.database.DBHelper;
import com.example.appbanhangonline.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductHandler extends SQLiteOpenHelper {
    SQLiteDatabase db;

    DBHelper dbHelper;
    private Context context;

    //    ham tao
    public ProductHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }


    public Cursor getAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        // Thực hiện truy vấn SQL và nhận một Cursor
        Cursor cursor = db.rawQuery("SELECT * FROM products;", null);
        return cursor;
    }

    public void add(String name, int category_id, int quantity, int price, byte[] image) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "Insert into products (name, category_id, quantity, price, image) values (?, ?, ?, ?, ?)";
//        bien dich cau lenh sql thanh mot SQLiteStatement
        SQLiteStatement statement = db.compileStatement(sql);
//        xoa rang buoc cu de gan gia tri moi
        statement.clearBindings();
        statement.bindString(1, name);
        statement.bindLong(2, category_id);
        statement.bindLong(3, quantity);
        statement.bindLong(4, price);
        statement.bindBlob(5, image);
        statement.executeInsert();
        db.close();
    }

    public void edit(int id, String name, int category_id, int quantity, int price, byte[] image) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "UPDATE products SET name = ? , category_id = ? , quantity = ?, price = ?, image = ? Where id = ? ";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, name);
        statement.bindLong(2, category_id);
        statement.bindLong(3, quantity);
        statement.bindLong(4, price);
        statement.bindBlob(5, image);
        statement.bindLong(6, id);
        statement.execute();
        db.close();
    }

    public void delete(int id) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete("products", "id" + " = " + id, null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
