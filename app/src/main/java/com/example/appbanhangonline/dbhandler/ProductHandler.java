package com.example.appbanhangonline.dbhandler;

import static com.example.appbanhangonline.database.DBHelper.DATABASE_NAME;
import static com.example.appbanhangonline.database.DBHelper.DATABASE_VERSION;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.example.appbanhangonline.activities.MainActivity;
import com.example.appbanhangonline.database.DBHelper;
import com.example.appbanhangonline.models.Category;
import com.example.appbanhangonline.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductHandler extends SQLiteOpenHelper {
    SQLiteDatabase db;

    DBHelper dbHelper;
    private Context context;
    CategoryHandle categoryHandle = new CategoryHandle();

    //    ham tao
    public ProductHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        try {
            DBHelper dbHelper = MainActivity.getDB();
            assert dbHelper != null;
            dbHelper.getReadableDatabase().beginTransaction();
            String sql = String.format("SELECT * from %s", DBHelper.PRODUCTS);
            Log.d("sql :: ", sql);
            Cursor cursor = dbHelper.getData(sql);

            while (cursor.moveToNext()){
                products.add(new Product(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(2), cursor.getInt(3), cursor.getString(5)));
            }
        } catch (Exception e) {
            MainActivity.getDB().getReadableDatabase().endTransaction();
            Log.d("error : ", e.getMessage());
        }
        return products;
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

//    public ArrayList<Product> getAllProduct() {
//        ArrayList<Product> productList = new ArrayList<>();
//        String query = "SELECT * FROM products";
//        Cursor c = dbHelper.getData(query);
//        while (c.moveToNext()) {
//            Product product = new Product();
//            product.setProductID(c.getInt(0));
//            product.setProductName(c.getString(1));
//            product.setCategoryID(c.getInt(2));
//            product.setQuantity(c.getInt(3));
//            product.setPrice(c.getDouble(4));
//            product.setImage(c.getBlob(5));
//
//            productList.add(product);
//            c.moveToNext();
//        }
//        c.close();
//        return productList;
//    }
//
//    public Product getProductById(int id) {
//        Product product = null;
//        String query = "SELECT * FROM products WHERE id = ?";
//        Cursor cursor = dbHelper.getReadableDatabase().rawQuery(query, new String[] {"" + id});
//        if (cursor.moveToFirst()) {
//            int productId = cursor.getInt(0);
//            String name = cursor.getString(1);
//            int categoryId = cursor.getInt(2);
//            int quantity = cursor.getInt(3);
//            double price = cursor.getDouble(4);
//            byte[] image = cursor.getBlob(5);
//
//            product = new Product(productId, name, categoryId, quantity, price, image);
//        }
//        cursor.close();
//        return product;
//    }

}
