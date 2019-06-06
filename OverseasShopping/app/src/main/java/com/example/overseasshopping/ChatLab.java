package com.example.overseasshopping;

import android.content.Context;

import com.example.overseasshopping.Model.Chat;

import java.util.ArrayList;
import java.util.List;

public class ChatLab {
    private static ChatLab sChatLab;
    // private List<Product> mProducts;
    private Context mContext;
    //  private SQLiteDatabase mDatabase;
    private DatabaseHelper db;


    public static ChatLab get(Context context){
        if(sChatLab == null){
            sChatLab = new ChatLab(context);

        }

        return sChatLab;
    }

    private ChatLab(Context context){

        mContext = context.getApplicationContext();
    }

    public List<Chat> getChats(){

        List<Chat> chats = new ArrayList<>();

        db = new DatabaseHelper(mContext);
        chats = db.getAllChats();

        return chats;
    }
}
