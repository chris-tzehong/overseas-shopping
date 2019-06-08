package com.example.overseasshopping;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.overseasshopping.Model.Chat;

import java.util.List;

public class ChatListFragment extends Fragment {
    private RecyclerView mChatRecyclerView;
    private View chatView;
    private ChatAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        chatView = inflater.inflate(R.layout.activity_message, container, false);

        mChatRecyclerView = (RecyclerView) chatView.findViewById(R.id.message_list);
        mChatRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

       // updateUI();

        return chatView;
    }



    private class ChatHolder extends RecyclerView.ViewHolder {
        ImageView profileImage;
        TextView receiver_name;
        TextView chatMessageDate;

        public ChatHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.activity_message, parent, false));

            profileImage = itemView.findViewById(R.id.users_profile_image);
            receiver_name = itemView.findViewById(R.id.receiver_name);
            chatMessageDate = itemView.findViewById(R.id.chatMessageTime);
        }


    }

    private class ChatAdapter extends RecyclerView.Adapter<ChatHolder> {

        private List<Chat> mChats;

        public ChatAdapter(List<Chat> chats) {
            mChats = chats;
        }

        @Override
        public ChatHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new ChatHolder(layoutInflater, viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull ChatHolder chatHolder, int i) {

        }

        @Override
        public int getItemCount() {
            return mChats.size();
        }
    }

//    //private void updateUI() {
//        mAdapter = new ChatAdapter();
//        mChatRecyclerView.setAdapter(mAdapter);
//
//    }
}
