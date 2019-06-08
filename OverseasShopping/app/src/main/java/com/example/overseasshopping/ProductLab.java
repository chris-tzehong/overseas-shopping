package com.example.overseasshopping;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.overseasshopping.Model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class ProductLab {
    private static ProductLab sProductLab;
   // private List<Product> mProducts;
    private Context mContext;
  //  private SQLiteDatabase mDatabase;
    private DatabaseHelper db;


    public static ProductLab get(Context context){
        if(sProductLab == null){
            sProductLab = new ProductLab(context);
        }
        return sProductLab;
    }
    private ProductLab(Context context){
        mContext = context.getApplicationContext();



    }
    public List<Product> getProducts(){

        List<Product> products = new ArrayList<>();
        db = new DatabaseHelper(mContext);
        products = db.getAllProduct();
        return products;
    }


}
