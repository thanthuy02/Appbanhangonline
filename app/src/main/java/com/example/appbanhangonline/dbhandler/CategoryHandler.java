package com.example.appbanhangonline.dbhandler;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.LinearGradient;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.appbanhangonline.activities.MainActivity;
import com.example.appbanhangonline.database.DBHelper;
import com.example.appbanhangonline.dbhandler.interfaces.IManager;
import com.example.appbanhangonline.models.Category;
import com.example.appbanhangonline.utils.Logger;

import java.util.ArrayList;
import java.util.List;

public class CategoryHandler extends SQLiteOpenHelper implements IManager<Category, Integer> {

    public CategoryHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static CategoryHandler gI(Context context) {
        return new CategoryHandler(context, DBHelper.DATABASE_NAME, null, DBHelper.DATABASE_VERSION);
    }

    @Override
    public void add(Category category) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            String sql = String.format("INSERT INTO %s(%s) VALUES('%s')", DBHelper.CATEGORIES, DBHelper.CATEGORY_NAME, category.getCategoryName());
            db.execSQL(sql);
            db.close();
        } catch (Exception e) {
            Logger.error(e.getMessage());
        }
    }

    @Override
    public void update(Category category) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            String sql = String.format("UPDATE %s SET %s='%s' WHERE %s=%d", DBHelper.CATEGORIES, DBHelper.CATEGORY_NAME, category.getCategoryName(), DBHelper.CATEGORY_ID, category.getCategoryID());
            db.execSQL(sql);
            db.close();
        } catch (Exception e) {
            Logger.error(e.getMessage());
        }
    }

    @Override
    public boolean delete(Integer integer) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            String remove_product_sql = String.format("DELETE FROM %s where %s=%d", DBHelper.PRODUCTS, DBHelper.PRODUCT_CATEGORY_ID, integer);
            db.execSQL(remove_product_sql);
            String sql = String.format("DELETE FROM %s WHERE %s = %d", DBHelper.CATEGORIES, DBHelper.CATEGORY_ID, integer);
            db.execSQL(sql);
            // query to remove product by category
            db.close();
            return true;
        } catch (Exception e) {
            Logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public Category getById(Integer integer) {
        Category category = null;
        try {
            DBHelper dbHelper = MainActivity.getDB();
            String sql = String.format("SELECT * from %s where %s=%d ", DBHelper.CATEGORIES, DBHelper.CATEGORY_ID, integer);
            Cursor cursor = dbHelper.getData(sql);
            while (cursor.moveToNext()) {
                category = new Category();
                category.setCategoryID(cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.CATEGORY_ID)));
                category.setCategoryName(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.CATEGORY_NAME)));
            }
        } catch (Exception e) {
            Log.d("error : ", e.getMessage());
        }
        return category;
    }

    public int getCategoryIdByName(String category) {
        int categoryId = 0;
        DBHelper dbHelper = MainActivity.getDB();
        String query = "SELECT id FROM categories WHERE categories.category = ?";
        Cursor cursor = dbHelper.getReadableDatabase().rawQuery(query, new String[] {"" + category});
        if (cursor.moveToFirst()) {
            categoryId = cursor.getInt(0);
        }
        cursor.close();
        return categoryId;
    }

    @Override
    public List<Category> getListByPage(int page, int limit) {
        return null;
    }

    @Override
    public List<Category> getAll() {
        List<Category> categories = new ArrayList<>();
        try {
            SQLiteDatabase database = getReadableDatabase();
            //assert dbHelper != null;
            String sql = String.format("SELECT * from %s", DBHelper.CATEGORIES);
            Log.d("sql :: ", sql);
            Cursor cursor = database.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                Category category = new Category();
                category.setCategoryID(cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.CATEGORY_ID)));
                category.setCategoryName(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.CATEGORY_NAME)));
                categories.add(category);
            }
            database.close();
        } catch (Exception e) {
            Log.d("error : ", e.getMessage());
        }
        return categories;
    }

    @Override
    public List<Category> searchByKeyword(String query) {
        return null;
    }

    @Override
    public List<Category> searchByID(Integer integer) {
        return null;
    }

    @Override
    public List<Category> sortAsc() {
        return null;
    }

    @Override
    public List<Category> sortDesc() {
        return null;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
