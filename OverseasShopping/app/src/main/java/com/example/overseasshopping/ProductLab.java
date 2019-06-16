package com.example.overseasshopping;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.overseasshopping.Model.Product;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.io.File;
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

    public File getPhotoFile(Product product) {
        File filesDir = mContext.getFilesDir();
        return new File(filesDir, product.getPhotoFileName());
    }

    public void setupImageLoader(){
        // UNIVERSAL IMAGE LOADER SETUP
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheOnDisc(true).cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(300)).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                mContext)
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new WeakMemoryCache())
                .discCacheSize(100 * 1024 * 1024).build();

        ImageLoader.getInstance().init(config);
        // END - UNIVERSAL IMAGE LOADER SETUP
    }

}
