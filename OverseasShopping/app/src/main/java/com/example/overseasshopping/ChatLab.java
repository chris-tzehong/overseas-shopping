package com.example.overseasshopping;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.overseasshopping.Model.Chat;

import java.util.List;


public class ChatLab {
    private static ChatLab sChatLab;
    private Context mContext;
    private SQLiteDatabase db;


    public static ChatLab get(Context context) {
        if (sChatLab == null) {
            sChatLab = new ChatLab(context);

        }

        return sChatLab;
    }

    private ChatLab(Context context) {

        mContext = context.getApplicationContext();
        db = new DatabaseHelper(mContext).getWritableDatabase();

    }

        public Chat getChat (String receiverId){
            return null;
        }

}
