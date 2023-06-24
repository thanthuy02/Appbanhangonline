package com.example.appbanhangonline.dbhandler;

import static com.example.appbanhangonline.database.DBHelper.DATABASE_NAME;
import static com.example.appbanhangonline.database.DBHelper.DATABASE_VERSION;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.appbanhangonline.database.DBHelper;
import com.example.appbanhangonline.models.DetailBill;

public class DetailBillHandler extends SQLiteOpenHelper {

    SQLiteDatabase db;

    DBHelper dbHelper;
    private Context context;

    //    ham tao
    public DetailBillHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public int insertDetailBill(DetailBill detailBill){
        ContentValues values = new ContentValues();
        values.put("bill_id", detailBill.getBillID());
        values.put("product_id", detailBill.getProductId());
        values.put("quantity", detailBill.getQuantity());
        values.put("price", detailBill.getPrice());

        long result = dbHelper.getWritableDatabase().insert("detailed_bills", null, values);
        if (result <= 0) {
            return -1;
        } else {
            return 1;
        }
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
