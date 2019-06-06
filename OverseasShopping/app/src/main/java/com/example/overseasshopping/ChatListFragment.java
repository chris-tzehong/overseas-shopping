package com.example.overseasshopping;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.overseasshopping.Model.Chat;

import java.util.List;

public class ChatListFragment extends Fragment {
    private RecyclerView mChatListRecyclerView;
    private ChatAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_user, container, false);

        mChatListRecyclerView = (RecyclerView) view.findViewById(R.id.chat_list);
        mChatListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }



    private class ChatHolder extends RecyclerView.ViewHolder {
        private Chat mChat;
        private TextView receiver_name;
        private TextView chatDate;

        public ChatHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.fragment_chat_user, parent, false));

            receiver_name = itemView.findViewById(R.id.custom_profile_name);
            chatDate = itemView.findViewById(R.id.custom_user_last_seen);
        }

        public void bind(Chat chat) {
            mChat = chat;
            receiver_name.setText(mChat.getReceiverId());
            chatDate.setText(mChat.getChat_date().toString());
        }

    }

    private class ChatAdapter extends RecyclerView.Adapter<ChatHolder> {
        private List<Chat> mChats;

        public ChatAdapter(List<Chat> chats) {
            mChats = chats;
        }

        @Override
        public ChatHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new ChatHolder(layoutInflater, viewGroup);
        }

        @Override
        public void onBindViewHolder(ChatHolder chatHolder, int i) {
            Chat chat = mChats.get(i);
            chatHolder.bind(chat);
        }

        @Override
        public int getItemCount() {
            return mChats.size();
        }

        public void setChats(List<Chat> chats){
            mChats = chats;
        }
    }

    private void updateUI() {
        ChatLab chatLab = ChatLab.get(getActivity());
        List<Chat> chats = chatLab.getChats();
        if (mAdapter ==null){
            mAdapter = new ChatAdapter(chats);
            mChatListRecyclerView.setAdapter(mAdapter);
        }else{
            mAdapter.setChats(chats);
            mAdapter.notifyDataSetChanged();
        }

    }


}
