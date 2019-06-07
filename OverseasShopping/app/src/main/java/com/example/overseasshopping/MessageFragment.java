package com.example.overseasshopping;

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
import android.widget.Toast;

import com.example.overseasshopping.Model.Chat;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MessageFragment extends Fragment {

    public String currentUserNo;
    public String otherUserNo;

    private RecyclerView mMessageRecyclerView;
    private MessageAdapter mAdapter;
    private Chat mChat;
    private EditText mInputMessage;
    private ImageButton mSendButton;
    private DatabaseHelper db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_message, container, false);
        currentUserNo = getArguments().getString("userNo");
        otherUserNo = getArguments().getString("otherUserNo");

        mMessageRecyclerView = (RecyclerView) v.findViewById(R.id.messages_list_of_users);
        mMessageRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mInputMessage = (EditText) v.findViewById(R.id.input_message);
        mInputMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mChat.setChatMessage(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mSendButton = (ImageButton) v.findViewById(R.id.send_message_btn);
        mSendButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String messageText = mInputMessage.getText().toString();
                if(!messageText.equals("")) {
                    Chat chat = new Chat();
                    Date time = Calendar.getInstance().getTime();
                    chat.setChatMessage(messageText);
                    chat.setChat_date(time);
                    chat.setReceiverId(otherUserNo);
                    chat.setSenderId(currentUserNo);
                    db.addChat(chat);

                    mInputMessage.setText("");
                }
            }
        });

        //updateUI();

        return v;
    }

    private class MessageHolder extends RecyclerView.ViewHolder {

        private Chat mChat;
        private TextView user_name;
        private TextView chatDate;
        private TextView messageText;
        private ImageButton sendButton;

        public MessageHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.fragment_messages, parent, false));

            user_name = (TextView) itemView.findViewById(R.id.user_name);
            chatDate = (TextView) itemView.findViewById(R.id.message_date);
            messageText = (TextView) itemView.findViewById(R.id.message_text);
        }

        public void bind(Chat chat) {
            mChat = chat;
            user_name.setText(mChat.getReceiverId());
            chatDate.setText(mChat.getChat_date().toString());
            messageText.setText(mChat.getChat());
        }

    }

    private class MessageAdapter extends RecyclerView.Adapter<MessageHolder> {

        private List<Chat> mChats;

        public MessageAdapter(List<Chat> chats) {
            mChats = chats;
        }

        @Override
        public MessageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new MessageHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(MessageHolder holder, int position) {
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


}