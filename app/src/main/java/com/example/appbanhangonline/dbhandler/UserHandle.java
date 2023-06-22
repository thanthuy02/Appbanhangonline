package com.example.appbanhangonline.dbhandler;

import android.database.Cursor;
import android.util.Log;

import com.example.appbanhangonline.activities.MainActivity;
import com.example.appbanhangonline.database.DBHelper;
import com.example.appbanhangonline.dbhandler.interfaces.IManager;
import com.example.appbanhangonline.models.User;
import com.example.appbanhangonline.utils.Logger;

import java.util.ArrayList;
import java.util.List;

public class UserHandle implements IManager<User, Integer> {

    private static UserHandle I;
    private final DBHelper mDbHelper;

    public UserHandle() {
        this.mDbHelper = MainActivity.getDB();
    }

    public static UserHandle gI() {
        if (I == null) {
            I = new UserHandle();
        }
        return I;
    }

    @Override
    public void add(User user) {
        try {
            String sql = String.format("INSERT INTO %s(%s,%s,%s,%s,%s,%s) VALUES('%s','%s','%s','%s','%s','%s')", DBHelper.USERS, DBHelper.USER_NAME, DBHelper.USER_PHONE, DBHelper.USER_ADDRESS, DBHelper.USER_EMAIL, DBHelper.USER_PASSWORD, DBHelper.USER_ROLE, user.getUserID(), user.getUsername(), user.getNumberPhone(), user.getAddress(), user.getEmail(), user.getPassword(), user.getRole());
            mDbHelper.queryData(sql);
        } catch (Exception e) {
            Logger.error(e.getMessage());
        }
    }

    @Override
    public void update(User user) {
        try {
            String sql = String.format("UPDATE %s SET %s='%s',%s='%s',%s='%s',%s='%s',%s='%s',%s='%s'", DBHelper.USERS, DBHelper.USER_NAME, DBHelper.USER_PHONE, DBHelper.USER_ADDRESS, DBHelper.USER_EMAIL, DBHelper.USER_PASSWORD, DBHelper.USER_ROLE, user.getUsername(), user.getNumberPhone(), user.getAddress(), user.getEmail(), user.getPassword(), user.getRole());
            mDbHelper.queryData(sql);
        } catch (Exception e) {
            Logger.error(e.getMessage());
        }
    }

    @Override
    public boolean delete(Integer integer) {
        try {
            String sql = String.format("DELETE * FROM %s where %s=%d", DBHelper.USERS, DBHelper.USER_ID, integer);
            mDbHelper.queryData(sql);
            // query to remove customer by user
            return true;
        } catch (Exception e) {
            Logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public User getById(Integer integer) {
        User user = null;
        try {
            mDbHelper.getReadableDatabase().beginTransaction();
            String sql = String.format("SELECT * from %s where %s=%d ", DBHelper.USERS, DBHelper.USER_ID, integer);
            Cursor cursor = mDbHelper.getData(sql);
            while (cursor.moveToNext()) {
                user = new User();
                user.setUserID(integer);
                user.setUsername(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.USER_NAME)));
                user.setNumberPhone(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.USER_PHONE)));
                user.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.USER_EMAIL)));
                user.setAddress(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.USER_ADDRESS)));
                user.setRole(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.USER_ROLE)));
                user.setPassword(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.USER_PASSWORD)));
            }
        } catch (Exception e) {
            MainActivity.getDB().getReadableDatabase().endTransaction();
            Log.d("error : ", e.getMessage());
        }
        return user;
    }

    @Override
    public List<User> getListByPage(int page, int limit) {
        return null;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try {
            DBHelper dbHelper = MainActivity.getDB();
            assert dbHelper != null;
            dbHelper.getReadableDatabase().beginTransaction();
            String sql = String.format("SELECT * from %s", DBHelper.CATEGORIES);
            Log.d("sql :: ", sql);
            Cursor cursor = dbHelper.getData(sql);
            while (cursor.moveToNext()) {
                User user = new User();
                user.setUserID(cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.USER_ID)));
                user.setUsername(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.USER_NAME)));
                user.setNumberPhone(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.USER_PHONE)));
                user.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.USER_EMAIL)));
                user.setAddress(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.USER_ADDRESS)));
                user.setRole(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.USER_ROLE)));
                user.setPassword(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.USER_PASSWORD)));
                users.add(user);
            }
        } catch (Exception e) {
            MainActivity.getDB().getReadableDatabase().endTransaction();
            Log.d("error : ", e.getMessage());
        }
        return users;
    }

    @Override
    public List<User> searchByKeyword(String query) {
        return null;
    }

    @Override
    public List<User> searchByID(Integer integer) {
        return null;
    }

    @Override
    public List<User> sortAsc() {
        return null;
    }

    @Override
    public List<User> sortDesc() {
        return null;
    }
}
