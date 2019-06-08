package com.example.overseasshopping;

import android.content.Context;

import com.example.overseasshopping.Model.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageLab {
    private static MessageLab sMessageLab;
    // private List<Product> mProducts;
    private Context mContext;
    //  private SQLiteDatabase mDatabase;
    private DatabaseHelper db;


    public static MessageLab get(Context context){
        if(sMessageLab == null){
            sMessageLab = new MessageLab(context);

        }

        return sMessageLab;
    }

    private MessageLab(Context context){

        mContext = context.getApplicationContext();
    }

    public List<Message> getChats(){

        List<Message> messages = new ArrayList<>();

        db = new DatabaseHelper(mContext);

        return messages;
    }
}
