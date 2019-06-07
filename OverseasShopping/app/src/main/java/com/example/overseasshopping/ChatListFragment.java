package com.example.overseasshopping;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.overseasshopping.Model.Chat;
import com.example.overseasshopping.Model.User;

import java.util.List;

public class ChatListFragment extends Fragment {
    private RecyclerView mChatListRecyclerView;
    private ChatAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_chat_list, container, false);


        mChatListRecyclerView = (RecyclerView) view.findViewById(R.id.chat_list);
        mChatListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }


    private class ChatHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private Chat mChat;
        private TextView receiver_name;
        private TextView chatDate;

        public ChatHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.fragment_chat_user, parent, false));
            itemView.setOnClickListener(this);

            receiver_name = itemView.findViewById(R.id.chat_name);
            chatDate = itemView.findViewById(R.id.chat_lastseen);
        }

        public void bind(Chat chat) {
            mChat = chat;
            receiver_name.setText(mChat.getReceiverId());
            chatDate.setText(mChat.getChat_date().toString());
        }

        @Override
        public void onClick(View view) {
            Bundle bundle = new Bundle();
            bundle.putString("otherUserNo", mChat.getReceiverId());

            //Intent i = MainActivity.newIntent(getActivity(),mChat.getReceiverId());

            Toast.makeText(getActivity(),
                    "ReceiverId = " + mChat.getReceiverId(), Toast.LENGTH_SHORT)
                    .show();
            //startActivity(i);
        }

    }


    private class ChatAdapter extends RecyclerView.Adapter<ChatHolder> {
        private List<Chat> mChats;

        public ChatAdapter(List<Chat> chats) {
            mChats = chats;
        }

        @Override
        public ChatHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new ChatHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(ChatHolder holder, int position) {
            Chat chat = mChats.get(position);
            holder.bind(chat);
        }

        @Override
        public int getItemCount() {
            return mChats.size();
        }

        public void setChats(List<Chat> chats) {
            mChats = chats;
        }
    }

    private void updateUI() {
        //ChatLab chatLab = ChatLab.get(getActivity());
        //List<Chat> chats = chatLab.getChats();

           // mAdapter = new ChatAdapter(chats);
            mChatListRecyclerView.setAdapter(mAdapter);

    }


}
