package com.example.overseasshopping;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.overseasshopping.Model.Message;

import java.util.List;

public class MessageFragment extends Fragment {

    private static final String ARG_RECEIVERUSER_NO = "receiveruser_no";
    private static final String ARG_SENDERUSER_NO = "senderuser_no";

    private RecyclerView mMessageRecyclerView;
    private MessageAdapter mAdapter;

    private Message mMessage;
    private Integer senderUserNo;
    private Integer receiverUserNo;
    private EditText mInputMessage;
    private ImageButton mSendButton;

    private DatabaseHelper mDatabaseHelper;

    public static MessageFragment newInstance(Integer senderUserNo, Integer receiverUserNo) {
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SENDERUSER_NO, senderUserNo);
        bundle.putInt(ARG_RECEIVERUSER_NO, receiverUserNo);

        MessageFragment fragment = new MessageFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatabaseHelper = new DatabaseHelper(getActivity());
        senderUserNo = (Integer) getArguments().getInt(ARG_SENDERUSER_NO, 1);
        receiverUserNo = (Integer) getArguments().getInt(ARG_RECEIVERUSER_NO, 2);
        Log.d("user_sender", senderUserNo + "," + receiverUserNo);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);

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
                    Message message = new Message(messageText, receiverUserNo, senderUserNo, DateUtils.getCurrentTime());
                    mDatabaseHelper.addMessage(message);
                    mInputMessage.setText("");
                    updateUI();
                }
            }
        });

        updateUI();

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
            mUsername.setText(mDatabaseHelper.getUsername(receiverUserNo));
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

        List<Message> messages = mDatabaseHelper.getUserPrivateMessage(senderUserNo, receiverUserNo);

        if (mAdapter == null){
            mAdapter = new MessageAdapter(messages);
            mMessageRecyclerView.setAdapter(mAdapter);
        }else{
            mAdapter.setMessages(messages);
            mAdapter.notifyDataSetChanged();
        }

    }

}