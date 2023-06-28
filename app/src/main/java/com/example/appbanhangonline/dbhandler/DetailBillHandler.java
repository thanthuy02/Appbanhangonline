package com.example.appbanhangonline.dbhandler;

import static com.example.appbanhangonline.database.DBHelper.DATABASE_NAME;
import static com.example.appbanhangonline.database.DBHelper.DATABASE_VERSION;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.appbanhangonline.database.DBHelper;
import com.example.appbanhangonline.models.DetailBill;
import com.example.appbanhangonline.utils.Logger;

import java.util.ArrayList;
import java.util.List;

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

    public void insertDetailBill(DetailBill detailBill){
        ContentValues values = new ContentValues();
        values.put("bill_id", detailBill.getBillID());
        values.put("product_id", detailBill.getProductId());
        values.put("quantity", detailBill.getQuantity());
        values.put("price", detailBill.getPrice());

        long insertedRowId = db.insert("detailed_bills", null, values);
        if (insertedRowId != -1) {
            System.out.println("Đã thêm vào bảng detail_bill thành công");
        } else {
            System.out.println("Lỗi thêm vào bảng detail_bill");
        }
    }
    public List<DetailBill> getBillDetailsByBillID(int billID) {
        List<DetailBill> billDetails = new ArrayList<>();
        SQLiteDatabase database = null;
        Cursor cursor = null;

        try {
            database = getReadableDatabase();
            String[] projection = {
                    "product_id",
                    "quantity",
                    "price"
            };
            String selection = "bill_id=?";
            String[] selectionArgs = {String.valueOf(billID)};

            cursor = database.query("detailed_bills", projection, selection, selectionArgs, null, null, null);

            while (cursor.moveToNext()) {
                int productId = cursor.getInt(cursor.getColumnIndexOrThrow("product_id"));
                int quantity = cursor.getInt(cursor.getColumnIndexOrThrow("quantity"));
                int price = cursor.getInt(cursor.getColumnIndexOrThrow("price"));

                DetailBill detailBill = new DetailBill();
                detailBill.setProductId(productId);
                detailBill.setQuantity(quantity);
                detailBill.setPrice(price);

                billDetails.add(detailBill);
            }
        } catch (Exception e) {
            Logger.error(e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (database != null) {
                database.close();
            }
        }
        return billDetails;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
