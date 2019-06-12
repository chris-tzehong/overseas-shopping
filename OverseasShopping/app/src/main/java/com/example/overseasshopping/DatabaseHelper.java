package com.example.overseasshopping;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.overseasshopping.Model.Message;
import com.example.overseasshopping.Model.Order;
import com.example.overseasshopping.Model.Product;
import com.example.overseasshopping.Model.Rating;
import com.example.overseasshopping.Model.User;

import java.util.Date;
import java.util.Calendar;
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
    private static final String TABLE_RATING = "rating";

    // User Table Columns names
    private static final String COLUMN_USER_NO = "user_no";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_TELEPHONE = "telephone";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_CREDITCARD_NO = "creditcard_no";
    private static final String COLUMN_SECURITY_NO = "security_no";
    private static final String COLUMN_EXPIRY_DATE = "expiry_date";
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
    private static final String COLUMN_SENDERID = "senderid";
    private static final String COLUMN_RECEIVERID = "receiverid";
    private static final String COLUMN_MESSAGE_TIME = "message_time";
    private static final String COLUMN_MESSAGETEXT = "MESSAGE";

    private static final String COLUMN_RATING_NO = "rating_no";
    private static final String COLUMN_RATED_BY = "rated_by";


    // create table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_NO + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USERNAME + " TEXT," + COLUMN_PASSWORD + " TEXT,"
            + COLUMN_TELEPHONE + " TEXT," + COLUMN_ADDRESS + " TEXT,"
            + COLUMN_CREDITCARD_NO + " INTEGER," + COLUMN_SECURITY_NO + " INTEGER,"
            + COLUMN_EXPIRY_DATE + " TEXT," + COLUMN_RATING + " INTEGER, "
            + COLUMN_TOTAL_RATED_BY + " INTEGER" + ")";

    private String CREATE_PRODUCT_TABLE = "CREATE TABLE " + TABLE_PRODUCT + "("
            + COLUMN_PRODUCT_NO + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_PRODUCT_NAME + " TEXT," + COLUMN_PHOTO + " TEXT,"
            + COLUMN_DESCRIPTION + " TEXT," + COLUMN_PRICE + " INTEGER,"
            + COLUMN_USER_NO + " INTEGER," + COLUMN_PRODUCT_QUANTITY + " INTEGER" + ")";

    private String CREATE_ORDERS_TABLE = "CREATE TABLE " + TABLE_ORDERS + "("
            + COLUMN_ORDER_NO + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_SELLER + " TEXT," + COLUMN_BUYER + " TEXT,"
            + COLUMN_TIME + " DATETIME," + COLUMN_PRODUCT_NO + " INTEGER" + ")";

    private String CREATE_MESSAGE_TABLE = "CREATE TABLE " + TABLE_MESSAGE + "("
            + COLUMN_MESSAGE_NO + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_SENDERID + " INTEGER," + COLUMN_RECEIVERID + " INTEGER,"
            + COLUMN_MESSAGE_TIME + " DATETIME," + COLUMN_MESSAGETEXT + " TEXT" + ")";

    private String CREATE_RATING_TABLE = "CREATE TABLE " + TABLE_RATING + "("
            + COLUMN_RATING_NO + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USER_NO + " INTEGER, " + COLUMN_RATING + " INTEGER,"
            + COLUMN_RATED_BY + " TEXT" + ")";

    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;
    private String DROP_PRODUCT_TABLE = "DROP TABLE IF EXISTS " + TABLE_PRODUCT;
    private String DROP_ORDERS_TABLE = "DROP TABLE IF EXISTS " + TABLE_ORDERS;
    private String DROP_MESSAGE_TABLE = "DROP TABLE IF EXISTS " + TABLE_MESSAGE;
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
        db.execSQL(CREATE_RATING_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop table if existed, all data will be gone!!!
        db.execSQL(DROP_USER_TABLE);
        db.execSQL(DROP_PRODUCT_TABLE);
        db.execSQL(DROP_ORDERS_TABLE);
        db.execSQL(DROP_MESSAGE_TABLE);
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
        values.put(COLUMN_CREDITCARD_NO, user.getCreditCardNo());
        values.put(COLUMN_SECURITY_NO, user.getSecurityNo());
        values.put(COLUMN_EXPIRY_DATE, user.getExpiryDate());
        values.put(COLUMN_RATING, user.getRating());
        values.put(COLUMN_TOTAL_RATED_BY, user.getTotalRatedBy());

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public User getUser(String username) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_NO,
                COLUMN_USERNAME,
                COLUMN_PASSWORD,
                COLUMN_TELEPHONE,
                COLUMN_ADDRESS,
                COLUMN_CREDITCARD_NO,
                COLUMN_SECURITY_NO,
                COLUMN_EXPIRY_DATE,
                COLUMN_RATING,
                COLUMN_TOTAL_RATED_BY
        };

        String sortOrder = COLUMN_USER_NO + " ASC";

        String selection = COLUMN_USERNAME + " =?";

        String[] selectionArgs = {username};

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_no,user_name,user_email,password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_USER,
                columns,    //columns to return
                selection,        //columns for the WHERE clause
                selectionArgs,        //The values for the WHERE clause
                null,       //group the rows
                null,
                sortOrder);       //filter by row groups

            if (cursor != null) {
                cursor.moveToFirst();
            }
            User u1 = new User();
            u1.setUserNo(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NO))));
            u1.setUsername(cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)));
            u1.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)));
            u1.setTelephone(cursor.getString(cursor.getColumnIndex(COLUMN_TELEPHONE)));
            u1.setAddress(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)));
            u1.setCreditCardNo(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_CREDITCARD_NO))));
            u1.setSecurityNo(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_SECURITY_NO))));
            u1.setExpiryDate(cursor.getString(cursor.getColumnIndex(COLUMN_EXPIRY_DATE)));
            u1.setRating(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_RATING))));
            u1.setTotalRatedBy(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_TOTAL_RATED_BY))));

        db.close();
        cursor.close();
        return u1;

        }

    public String getUsername(Integer userno) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USERNAME,
        };

        String sortOrder = COLUMN_USER_NO + " ASC";

        String selection = COLUMN_USER_NO + " = ?";

        String[] selectionArgs = {String.valueOf(userno)};

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_no,user_name,user_email,password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_USER,
                columns,    //columns to return
                selection,        //columns for the WHERE clause
                selectionArgs,        //The values for the WHERE clause
                null,       //group the rows
                null,
                sortOrder);       //filter by row groups

        if (cursor != null) {
            cursor.moveToFirst();
        }
        String u1 = cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME));

        db.close();
        cursor.close();

        return u1;

    }

    public List<User> getAllUser() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_NO,
                COLUMN_USERNAME,
                COLUMN_PASSWORD,
                COLUMN_TELEPHONE,
                COLUMN_ADDRESS,
                COLUMN_CREDITCARD_NO,
                COLUMN_SECURITY_NO,
                COLUMN_EXPIRY_DATE,
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
                user.setCreditCardNo(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_CREDITCARD_NO))));
                user.setSecurityNo(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_SECURITY_NO))));
                user.setExpiryDate(cursor.getString(cursor.getColumnIndex(COLUMN_EXPIRY_DATE)));
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
        values.put(COLUMN_CREDITCARD_NO, user.getCreditCardNo());
        values.put(COLUMN_SECURITY_NO, user.getSecurityNo());
        values.put(COLUMN_EXPIRY_DATE, String.valueOf(user.getExpiryDate()));
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

    public void addProduct(Product product, Integer userno) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_NAME, product.getProductName());
        values.put(COLUMN_PHOTO, product.getPhoto());
        values.put(COLUMN_DESCRIPTION, product.getDescription());
        values.put(COLUMN_PRICE, product.getPrice());
        values.put(COLUMN_USER_NO, userno);
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
                order.setTime(new Date(cursor.getString(cursor.getColumnIndex(COLUMN_TIME))));
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

    public void addMessage(Message message) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_MESSAGETEXT, message.getMessageText());
        values.put(COLUMN_SENDERID, message.getSenderId());
        values.put(COLUMN_RECEIVERID, message.getReceiverId());
        values.put(COLUMN_MESSAGE_TIME, String.valueOf(message.getMessage_time()));

        db.insert(TABLE_MESSAGE, null, values);
        db.close();
    }

    public List<Message> getUserMessages(Integer userNo) {

        List<Message> messages = new ArrayList<>();

        String[] columns = {
                COLUMN_SENDERID,
                COLUMN_RECEIVERID,
                COLUMN_MESSAGE_TIME
        };

        String orderBy = COLUMN_MESSAGE_TIME + " DESC";

        String groupBy = COLUMN_SENDERID + ", " + COLUMN_RECEIVERID;

        String selection = COLUMN_RECEIVERID + " = ?";

        String[] selectionArgs ={String.valueOf(userNo)};

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_MESSAGE,
                columns,
                selection,
                selectionArgs,
                groupBy,
                null,
                orderBy);

        if(cursor.moveToFirst()) {
            do {
                    Message cM = new Message();
                    cM.setSenderId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_SENDERID))));
                    cM.setReceiverId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_RECEIVERID))));
                    cM.setMessage_time(cursor.getString(cursor.getColumnIndex(COLUMN_MESSAGE_TIME)));
                    // Adding message record to list
                    messages.add(cM);
            } while(cursor.moveToNext());
        }
        db.close();
        cursor.close();

        return messages;
    }

    public List<Message> getUserPrivateMessage(Integer currentUserNo, Integer otherUserNo) {

        List<Message> messages = new ArrayList<>();

        String[] columns = {
                COLUMN_MESSAGETEXT,
                COLUMN_SENDERID,
                COLUMN_RECEIVERID,
                COLUMN_MESSAGE_TIME
        };

        String sortOrder = COLUMN_MESSAGE_TIME + " ASC";

        String selection = "( " + COLUMN_SENDERID + " = ?" + " AND " + COLUMN_RECEIVERID + " = ?" + " ) OR " +
                           "( " + COLUMN_SENDERID + " = ?" + " AND " + COLUMN_RECEIVERID + " = ?" + " )";

        String[] selectionArgs ={String.valueOf(currentUserNo), String.valueOf(otherUserNo),
                                 String.valueOf(otherUserNo), String.valueOf(currentUserNo)};

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_MESSAGE, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                sortOrder);

        if(cursor.moveToFirst()) {
            do {
                Message cM = new Message();
                cM.setMessageText(cursor.getString(cursor.getColumnIndex(COLUMN_MESSAGETEXT)));
                cM.setSenderId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_SENDERID))));
                cM.setReceiverId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_RECEIVERID))));
                cM.setMessage_time(cursor.getString(cursor.getColumnIndex(COLUMN_MESSAGE_TIME)));
                // Adding order record to list
                messages.add(cM);
            } while (cursor.moveToNext());

        }

        db.close();
        cursor.close();
        return messages;
    }

    public List<Message> getAllMessages() {

        List<Message> messages = new ArrayList<>();

        String[] columns = {
                COLUMN_MESSAGETEXT,
                COLUMN_SENDERID,
                COLUMN_RECEIVERID,
                COLUMN_MESSAGE_TIME
        };

        String sortOrder = COLUMN_MESSAGE_TIME + " ASC";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_MESSAGE, //Table to query
                columns,                    //columns to return
                null,                  //columns for the WHERE clause
                null,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                sortOrder);

        if(cursor.moveToFirst()) {
            do {
                Message cM = new Message();
                cM.setMessageText(cursor.getString(cursor.getColumnIndex(COLUMN_MESSAGETEXT)));
                cM.setSenderId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_SENDERID))));
                cM.setReceiverId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_RECEIVERID))));
                cM.setMessage_time(cursor.getString(cursor.getColumnIndex(COLUMN_MESSAGE_TIME)));
                //Product.setProductNo(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_NO)));
                // Adding order record to list
                messages.add(cM);
            } while (cursor.moveToNext());
        }

        db.close();
        cursor.close();

        return messages;
    }

    public void updateMessage(Message message) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_SENDERID, message.getSenderId());
        values.put(COLUMN_RECEIVERID, message.getReceiverId());
        values.put(COLUMN_MESSAGETEXT, message.getMessageText());
        values.put(COLUMN_MESSAGE_TIME, message.getMessage_time());

        // updating row
        db.update(TABLE_MESSAGE, values, COLUMN_MESSAGE_NO + " = ?",
                new String[]{String.valueOf(message.getMessageNo())});
        db.close();
    }

    public void deleteMessage(Message message) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete product record by id
        db.delete(TABLE_MESSAGE, COLUMN_MESSAGE_NO + " = ?",
                new String[]{String.valueOf(message.getMessageNo())});
        db.close();
    }



//    public void getOtherUserNo(String userNo) {
//        String[] columns = {
//                COLUMN_SENDERID,
//                COLUMN_RECEIVERID
//        };
//
//        String sortOrder = COLUMN_USER_NO + " ASC";
//
//        String selection = COLUMN_SENDERID + " = ?" + " OR " + COLUMN_RECEIVERID + " = ?";
//
//        String[] selectionArgs ={userNo, userNo};
//
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = db.query(TABLE_ORDERS, //Table to query
//                columns,                    //columns to return
//                selection,                  //columns for the WHERE clause
//                selectionArgs,              //The values for the WHERE clause
//                null,                       //group the rows
//                null,                      //filter by row groups
//                sortOrder);
//
//        if(cursor.moveToFirst())
//            do {
//                Message cM = new Message();
//                cM.setMessageText(cursor.getString(cursor.getColumnIndex(COLUMN_MESSAGETEXT)));
//                cM.setSenderId(cursor.getString(cursor.getColumnIndex(COLUMN_SENDERID)));
//                cM.setReceiverId(cursor.getString(cursor.getColumnIndex(COLUMN_RECEIVERID)));
//                cM.setMessage_time(new Date(cursor.getString(cursor.getColumnIndex(COLUMN_MESSAGE_TIME))));
//                // Adding order record to list
//                messages.add(cM);
//            }while(cursor.moveToNext());
//
//
//        return messages;
//    }

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

    public boolean checkRating(String userNo) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_RATING_NO
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_USER_NO + " = ?";

        // selection argument
        String[] selectionArgs = {userNo};

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
