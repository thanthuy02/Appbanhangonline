package com.example.appbanhangonline.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.example.appbanhangonline.activities.MainActivity;
import com.example.appbanhangonline.database.interfaces.IManager;
import com.example.appbanhangonline.models.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryHelper implements IManager<Category, Integer> {
    private static CategoryHelper I;

    public static CategoryHelper gI() {
        if (I == null) {
            I = new CategoryHelper();
        }
        return I;
    }

    @Override
    public void add(Category category) {
        DBHelper dbHelper = MainActivity.getDB();
        String sql = String.format("INSERT INTO %s(%s) VALUES('%s')", DBHelper.CATEGORIES, DBHelper.CATEGORY_NAME, category.getCategoryName());
        dbHelper.queryData(sql);
    }

    @Override
    public void update(Category category) {

    }

    @Override
    public void transactionWithCallBack(DBHelper.TransactionCallBack callBack) {
        try {
            MainActivity.getDB().getWritableDatabase().beginTransaction();
            callBack.onCallBack();
        } catch (Exception e) {
            Log.d("error : ", e.getMessage());
        } finally {
            MainActivity.getDB().getWritableDatabase().endTransaction();
        }
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }

    @Override
    public Category getById(Integer integer) {
        Category category = null;
        try {
            DBHelper dbHelper = MainActivity.getDB();
            assert dbHelper != null;
            dbHelper.getReadableDatabase().beginTransaction();
            String sql = String.format("SELECT * from %s where %s=%d ", DBHelper.CATEGORIES, DBHelper.CATEGORY_ID, integer);
            Cursor cursor = dbHelper.getData(sql);
            while (cursor.moveToNext()) {
                category = new Category();
                category.setCategoryID(cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.CATEGORY_ID)));
                category.setCategoryName(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.CATEGORY_NAME)));
            }
        } catch (Exception e) {
            MainActivity.getDB().getReadableDatabase().endTransaction();
            Log.d("error : ", e.getMessage());
        }
        return category;
    }

    @Override
    public List<Category> getListByPage(int page, int limit) {
        return null;
    }

    @Override
    public List<Category> getAll() {
        List<Category> categories = new ArrayList<>();
        try {
            DBHelper dbHelper = MainActivity.getDB();
            assert dbHelper != null;
            dbHelper.getReadableDatabase().beginTransaction();
            String sql = String.format("SELECT * from %s", DBHelper.CATEGORIES);
            Log.d("sql :: ", sql);
            Cursor cursor = dbHelper.getData(sql);
            while (cursor.moveToNext()) {
                Category category = new Category();
                category.setCategoryID(cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.CATEGORY_ID)));
                category.setCategoryName(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.CATEGORY_NAME)));
                categories.add(category);
            }
        } catch (Exception e) {
            MainActivity.getDB().getReadableDatabase().endTransaction();
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
}
