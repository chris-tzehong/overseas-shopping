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

public class ChatListFragment extends Fragment {
    private RecyclerView mChatMessageList;
    private View ChatMessagesView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ChatMessagesView = inflater.inflate(R.layout.activity_message, container, false);

        mChatMessageList = (RecyclerView) ChatMessagesView.findViewById(R.id.message_list);
        mChatMessageList.setLayoutManager(new LinearLayoutManager(getContext()));

        return ChatMessagesView;
    }



    private class ChatMessageViewHolder extends RecyclerView.ViewHolder {
        ImageView profileImage;
        TextView receiver_name;
        TextView chatMessageDate;

        public ChatMessageViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.activity_message, parent, false));

            profileImage = itemView.findViewById(R.id.users_profile_image);
            receiver_name = itemView.findViewById(R.id.receiver_name);
            chatMessageDate = itemView.findViewById(R.id.chatMessageTime);
        }


    }
}
