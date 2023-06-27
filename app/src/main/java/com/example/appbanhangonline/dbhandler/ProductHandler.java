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

    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products";
        Cursor c = dbHelper.getReadableDatabase().rawQuery(query, null);
        while (c.moveToNext()) {
            Product product = new Product();
            product.setProductID(c.getInt(0));
            product.setProductName(c.getString(1));
            product.setCategoryName(getCategoryName(c.getInt(2)));
            product.setQuantity(c.getInt(3));
            product.setPrice(c.getInt(4));
            product.setImage(c.getString(5));
            products.add(product);
        }
        c.close();
        return products;
    }

    public List<String> getAllNameCategory() {
        List<String> listCategoryName = new ArrayList<String>();
        String selectQuery = "SELECT * FROM categories;";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                listCategoryName.add(c.getString(1));

            } while (c.moveToNext());
        }
        c.close();
        return listCategoryName;
    }

    public String getCategoryName(int category_id) {
        String categoryName = "";
        String query = "SELECT category FROM categories WHERE id = ?";
        Cursor cursor = dbHelper.getReadableDatabase().rawQuery(query, new String[] {"" + category_id});
        if (cursor.moveToFirst()) {
            categoryName = cursor.getString(0);
        }
        cursor.close();
        return categoryName;
    }



    public int getCategoryIdByName(String category) {
        int categoryId = 0;
        String query = "SELECT id FROM categories WHERE categories.category = ?";
        Cursor cursor = dbHelper.getReadableDatabase().rawQuery(query, new String[] {"" + category});
        if (cursor.moveToFirst()) {
            categoryId = cursor.getInt(0);
        }
        cursor.close();
        return categoryId;
    }

    public void add(String name, int category_id, int quantity, int price, String image) {
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
        statement.bindString(5, image);
        statement.executeInsert();
        db.close();
    }

    public Product getProductById(int productId) {
        Product product = null;
        try {
            DBHelper dbHelper = MainActivity.getDB();
            SQLiteDatabase database = dbHelper.getReadableDatabase();

            String selection = DBHelper.PRODUCT_ID + "=?";
            String[] selectionArgs = {String.valueOf(productId)};

            Cursor cursor = database.query(DBHelper.PRODUCTS, null, selection, selectionArgs, null, null, null);

            if (cursor.moveToFirst()) {
                product = new Product();
                product.setProductID(productId);
                product.setProductName(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.PRODUCT_NAME)));
//                product.setCategoryName(cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.P)));
                product.setQuantity(cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.PRODUCT_QUANTITY)));
                product.setPrice(cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.PRODUCT_PRICE)));
                product.setImage(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.PRODUCT_IMAGE)));
            }

            cursor.close();
        } catch (Exception e) {
            Log.d("error : ", e.getMessage());
        }
        return product;
    }


    public void edit(int id, String name, int category_id, int quantity, int price, String image) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "UPDATE products SET name = ? , category_id = ? , quantity = ?, price = ?, image = ? Where id = ? ";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, name);
        statement.bindLong(2, category_id);
        statement.bindLong(3, quantity);
        statement.bindLong(4, price);
        statement.bindString(5, image);
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
