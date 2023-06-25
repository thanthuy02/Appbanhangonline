package com.example.appbanhangonline.dbhandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.appbanhangonline.activities.MainActivity;
import com.example.appbanhangonline.database.DBHelper;
import com.example.appbanhangonline.dbhandler.interfaces.IManager;
import com.example.appbanhangonline.models.Bill;
import com.example.appbanhangonline.models.DetailBill;
import com.example.appbanhangonline.utils.Logger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BillHandler extends SQLiteOpenHelper implements IManager<Bill, Integer> {

    public BillHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static BillHandler gI(Context context) {
        return new BillHandler(context, DBHelper.DATABASE_NAME, null, DBHelper.DATABASE_VERSION);
    }
    public List<Bill> getByUserID(int userID) {
        List<Bill> bills = new ArrayList<>();
        try {
            SQLiteDatabase database = getReadableDatabase();
            String[] projection = {
                    DBHelper.BILL_ID,
                    DBHelper.BILL_CUSTOMER_ID,
                    DBHelper.BILL_TOTAL_PRICE,
                    DBHelper.BILL_CREATED_AT
            };
            String selection = DBHelper.BILL_CUSTOMER_ID + "=?";
            String[] selectionArgs = { String.valueOf(userID) };
            Cursor cursor = database.query(DBHelper.BILLS, projection, selection, selectionArgs, null, null, null);

            while (cursor.moveToNext()) {
                Bill bill = new Bill();
                bill.setBillID(cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.BILL_ID)));
                bill.setBillCustomerID(cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.BILL_CUSTOMER_ID)));
                bill.setBillTotalPrice(cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.BILL_TOTAL_PRICE)));
                bill.setCreatedAt(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.BILL_CREATED_AT)));
                bills.add(bill);
            }
        } catch (Exception e) {
            Logger.error(e.getMessage());
        }
        return bills;
    }

    public List<Bill> getAllBill() {
        List<Bill> bills = new ArrayList<>();
        try {
            SQLiteDatabase database = getReadableDatabase();
            String[] projection = {
                    DBHelper.BILL_ID,
                    DBHelper.BILL_CUSTOMER_ID,
                    DBHelper.BILL_TOTAL_PRICE,
                    DBHelper.BILL_CREATED_AT
            };
            String sortOrder = DBHelper.BILL_CREATED_AT + " DESC";
            Cursor cursor = database.query(DBHelper.BILLS, projection, null, null, null, null, sortOrder);

            while (cursor.moveToNext()) {
                Bill bill = new Bill();
                bill.setBillID(cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.BILL_ID)));
                bill.setBillCustomerID(cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.BILL_CUSTOMER_ID)));
                bill.setBillTotalPrice(cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.BILL_TOTAL_PRICE)));
                bill.setCreatedAt(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.BILL_CREATED_AT)));
                bills.add(bill);
            }
        } catch (Exception e) {
            Logger.error(e.getMessage());
        }
        return bills;
    }




    @Override
    public void add(Bill bill) {
        DBHelper dbHelper = MainActivity.getDB();
        String sql = String.format("INSERT INTO %s(%s,%s,%s) VALUES('%s','%s',%2f)",
                DBHelper.BILLS,
                DBHelper.BILL_CUSTOMER_ID,
                DBHelper.BILL_CREATED_AT,
                DBHelper.BILL_TOTAL_PRICE,
                bill.getBillCustomerID(),
                bill.getCreatedAt(),
                bill.getBillTotalPrice()
        );
        dbHelper.queryData(sql);

        DetailBill detailBill = bill.getDetailBill();
        if (detailBill != null) {
            // insert into detail bill
            String sql_detail = String.format("INSERT INTO %s(%s,%s,%s,%s) VALUES('%s','%s',%d,%2f)",
                    DBHelper.DETAILED_BILL_BILL_ID,
                    DBHelper.DETAILED_BILL_PRODUCT_ID,
                    DBHelper.DETAILED_BILL_QUANTITY,
                    DBHelper.DETAILED_BILL_PRICE
            );
            dbHelper.queryData(sql_detail);
        } else {
            throw new RuntimeException("no bill detail to add");
        }

    }

    public int insertBill(Bill bill) {
        DBHelper dbHelper = MainActivity.getDB();

        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ContentValues values = new ContentValues();
        values.put("user_id", bill.getBillCustomerID());
        String currentTime = date.format(new Date(System.currentTimeMillis()));
        values.put("created_at", currentTime);
        values.put("total_price", bill.getBillTotalPrice());

        long result = dbHelper.getWritableDatabase().insert("bills", null, values);
        if (result <= 0) {
            return -1;
        } else {
            return 1;
        }
    }

    public int getBillIdNew() {
        DBHelper dbHelper = MainActivity.getDB();

        int bill_id = -1;
        String query = "SELECT id FROM bills ORDER BY id DESC LIMIT 1;";

        Cursor c = dbHelper.getReadableDatabase().rawQuery(query, null);
        c.moveToFirst();
        bill_id = c.getInt(0);
        c.close();
        return bill_id;
    }

    @Override
    public void update(Bill bill) {

    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }

    @Override
    public Bill getById(Integer integer) {
        return null;
    }

    @Override
    public List<Bill> getListByPage(int page, int limit) {
        return null;
    }

    @Override
    public List<Bill> getAll() {
        return null;
    }

    @Override
    public List<Bill> searchByKeyword(String query) {
        return null;
    }

    @Override
    public List<Bill> searchByID(Integer integer) {
        return null;
    }

    @Override
    public List<Bill> sortAsc() {
        return null;
    }

    @Override
    public List<Bill> sortDesc() {
        return null;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
