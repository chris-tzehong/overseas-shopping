package com.example.overseasshopping;

import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.overseasshopping.Model.Message;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageFragment extends Fragment {
    private RecyclerView mMessageRecyclerView;
    private MessageAdapter mAdapter;

    private Message mMessage;
    private String currentUserNo;
    private String otherUserNo;
    private EditText mInputMessage;
    private ImageButton mSendButton;

    private DatabaseHelper mDatabaseHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mMessage = new Message();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);

        mDatabaseHelper = new DatabaseHelper(getActivity());
        currentUserNo = (String) getActivity().getIntent().getStringExtra("user1");
        otherUserNo = (String) getActivity().getIntent().getStringExtra("user2");

        mMessageRecyclerView = (RecyclerView) view.findViewById(R.id.messages_recycler_view);
        mMessageRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mInputMessage = (EditText) view.findViewById(R.id.input_message);
        mInputMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mMessage.setMessageText(s.toString());


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mSendButton = (ImageButton) view.findViewById(R.id.send_message_btn);
        mSendButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String messageText = mInputMessage.getText().toString();

                if(!messageText.equals("")) {
                    mDatabaseHelper = new DatabaseHelper(getActivity());
                    Message message = new Message(messageText, otherUserNo, currentUserNo, DateUtils.getCurrentTime());
                    mDatabaseHelper.addMessage(message);
                    mInputMessage.setText("");
                }
            }
        });

        return view;
    }

    private class MessageHolder extends RecyclerView.ViewHolder {
        private TextView mUsername;
        private TextView mMessageTime;
        private TextView mMessageText;

        public MessageHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.fragment_message_box,parent, false));

            mUsername = (TextView) itemView.findViewById(R.id.user_name);
            mMessageTime = (TextView) itemView.findViewById(R.id.message_time);
            mMessageText = (TextView) itemView.findViewById(R.id.message_text);
        }
        public void bind(Message message){
            mMessage = message;
            mUsername.setText(mMessage.getReceiverId());
            mMessageTime.setText(mMessage.getMessage_time());
            mMessageText.setText(mMessage.getMessageText());
        }

    }

    private class MessageAdapter extends RecyclerView.Adapter<MessageHolder>{
        private List<Message> mMessages;

        public MessageAdapter(List<Message> messages){
            mMessages = messages;
        }
        @Override
        public MessageHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new MessageHolder(layoutInflater, parent);
        }
        @Override
        public void onBindViewHolder(MessageHolder holder, int position){
            Message message = mMessages.get(position);
            holder.bind(message);
        }
        @Override
        public int getItemCount(){

            return mMessages.size();
        }

        public void setMessages(List<Message> messages){
            mMessages = messages;
        }


    }

    private void updateUI() {
        MessageLab messageLab = MessageLab.get(getActivity());
        List<Message> messages = messageLab.getMessages();

        if (mAdapter ==null){
            mAdapter = new MessageAdapter(messages);
            mMessageRecyclerView.setAdapter(mAdapter);
        }else{
            mAdapter.setMessages(messages);
            mAdapter.notifyDataSetChanged();
        }

    }

}