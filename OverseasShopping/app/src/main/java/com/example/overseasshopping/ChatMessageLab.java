package com.example.overseasshopping;

import android.content.Context;

import com.example.overseasshopping.Model.Chat;

import java.util.ArrayList;
import java.util.List;

public class ChatMessageLab {
    private static ChatMessageLab sChatMessageLab;

    private List<Chat> mChats;

    public static ChatMessageLab get(Context context) {

        return null;
    }

    private ChatMessageLab(Context context) {
        mChats = new ArrayList<>();
    }

    public List<Chat> getChats() {
        return mChats;
    }

}
