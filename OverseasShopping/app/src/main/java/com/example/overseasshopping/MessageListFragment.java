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
import com.example.overseasshopping.Model.User;

import java.util.List;

public class MessageListFragment extends Fragment {
    private RecyclerView mMessageListRecyclerView;
    private MessageListAdapter mAdapter;
    private DatabaseHelper mDatabaseHelper;
    private String mUsername;
    private int mUserno;
    private List<Message> userMessageList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_messages_list, container, false);

        mMessageListRecyclerView = (RecyclerView) view.findViewById(R.id.messages_list_recycler_view);
        mMessageListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mDatabaseHelper = new DatabaseHelper(getActivity());
        mUsername = (String) getActivity().getIntent().getStringExtra(MainActivity.EXTRA_USERNAME);
        mUserno = (int) getActivity().getIntent().getIntExtra(MainActivity.EXTRA_USERNO);
        updateUI();

        return view;
    }

    private class MessageHolder extends RecyclerView.ViewHolder {
        private Message mMessage;
        private TextView receiverName;
        private TextView messageTime;

        public MessageHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.fragment_message_list_display, parent, false));

            //itemView.setOnClickListener(this);
            receiverName = itemView.findViewById(R.id.receiver_name);
            messageTime = itemView.findViewById(R.id.receiver_last_seen);
        }

        public void bind(Message message) {
            mMessage = message;
            if(mUsername.equals(mMessage.getReceiverId())) {
                receiverName.setText(mMessage.getSenderId());
            }
            else if(mUsername.equals(mMessage.getSenderId())){
                receiverName.setText(mMessage.getReceiverId());
            }
            messageTime.setText(mMessage.getMessage_time().toString());
        }

//        public void onClick(View v) {
//
//
//        }

    }

    private class MessageListAdapter extends RecyclerView.Adapter<MessageHolder> {
        private List<Message> mMessages;

        public MessageListAdapter(List<Message> messages) {
            mMessages = messages;
        }

        @Override
        public MessageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new MessageHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(MessageHolder holder, int position) {
            Message message = mMessages.get(position);
            holder.bind(message);
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

        //MessageLab messageLab = MessageLab.get(getActivity());
        mDatabaseHelper = new DatabaseHelper(getActivity());
        //List<Message> messages = messageLab.getMessages();
        userMessageList = mDatabaseHelper.getUserMessages(mUsername);
        if (mAdapter == null){
            mAdapter = new MessageListAdapter(userMessageList);
            mMessageListRecyclerView.setAdapter(mAdapter);
        }else{
            mAdapter.setMessages(userMessageList);
            mAdapter.notifyDataSetChanged();
        }

    }

}
