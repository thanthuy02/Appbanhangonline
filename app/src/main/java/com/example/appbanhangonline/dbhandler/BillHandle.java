package com.example.appbanhangonline.dbhandler;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.appbanhangonline.activities.MainActivity;
import com.example.appbanhangonline.database.DBHelper;
import com.example.appbanhangonline.dbhandler.interfaces.IManager;
import com.example.appbanhangonline.models.Bill;
import com.example.appbanhangonline.models.DetailBill;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BillHandle implements IManager<Bill, Integer> {

    private static BillHandle I;
    private final DBHelper mDbHelper;

    public BillHandle() {
        this.mDbHelper = MainActivity.getDB();
    }

    public static BillHandle gI() {
        if (I == null) {
            I = new BillHandle();
        }
        return I;
    }

    @Override
    public void add(Bill bill) {
        String sql = String.format("INSERT INTO %s(%s,%s,%s) VALUES('%s','%s',%2f)",
                DBHelper.BILLS,
                DBHelper.BILL_CUSTOMER_ID,
                DBHelper.BILL_CREATED_AT,
                DBHelper.BILL_TOTAL_PRICE,
                bill.getBillCustomerID(),
                bill.getCreatedAt(),
                bill.getBillTotalPrice()
        );
        mDbHelper.queryData(sql);

        DetailBill detailBill = bill.getDetailBill();
        if (detailBill != null) {
            // insert into detail bill
            String sql_detail = String.format("INSERT INTO %s(%s,%s,%s,%s) VALUES('%s','%s',%d,%2f)",
                    DBHelper.DETAILED_BILL_BILL_ID,
                    DBHelper.DETAILED_BILL_PRODUCT_ID,
                    DBHelper.DETAILED_BILL_QUANTITY,
                    DBHelper.DETAILED_BILL_PRICE
            );
            mDbHelper.queryData(sql_detail);
        } else {
            throw new RuntimeException("no bill detail to add");
        }

    }

    public int insertBill(Bill bill){
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ContentValues values = new ContentValues();
        values.put("user_id", bill.getBillCustomerID());
        String currentTime = date.format(new Date(System.currentTimeMillis()));
        values.put("created_at", currentTime);
        values.put("total_price", bill.getBillTotalPrice());

        long result = mDbHelper.getWritableDatabase().insert("bills", null, values);
        if (result <= 0) {
            return -1;
        } else {
            return 1;
        }
    }

    public int getBillIdNew() {
        int bill_id = -1;
        String query = "SELECT id FROM bills ORDER BY id DESC LIMIT 1;";

        Cursor c = mDbHelper.getReadableDatabase().rawQuery(query, null);
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
}
