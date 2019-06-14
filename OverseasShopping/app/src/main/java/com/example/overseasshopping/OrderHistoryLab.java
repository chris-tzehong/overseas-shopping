package com.example.overseasshopping;

import android.content.Context;

import com.example.overseasshopping.Model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderHistoryLab {
    private static OrderHistoryLab sOrderHistoryLab;
    private Context mContext;
    private DatabaseHelper db;


    public static OrderHistoryLab get(Context context){
        if(sOrderHistoryLab == null){
            sOrderHistoryLab = new OrderHistoryLab(context);
        }
        return sOrderHistoryLab;
    }

    private OrderHistoryLab(Context context){
        mContext = context.getApplicationContext();

    }

    public List<Order> getOrders(String username) {

        List<Order> orders = new ArrayList<>();
        db = new DatabaseHelper(mContext);
        orders = db.getAllOrder();
        return orders;
    }



}
