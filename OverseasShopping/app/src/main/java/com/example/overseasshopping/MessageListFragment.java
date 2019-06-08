package com.example.overseasshopping;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.overseasshopping.Model.Message;

import java.util.List;

public class MessageListFragment extends Fragment {
    private RecyclerView mMessageListRecyclerView;
    private ChatAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message_list_display, container, false);

        mMessageListRecyclerView = (RecyclerView) view.findViewById(R.id.messages_list);
        mMessageListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }



    private class ChatHolder extends RecyclerView.ViewHolder {
        private Message mMessage;
        private TextView receiver_name;
        private TextView chatDate;

        public ChatHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.fragment_message_list_display, parent, false));

            receiver_name = itemView.findViewById(R.id.custom_profile_name);
            chatDate = itemView.findViewById(R.id.custom_user_last_seen);
        }

        public void bind(Message message) {
            mMessage = message;
            receiver_name.setText(mMessage.getReceiverId());
            chatDate.setText(mMessage.getMessage_time().toString());
        }

    }

    private class ChatAdapter extends RecyclerView.Adapter<ChatHolder> {
        private List<Message> mMessages;

        public ChatAdapter(List<Message> messages) {
            mMessages = messages;
        }

        @Override
        public ChatHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new ChatHolder(layoutInflater, viewGroup);
        }

        @Override
        public void onBindViewHolder(ChatHolder chatHolder, int i) {
            Message message = mMessages.get(i);
            chatHolder.bind(message);
        }

        @Override
        public int getItemCount() {
            return mMessages.size();
        }

        public void setMessages(List<Message> messages){
            mMessages = messages;
        }
    }

    private void updateUI() {
        MessageLab messageLab = MessageLab.get(getActivity());
        List<Message> messages = messageLab.getChats();
        if (mAdapter ==null){
            mAdapter = new ChatAdapter(messages);
            mMessageListRecyclerView.setAdapter(mAdapter);
        }else{
            mAdapter.setMessages(messages);
            mAdapter.notifyDataSetChanged();
        }

    }


}
