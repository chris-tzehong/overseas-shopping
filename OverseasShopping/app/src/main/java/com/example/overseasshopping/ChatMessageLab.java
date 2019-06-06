package com.example.overseasshopping;

import android.content.Context;

import com.example.overseasshopping.Model.Message;

import java.util.ArrayList;
import java.util.List;

public class ChatMessageLab {
    private static ChatMessageLab sChatMessageLab;

    private List<Message> mMessages;

    public static ChatMessageLab get(Context context) {

        return null;
    }

    private ChatMessageLab(Context context) {
        mMessages = new ArrayList<>();
    }

    public List<Message> getMessages() {
        return mMessages;
    }

}
