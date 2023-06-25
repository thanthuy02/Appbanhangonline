package com.example.appbanhangonline.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import com.example.appbanhangonline.dbhandler.LoginHandler;

public class DBHelper extends SQLiteOpenHelper {
    public Cursor getData(String sql) {
        return getReadableDatabase().rawQuery(sql, null);
    }

    public void queryData(String sql) {
        getWritableDatabase().execSQL(sql);
    }


//  define database name

    public static final String DATABASE_NAME = "SMS.db";

    //  define database version
    public static final int DATABASE_VERSION = 1;

    // Constructor
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //    define tables
    public static final String USERS = "users";
    public static final String CATEGORIES = "categories";
    public static final String PRODUCTS = "products";
    public static final String BILLS = "bills";
    public static final String DETAILED_BILLS = "detailed_bills";

    //    columns for table users
    public static final String USER_ID = "id";
    public static final String USER_NAME = "name";
    public static final String USER_PHONE = "phone";
    public static final String USER_ADDRESS = "address";
    public static final String USER_EMAIL = "email";
    public static final String USER_PASSWORD = "password";
    public static final String USER_ROLE = "role";

    //    columns for categories
    public static final String CATEGORY_ID = "id";
    public static final String CATEGORY_NAME = "category";

    //    columns for products
    public static final String PRODUCT_ID = "id";
    public static final String PRODUCT_NAME = "name";
    public static final String PRODUCT_CATEGORY_ID = "category_id";
    public static final String PRODUCT_QUANTITY = "quantity";
    public static final String PRODUCT_PRICE = "price";
    public static final String PRODUCT_IMAGE = "image";

    //    columns for bills
    public static final String BILL_ID = "id";
    public static final String BILL_CUSTOMER_ID = "user_id";
    public static final String BILL_CREATED_AT = "created_at";
    public static final String BILL_TOTAL_PRICE = "total_price";

    //    columns for detailed bills
    public static final String DETAILED_BILL_ID = "id";
    public static final String DETAILED_BILL_BILL_ID = "bill_id";
    public static final String DETAILED_BILL_PRODUCT_ID = "product_id";
    public static final String DETAILED_BILL_QUANTITY = "quantity";
    public static final String DETAILED_BILL_PRICE = "price";


    //    create table users
    private static final String CREATE_USERS = "CREATE TABLE IF NOT EXISTS "
            + USERS + "("
            + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + USER_NAME + " TEXT, "
            + USER_PHONE + " TEXT UNIQUE,"
            + USER_ADDRESS + " TEXT,"
            + USER_EMAIL + " TEXT UNIQUE,"
            + USER_PASSWORD + " TEXT,"
            + USER_ROLE + " TEXT" + ")";

    //    create table categories
    private static final String CREATE_CATEGORIES = "CREATE TABLE IF NOT EXISTS "
            + CATEGORIES + "("
            + CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + CATEGORY_NAME + " TEXT" + ")";

    //   create table products
    private static final String CREATE_PRODUCTS = "CREATE TABLE IF NOT EXISTS "
            + PRODUCTS + "("
            + PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + PRODUCT_NAME + " TEXT,"
            + PRODUCT_CATEGORY_ID + " INTEGER,"
            + PRODUCT_QUANTITY + " INTEGER,"
            + PRODUCT_PRICE + " INTEGER,"
            + PRODUCT_IMAGE + " TEXT,"
            + "FOREIGN KEY(" + PRODUCT_CATEGORY_ID + ") REFERENCES " + CATEGORIES + "(" + CATEGORY_ID + ")"
            + ")";

    //   create table bills
    private static final String CREATE_BILLS = "CREATE TABLE IF NOT EXISTS "
            + BILLS + "("
            + BILL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + BILL_CUSTOMER_ID + " TEXT,"
            + BILL_CREATED_AT + " TEXT DEFAULT CURRENT_TIMESTAMP,"
            + BILL_TOTAL_PRICE + " INTEGER,"
            + "FOREIGN KEY(" + BILL_CUSTOMER_ID + ") REFERENCES " + USERS + "(" + USER_ID + ")"
            + ")";

    //    create table detailed bills
    private static final String CREATE_DETAILED_BILLS = "CREATE TABLE IF NOT EXISTS "
            + DETAILED_BILLS + "("
            + DETAILED_BILL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + DETAILED_BILL_BILL_ID + " INTEGER,"
            + DETAILED_BILL_PRODUCT_ID + " TEXT DEFAULT CURRENT_TIMESTAMP,"
            + DETAILED_BILL_QUANTITY + " INTEGER,"
            + DETAILED_BILL_PRICE + " INTEGER,"
            + "FOREIGN KEY(" + DETAILED_BILL_BILL_ID + ") REFERENCES " + BILLS + "(" + BILL_ID + "),"
            + "FOREIGN KEY(" + DETAILED_BILL_PRODUCT_ID + ") REFERENCES " + PRODUCTS + "(" + PRODUCT_ID + ")"
            + ")";

    //    insert admin info into table users
    private static final String INSERT_USER = "INSERT INTO " + USERS + " ("
            + USER_NAME + ", "
            + USER_PHONE + ", "
            + USER_ADDRESS + ", "
            + USER_EMAIL + ", "
            + USER_PASSWORD + ", "
            + USER_ROLE + ") "
            + "VALUES ('Than Thi Thuy', '012345678', 'Dong Da', 'thanthuy2811@gmail.com', 'admin', 'admin'),"
            + "('Dao Thi Kieu Trang' , '023456789', 'Dong Da', 'kieutrang021002@gmail.com', 'admin', 'admin'),"
            + "('Bui Thi Thu Uyen', '012345679', 'Dong Da', 'buiuyen1207@gmail.com', 'admin', 'admin'),"
            + "('ABC', '013345679', 'Dong Da', 'customer@gmail.com', '123', 'customer')";

    private static final String INSERT_CATEGORY = "INSERT INTO " + CATEGORIES + " (" + CATEGORY_NAME + ") "
            + "VALUES ('Bút'),"
            + "('Vở'),"
            + "('Máy tính'),"
            + "('Đèn học')";

//    String imagePath1 = "android.resource://" + context.getPackageName() + "/drawable/pd1";
//    String imagePath2 = "android.resource://" + context.getPackageName() + "/drawable/pd2";
//    String imagePath3 = "android.resource://" + context.getPackageName() + "/drawable/pd3";
//    String imagePath4 = "android.resource://" + context.getPackageName() + "/drawable/pd4";
//    String imagePath5 = "android.resource://" + context.getPackageName() + "/drawable/pd5";
//    String imagePath6 = "android.resource://" + context.getPackageName() + "/drawable/pd6";
//    String imagePath7 = "android.resource://" + context.getPackageName() + "/drawable/pd7";
//    String imagePath8 = "android.resource://" + context.getPackageName() + "/drawable/pd8";
//    String imagePath9 = "android.resource://" + context.getPackageName() + "/drawable/pd9";
//    String imagePath10 = "android.resource://" + context.getPackageName() + "/drawable/pd10";
//    String imagePath11 = "android.resource://" + context.getPackageName() + "/drawable/pd11";

//    Uri imageUri1 = Uri.parse(imagePath1);
//    Uri imageUri2 = Uri.parse(imagePath2);
//    Uri imageUri3 = Uri.parse(imagePath3);
//    Uri imageUri4 = Uri.parse(imagePath4);
//    Uri imageUri5 = Uri.parse(imagePath5);
//    Uri imageUri6 = Uri.parse(imagePath6);
//    Uri imageUri7 = Uri.parse(imagePath7);
//    Uri imageUri8 = Uri.parse(imagePath8);
//    Uri imageUri9 = Uri.parse(imagePath9);
//    Uri imageUri10 = Uri.parse(imagePath10);
//    Uri imageUri11 = Uri.parse(imagePath11);
//
//    String image1 = imageUri1.toString();
//    String image2 = imageUri2.toString();
//    String image3 = imageUri3.toString();
//    String image4 = imageUri4.toString();
//    String image5 = imageUri5.toString();
//    String image6 = imageUri6.toString();
//    String image7 = imageUri7.toString();
//    String image8 = imageUri8.toString();
//    String image9 = imageUri9.toString();
//    String image10 = imageUri10.toString();
//    String image11 = imageUri11.toString();

//    private static final String INSERT_PRODUCT = "INSERT INTO " + PRODUCTS + " ("
//            + PRODUCT_NAME + ", "
//            + PRODUCT_CATEGORY_ID + ", "
//            + PRODUCT_QUANTITY + ", "
//            + PRODUCT_PRICE + ", "
//            + PRODUCT_IMAGE + ") "
//            + "VALUES ('Bút bi', 1, 35, 6000, image1),"
//            + "('Bút 3 màu', 1, 15, 15000, image2),"
//            + "('Đèn học trắng', 4, 21, 85000, image3),"
//            + "('Vở bìa cứng', 2, 10, 28000, image4),"
//            + "('Vở 200 trang', 2, 4, 20000, image5),"
//            + "('Vở lò xo', 2, 12, 56000, image6),"
//            + "('Máy tính 570', 3, 15, 427000, image7),"
//            + "('Máy tính 580', 3, 6, 510000, image8),"
//            + "('Đèn học chân cao', 4, 5, 90000, image9),"
//            + "('Đèn học chân cao', 4, 5, 90000, image10),"
//            + "('Bút chì 2B', 1, 56, 4000, image11)";

    private static final String TRIGGER_QUANTITY = "CREATE TRIGGER update_quantity " +
            "AFTER INSERT ON detailed_bills " +
            "for each ROW " +
            "BEGIN " +
            "UPDATE products " +
            "SET quantity = quantity - NEW.quantity " +
            "WHERE products.id = NEW.product_id; " +
            "END; ";

    @Override
    public void onCreate(SQLiteDatabase db) {
//    execute SQLite commands to create table
        db.execSQL(CREATE_USERS);
        db.execSQL(CREATE_CATEGORIES);
        db.execSQL(CREATE_PRODUCTS);
        db.execSQL(CREATE_BILLS);
        db.execSQL(CREATE_DETAILED_BILLS);
        db.execSQL(INSERT_USER);
        db.execSQL(TRIGGER_QUANTITY);
        db.execSQL(INSERT_CATEGORY);
        //db.execSQL(INSERT_PRODUCT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//     refresh table (drop, then create tables again)
    }
}
