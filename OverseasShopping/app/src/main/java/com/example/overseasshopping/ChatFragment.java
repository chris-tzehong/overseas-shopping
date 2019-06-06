package com.example.overseasshopping;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.overseasshopping.Model.Message;
import com.example.overseasshopping.Model.User;

public class ChatFragment extends Fragment {
    private View ChatsView;
    private RecyclerView chatsList;
    private DatabaseHelper db;
    private Integer currentUserID;

    public ChatFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ChatsView = inflater.inflate(R.layout.fragment_chat, container, false);

        db = new DatabaseHelper(getActivity());
        User user = new User("1", "1", "1", "1", "1", "1", null, 0, 0);
        Message cM = new Message("GGWP", 1, 2, null);
        String currentUser = user.getUsername();
        currentUserID = db.getUser(currentUser).getUserNo();

        chatsList = (RecyclerView) ChatsView.findViewById(R.id.chats_list);
        chatsList.setLayoutManager(new LinearLayoutManager(getContext()));

        return ChatsView;
    }

}
