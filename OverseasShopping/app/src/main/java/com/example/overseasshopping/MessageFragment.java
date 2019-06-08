package com.example.overseasshopping;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.overseasshopping.Model.Message;

public class MessageFragment extends Fragment {
    private Message mMessage;
    private String mReceiverId;
    private String mSenderId;
    public String currentUserNo;
    public String otherUserNo;
    private DatabaseHelper db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mMessage = new Message();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_message_list_display, container, false);
        currentUserNo = getArguments().getString("userNo");


        db = new DatabaseHelper(getContext());


        return v;
    }

}