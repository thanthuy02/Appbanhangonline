package com.example.appbanhangonline.dbhandler;

import static com.example.appbanhangonline.database.DBHelper.DATABASE_NAME;
import static com.example.appbanhangonline.database.DBHelper.DATABASE_VERSION;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.appbanhangonline.activities.MainActivity;
import com.example.appbanhangonline.database.DBHelper;
import com.example.appbanhangonline.dbhandler.interfaces.IManager;
import com.example.appbanhangonline.models.User;
import com.example.appbanhangonline.utils.Logger;

import java.util.ArrayList;
import java.util.List;

public class UserHandle extends SQLiteOpenHelper implements IManager<User, Integer> {
    SQLiteDatabase db;

    DBHelper dbHelper;
    private Context context;
    public UserHandle(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public UserHandle(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static UserHandle gI(Context context) {
        return new UserHandle(context, DBHelper.DATABASE_NAME, null, DBHelper.DATABASE_VERSION);
    }

    @Override
    public void add(User user) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            String sql = String.format("INSERT INTO %s(%s,%s,%s,%s,%s,%s) VALUES('%s','%s','%s','%s','%s','%s')", DBHelper.USERS, DBHelper.USER_NAME, DBHelper.USER_PHONE, DBHelper.USER_ADDRESS, DBHelper.USER_EMAIL, DBHelper.USER_PASSWORD, DBHelper.USER_ROLE, user.getUserID(), user.getUsername(), user.getNumberPhone(), user.getAddress(), user.getEmail(), user.getPassword(), user.getRole());
            db.execSQL(sql);
            db.close();
        } catch (Exception e) {
            Logger.error(e.getMessage());
        }
    }

    @Override
    public void update(User user) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            String sql = String.format("UPDATE %s SET %s='%s',%s='%s',%s='%s',%s='%s',%s='%s',%s='%s'", DBHelper.USERS, DBHelper.USER_NAME, DBHelper.USER_PHONE, DBHelper.USER_ADDRESS, DBHelper.USER_EMAIL, DBHelper.USER_PASSWORD, DBHelper.USER_ROLE, user.getUsername(), user.getNumberPhone(), user.getAddress(), user.getEmail(), user.getPassword(), user.getRole());
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
            String sql = String.format("DELETE * FROM %s where %s=%d", DBHelper.USERS, DBHelper.USER_ID, integer);
            db.execSQL(sql);
            db.close();
            // query to remove customer by user
            return true;
        } catch (Exception e) {
            Logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public User getById(Integer userId) {
        User user = null;
        try {
            DBHelper dbHelper = MainActivity.getDB();
            SQLiteDatabase database = dbHelper.getReadableDatabase();

            String selection = DBHelper.USER_ID + "=?";
            String[] selectionArgs = {String.valueOf(userId)};

            Cursor cursor = database.query(DBHelper.USERS, null, selection, selectionArgs, null, null, null);

            if (cursor.moveToFirst()) {
                user = new User();
                user.setUserID(userId);
                user.setUsername(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.USER_NAME)));
                user.setNumberPhone(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.USER_PHONE)));
                user.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.USER_EMAIL)));
                user.setAddress(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.USER_ADDRESS)));
                user.setRole(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.USER_ROLE)));
                user.setPassword(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.USER_PASSWORD)));
            }

            cursor.close();
        } catch (Exception e) {
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
            SQLiteDatabase database = getReadableDatabase();
            String sql = String.format("SELECT * from %s", DBHelper.USERS);
            Log.d("sql :: ", sql);
            Cursor cursor = database.rawQuery(sql, null);
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
            database.close();
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

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
