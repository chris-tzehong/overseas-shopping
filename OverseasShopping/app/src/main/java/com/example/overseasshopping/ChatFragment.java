package com.example.overseasshopping;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.overseasshopping.Model.Chat;

public class ChatFragment extends Fragment {
    private Chat mChat;
    private String mReceiverId;
    private String mSenderId;
    public String currentUserNo;
    public String otherUserNo;
    private DatabaseHelper db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mChat = new Chat();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chat_user, container, false);
        currentUserNo = getArguments().getString("userNo");


        db = new DatabaseHelper(getContext());


        return v;
    }

}