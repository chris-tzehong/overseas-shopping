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
    private MessageAdapter mAdapter;
    private DatabaseHelper mDatabaseHelper;
    private String mUsername;
    private String mUsername2;
    private List<Message> userMessages;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_messages_list, container, false);

        mMessageListRecyclerView = (RecyclerView) view.findViewById(R.id.messages_recycler_view);
        mMessageListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mDatabaseHelper = new DatabaseHelper(getActivity());
        mUsername = (String) getActivity().getIntent().getStringExtra(MainActivity.EXTRA_USERNAME);
//        updateUI();

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
            receiverName.setText(mMessage.getReceiverId());
            messageTime.setText(mMessage.getMessage_time().toString());
        }

    }

    private class MessageAdapter extends RecyclerView.Adapter<MessageHolder> {
        private List<Message> mMessages;

        public MessageAdapter(List<Message> messages) {
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

//    private void updateUI() {
//
//        //MessageLab messageLab = MessageLab.get(getActivity());
//        mDatabaseHelper = new DatabaseHelper(getActivity());
//        //List<Message> messages = messageLab.getMessages();
//        //userMessages = mDatabaseHelper.getUserMessages(mUsername,mUsername2);
//        if (mAdapter == null){
//            mAdapter = new MessageAdapter(userMessages);
//            mMessageListRecyclerView.setAdapter(mAdapter);
//        }else{
//            mAdapter.setMessages(userMessages);
//            mAdapter.notifyDataSetChanged();
//        }
//
//    }


}
