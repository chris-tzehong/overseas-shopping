package com.example.overseasshopping;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.overseasshopping.Model.User;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {
    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number.
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "myDatabase.db";

    // User table name
    private static final String TABLE_USER = "user";
    private static final String TABLE_PRODUCT = "product";
    private static final String TABLE_ORDERS = "orders";
    private static final String TABLE_MESSAGE = "message";
    private static final String TABLE_CREDITCARD = "creditCard";
    private static final String TABLE_RATING = "rating";

    // User Table Columns names
    private static final String COLUMN_USER_NO = "user_no";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_TELEPHONE = "telephone";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_RATING = "rating";
    private static final String COLUMN_TOTAL_RATED_BY = "totalRatedBy";

    private static final String COLUMN_PRODUCT_NO = "product_no";
    private static final String COLUMN_PRODUCT_NAME = "product_name";
    private static final String COLUMN_PHOTO = "photo";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_PRODUCT_QUANTITY = "product_quantity";

    private static final String COLUMN_ORDER_NO = "order_no";
    private static final String COLUMN_SELLER = "seller";
    private static final String COLUMN_BUYER = "buyer";
    private static final String COLUMN_TIME = "time";

    private static final String COLUMN_MESSAGE_NO = "message_no";
    private static final String COLUMN_USER1 = "user1";
    private static final String COLUMN_USER2 = "user2";
    private static final String COLUMN_MESSAGE_TIME = "message_time";
    private static final String COLUMN_MESSAGE = "MESSAGE";

    private static final String COLUMN_CREDITCARD_ID = "creditcard_id";
    private static final String COLUMN_CREDITCARD_NO = "creditcard_no";
    private static final String COLUMN_SECURITY_NO = "security_no";
    private static final String COLUMN_EXPIRY_DATE = "expiry_date";

    private static final String COLUMN_RATING_NO = "rating_no";
    private static final String COLUMN_RATED_BY = "rated_by";


    // create table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_NO + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USERNAME + " TEXT," + COLUMN_PASSWORD + " TEXT,"
            + COLUMN_TELEPHONE + " TEXT," + COLUMN_ADDRESS + " TEXT,"
            + COLUMN_RATING + "INTEGER" + COLUMN_TOTAL_RATED_BY + "INTEGER" + ")";

    private String CREATE_PRODUCT_TABLE = "CREATE TABLE " + TABLE_PRODUCT + "("
            + COLUMN_PRODUCT_NO + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_PRODUCT_NAME + " TEXT," + COLUMN_PHOTO + " TEXT,"
            + COLUMN_DESCRIPTION + " TEXT," + COLUMN_PRICE + " INTEGER,"
            + COLUMN_USER_NO + " INTEGER " + COLUMN_PRODUCT_QUANTITY + " INTEGER " + ")";

    private String CREATE_ORDERS_TABLE = "CREATE TABLE " + TABLE_ORDERS + "("
            + COLUMN_ORDER_NO + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_SELLER + " TEXT," + COLUMN_BUYER + " TEXT,"
            + COLUMN_TIME + " DATETIME," + COLUMN_PRODUCT_NO + " INTEGER" + ")";

    private String CREATE_MESSAGE_TABLE = "CREATE TABLE " + TABLE_MESSAGE + "("
            + COLUMN_MESSAGE_NO + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USER1 + " INTEGER," + COLUMN_USER2 + " INTEGER,"
            + COLUMN_MESSAGE_TIME + " DATETIME," + COLUMN_MESSAGE + " TEXT" + ")";

    private String CREATE_CREDITCARD_TABLE = "CREATE TABLE " + TABLE_CREDITCARD + "("
            + COLUMN_CREDITCARD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_CREDITCARD_NO + " INTEGER," + COLUMN_SECURITY_NO + " INTEGER,"
            + COLUMN_EXPIRY_DATE + " DATE," + COLUMN_USER_NO + " INTEGER " + ")";

    private String CREATE_RATING_TABLE = "CREATE TABLE " + TABLE_RATING + "("
            + COLUMN_RATING_NO + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USER_NO + " INTEGER, " + COLUMN_RATING + " INTEGER,"
            + COLUMN_RATED_BY + " TEXT" + ")";

    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;
    private String DROP_PRODUCT_TABLE = "DROP TABLE IF EXISTS " + TABLE_PRODUCT;
    private String DROP_ORDERS_TABLE = "DROP TABLE IF EXISTS " + TABLE_ORDERS;
    private String DROP_MESSAGE_TABLE = "DROP TABLE IF EXISTS " + TABLE_MESSAGE;
    private String DROP_CREDITCARD_TABLE = "DROP TABLE IF EXISTS " + TABLE_CREDITCARD;
    private String DROP_RATING_TABLE = "DROP TABLE IF EXISTS " + TABLE_RATING;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //All necessary tables you like to create will create here
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_PRODUCT_TABLE);
        db.execSQL(CREATE_ORDERS_TABLE);
        db.execSQL(CREATE_MESSAGE_TABLE);
        db.execSQL(CREATE_CREDITCARD_TABLE);
        db.execSQL(CREATE_RATING_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop table if existed, all data will be gone!!!
        db.execSQL(DROP_USER_TABLE);
        db.execSQL(DROP_PRODUCT_TABLE);
        db.execSQL(DROP_ORDERS_TABLE);
        db.execSQL(DROP_MESSAGE_TABLE);
        db.execSQL(DROP_CREDITCARD_TABLE);
        db.execSQL(DROP_RATING_TABLE);
        onCreate(db);
    }

    //----------------------------------------User Database----------------------------------------//

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, user.getUsername());
        values.put(COLUMN_PASSWORD, user.getPassword());
        values.put(COLUMN_TELEPHONE, user.getTelephone());
        values.put(COLUMN_ADDRESS, user.getAddress());
        values.put(COLUMN_RATING, user.getRating());
        values.put(COLUMN_TOTAL_RATED_BY, user.getTotalRatedBy());

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public User getUser(User user_no) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_NO,
                COLUMN_USERNAME,
                COLUMN_PASSWORD,
                COLUMN_TELEPHONE,
                COLUMN_ADDRESS,
                COLUMN_RATING,
                COLUMN_TOTAL_RATED_BY
        };

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_no,user_name,user_email,password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                null); //The sort order


        // Traversing through all rows and adding to list
        if (cursor != null ) {
            cursor.moveToFirst();
                User user = new User();
                user.setUserNo(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NO))));
                user.setUsername(cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)));
                user.setTelephone(cursor.getString(cursor.getColumnIndex(COLUMN_TELEPHONE)));
                user.setAddress(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)));
                user.setRating(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_RATING))));
                user.setTotalRatedBy(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_TOTAL_RATED_BY))));

            cursor.close();
            return user;
        }
        
        db.close();
        return null;
    }

    public List<User> getAllUser() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_NO,
                COLUMN_USERNAME,
                COLUMN_PASSWORD,
                COLUMN_TELEPHONE,
                COLUMN_ADDRESS,
                COLUMN_RATING,
                COLUMN_TOTAL_RATED_BY
        };
        // sorting orders
        String sortOrder =
                COLUMN_USERNAME + " ASC";
        List<User> userList = new ArrayList<User>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_no,user_name,user_email,password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setUserNo(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NO))));
                user.setUsername(cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)));
                user.setTelephone(cursor.getString(cursor.getColumnIndex(COLUMN_TELEPHONE)));
                user.setAddress(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)));
                user.setRating(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_RATING))));
                user.setTotalRatedBy(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_TOTAL_RATED_BY))));
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }

    /**
     * This method to update user record
     *
     * @param user
     */
    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, user.getUsername());
        values.put(COLUMN_PASSWORD, user.getPassword());
        values.put(COLUMN_TELEPHONE, user.getTelephone());
        values.put(COLUMN_ADDRESS, user.getAddress());
        values.put(COLUMN_RATING, user.getRating());
        values.put(COLUMN_TOTAL_RATED_BY, user.getTotalRatedBy());

        // updating row
        db.update(TABLE_USER, values, COLUMN_USER_NO + " = ?",
                new String[]{String.valueOf(user.getUserNo())});
        db.close();
    }

    /**
     * This method is to delete user record
     *
     * @param user
     */
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_USER, COLUMN_USER_NO + " = ?",
                new String[]{String.valueOf(user.getUserNo())});
        db.close();
    }

    /**
     * This method to check user exist or not
     *
     * @param username
     * @return true/false
     */
    public boolean checkUser(String username) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_NO
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_USERNAME + " = ?";

        // selection argument
        String[] selectionArgs = {username};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_no FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    /**
     * This method to check user exist or not
     *
     * @param username
     * @param password
     * @return true/false
     */
    public boolean checkUser(String username, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_NO
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USERNAME + " = ?" + " AND " + COLUMN_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {username, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_no FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    //----------------------------------------Product Database----------------------------------------//

    public void addProduct(Product product, User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_NAME, product.getProductName());
        values.put(COLUMN_PHOTO, product.getPhoto());
        values.put(COLUMN_DESCRIPTION, product.getDescription());
        values.put(COLUMN_PRICE, product.getPrice());
        values.put(COLUMN_USER_NO, user.getUserNo());
        values.put(COLUMN_PRODUCT_QUANTITY, product.getProductQuantity());

        // Inserting Row
        db.insert(TABLE_PRODUCT, null, values);
        db.close();
    }

    /**
     * This method is to fetch all product and return the list of product records
     *
     * @return list
     */
    public List<Product> getAllProduct() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_PRODUCT_NO,
                COLUMN_PRODUCT_NAME,
                COLUMN_PHOTO,
                COLUMN_DESCRIPTION,
                COLUMN_PRICE,
                COLUMN_USER_NO,
                COLUMN_PRODUCT_QUANTITY,

        };
        // sorting orders
        String sortOrder =
                COLUMN_PRODUCT_NAME + " ASC";
        List<Product> productList = new ArrayList<Product>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the product table
        /**
         * Here query function is used to fetch records from product table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT product_no,product_name, FROM product ORDER BY product_name;
         */
        Cursor cursor = db.query(TABLE_PRODUCT, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.setProductNo(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_NO))));
                product.setProductName(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_NAME)));
                product.setPhoto(cursor.getString(cursor.getColumnIndex(COLUMN_PHOTO)));
                product.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)));
                product.setPrice(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_PRICE))));
                product.setProductQuantity(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_QUANTITY))));
                //User.setUserNo(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NO)));
                // Adding product record to list
                productList.add(product);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return product list
        return productList;
    }

    /**
     * This method to update product record
     *
     * @param product
     */
    public void updateProduct(Product product, User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_NAME, product.getProductName());
        values.put(COLUMN_PHOTO, product.getPhoto());
        values.put(COLUMN_DESCRIPTION, product.getDescription());
        values.put(COLUMN_PRICE, product.getPrice());
        values.put(COLUMN_USER_NO, user.getUserNo());
        values.put(COLUMN_PRODUCT_QUANTITY, product.getProductQuantity());

        // updating row
        db.update(TABLE_PRODUCT, values, COLUMN_PRODUCT_NO + " = ?",
                new String[]{String.valueOf(product.getProductNo())});
        db.close();
    }

    /**
     * This method is to delete product record
     *
     * @param product
     */
    public void deleteProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete product record by id
        db.delete(TABLE_PRODUCT, COLUMN_PRODUCT_NO + " = ?",
                new String[]{String.valueOf(product.getProductNo())});
        db.close();
    }

    /**
     * This method to check product exist or not
     *
     * @param product
     * @return true/false
     */
    public boolean checkProduct(String product) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_PRODUCT_NO
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_PRODUCT_NAME + " = ?";

        // selection argument
        String[] selectionArgs = {product};

        // query product table with condition
        /**
         * Here query function is used to fetch records from product table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT product_no FROM product WHERE product_name = 'Phone';
         */
        Cursor cursor = db.query(TABLE_PRODUCT, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    //----------------------------------------Order Database----------------------------------------//

    public void addOrder(Order order, Product product) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_SELLER, product.getProductName());
        values.put(COLUMN_BUYER, product.getPhoto());
        values.put(COLUMN_TIME, product.getDescription());
        values.put(COLUMN_PRODUCT_NO, product.getPrice());

        // Inserting Row
        db.insert(TABLE_ORDERS, null, values);
        db.close();
    }

    /**
     * This method is to fetch all product and return the list of product records
     *
     * @return list
     */
    public List<Order> getAllOrder() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_ORDER_NO,
                COLUMN_SELLER,
                COLUMN_BUYER,
                COLUMN_TIME,
                COLUMN_PRODUCT_NO,

        };
        // sorting orders
        String sortOrder =
                COLUMN_TIME + " ASC";
        List<Order> orderList = new ArrayList<Order>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the order table
        /**
         * Here query function is used to fetch records from order table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT order_no,seller , FROM order_ ORDER BY seller;
         */
        Cursor cursor = db.query(TABLE_ORDERS, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Order order = new Order();
                order.setOrderNo(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ORDER_NO))));
                order.setSeller(cursor.getString(cursor.getColumnIndex(COLUMN_SELLER)));
                order.setBuyer(cursor.getString(cursor.getColumnIndex(COLUMN_BUYER)));
                order.setTime(Date.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_TIME))));
                //Product.setProductNo(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_NO)));
                // Adding order record to list
                orderList.add(order);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return order list
        return orderList;
    }

    /**
     * This method to update order record
     *
     * @param order
     */
    public void updateOrder(Order order, Product product) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_SELLER, order.getSeller());
        values.put(COLUMN_BUYER, order.getBuyer());
        values.put(COLUMN_TIME, String.valueOf(order.getTime()));
        values.put(COLUMN_PRODUCT_NO, product.getProductNo());

        // updating row
        db.update(TABLE_ORDERS, values, COLUMN_ORDER_NO + " = ?",
                new String[]{String.valueOf(order.getOrderNo())});
        db.close();
    }

    /**
     * This method is to delete order record
     *
     * @param order
     */
    public void deleteOrder(Order order) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete order record by id
        db.delete(TABLE_ORDERS, COLUMN_ORDER_NO + " = ?",
                new String[]{String.valueOf(order.getOrderNo())});
        db.close();
    }

    /**
     * This method to check order exist or not
     *
     * @param order
     * @return true/false
     */
    public boolean checkOrder(String order) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_ORDER_NO
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_ORDER_NO + " = ?";

        // selection argument
        String[] selectionArgs = {order};

        // query order table with condition
        /**
         * Here query function is used to fetch records from order table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT order_no FROM order WHERE product_name = 'Phone';
         */
        Cursor cursor = db.query(TABLE_ORDERS, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    //----------------------------------------Message Database----------------------------------------//


    //----------------------------------------CreditCard Database----------------------------------------//

    public void addCreditCard(CreditCard creditCard, User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CREDITCARD_NO, creditCard.getCreditCardNo());
        values.put(COLUMN_SECURITY_NO, creditCard.getSecurityNo());
        values.put(COLUMN_EXPIRY_DATE, String.valueOf(creditCard.getExpiryDate()));
        values.put(COLUMN_USER_NO, user.getUserNo());

        // Inserting Row
        db.insert(TABLE_CREDITCARD, null, values);
        db.close();
    }

    /**
     * This method is to fetch all product and return the list of product records
     *
     * @return list
     */
    public List<CreditCard> getAllCreditCard() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_CREDITCARD_ID,
                COLUMN_CREDITCARD_NO,
                COLUMN_SECURITY_NO,
                COLUMN_EXPIRY_DATE,
                COLUMN_USER_NO,

        };
        // sorting orders
        String sortOrder =
                COLUMN_CREDITCARD_NO + " ASC";
        List<CreditCard> creditCardList = new ArrayList<CreditCard>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the creditCard table
        /**
         * Here query function is used to fetch records from product table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT product_no,product_name, FROM product ORDER BY product_name;
         */
        Cursor cursor = db.query(TABLE_CREDITCARD, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                CreditCard creditCard = new CreditCard();
                creditCard.setCreditCardId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_CREDITCARD_ID))));
                creditCard.setCreditCardNo(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_CREDITCARD_NO))));
                creditCard.setSecurityNo(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_SECURITY_NO))));
                creditCard.setExpiryDate(Date.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_EXPIRY_DATE))));
                //Iser.setUserNo(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NO)));
                // Adding product record to list
                creditCardList.add(creditCard);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return product list
        return creditCardList;
    }

    /**
     * This method to update product record
     *
     * @param creditCard
     */
    public void updateCreditCard(CreditCard creditCard, User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CREDITCARD_NO, creditCard.getCreditCardNo());
        values.put(COLUMN_SECURITY_NO, creditCard.getSecurityNo());
        values.put(COLUMN_EXPIRY_DATE, String.valueOf(creditCard.getExpiryDate()));
        values.put(COLUMN_USER_NO, user.getUserNo());

        // updating row
        db.update(TABLE_CREDITCARD, values, COLUMN_CREDITCARD_ID + " = ?",
                new String[]{String.valueOf(creditCard.getCreditCardId())});
        db.close();
    }

    /**
     * This method is to delete product record
     *
     * @param creditCard
     */
    public void deleteCreditCard(CreditCard creditCard) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete creditCard record by id
        db.delete(TABLE_CREDITCARD, COLUMN_CREDITCARD_ID + " = ?",
                new String[]{String.valueOf(creditCard.getCreditCardId())});
        db.close();
    }

    /**
     * This method to check product exist or not
     *
     * @param creditCard
     * @return true/false
     */
    public boolean checkCreditCard(String creditCard) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_CREDITCARD_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_CREDITCARD_NO + " = ?";

        // selection argument
        String[] selectionArgs = {creditCard};

        // query creditCard table with condition
        /**
         * Here query function is used to fetch records from creditCard table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT product_no FROM product WHERE product_name = 'Phone';
         */
        Cursor cursor = db.query(TABLE_CREDITCARD, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;

    }

    //----------------------------------------Rating Database----------------------------------------//

    public void addRating(Rating rating, User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NO, user.getUserNo());
        values.put(COLUMN_RATING, rating.getRating());
        values.put(COLUMN_RATED_BY, rating.getRatedBy());

        // Inserting Row
        db.insert(TABLE_RATING, null, values);
        db.close();
    }

    /**
     * This method is to fetch all product and return the list of product records
     *
     * @return list
     */
    public List<Rating> getAllRating() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_RATING_NO,
                COLUMN_USER_NO,
                COLUMN_RATING,
                COLUMN_RATED_BY,

        };
        // sorting orders
        String sortOrder =
                COLUMN_RATING_NO + " ASC";
        List<Rating> ratingList = new ArrayList<Rating>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the product table
        /**
         * Here query function is used to fetch records from product table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT product_no,product_name, FROM product ORDER BY product_name;
         */
        Cursor cursor = db.query(TABLE_RATING, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Rating rating = new Rating();
                rating.setRatingNo(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_RATING_NO))));
                //User.setUserNo(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NO)));
                rating.setRating(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_RATING))));
                rating.setRatedBy(cursor.getString(cursor.getColumnIndex(COLUMN_RATED_BY)));
                // Adding product record to list
                ratingList.add(rating);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return rating list
        return ratingList;
    }

    /**
     * This method to update product record
     *
     * @param rating
     */
    public void updateRating(Rating rating, User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NO, user.getUserNo());
        values.put(COLUMN_RATING, rating.getRating());
        values.put(COLUMN_RATED_BY, rating.getRatedBy());

        // updating row
        db.update(TABLE_RATING, values, COLUMN_RATING_NO + " = ?",
                new String[]{String.valueOf(rating.getRatingNo())});
        db.close();
    }

    public void deleteRating(Rating rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete product record by id
        db.delete(TABLE_RATING, COLUMN_RATING_NO + " = ?",
                new String[]{String.valueOf(rating.getRatingNo())});
        db.close();
    }

    public boolean checkRating(String rating) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_RATING_NO
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_PRODUCT_NAME + " = ?";

        // selection argument
        String[] selectionArgs = {rating};

        // query product table with condition
        Cursor cursor = db.query(TABLE_RATING, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }


}
