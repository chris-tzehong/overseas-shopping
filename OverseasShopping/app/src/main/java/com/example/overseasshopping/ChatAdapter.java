package com.example.overseasshopping;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.overseasshopping.Model.Chat;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MessageViewHolder> {
    private List<Chat> mUserMessagesList;
    private DatabaseHelper db;

    public ChatAdapter(List<Chat> userMessagesList) {
        this.mUserMessagesList = userMessagesList;
    }


    public class MessageViewHolder extends RecyclerView.ViewHolder {
        public TextView senderMessageText, receiverMessageText;
        public ImageView receiverProfileImage;
        public ImageView messageSenderPicture, messageReceiverPicture;


        public MessageViewHolder(View view) {
            super(view);

            senderMessageText = (TextView) view.findViewById(R.id.sender_messsage_text);
            receiverMessageText = (TextView) view.findViewById(R.id.receiver_message_text);
            receiverProfileImage = (ImageView) view.findViewById(R.id.message_profile_image);
            messageReceiverPicture = view.findViewById(R.id.message_receiver_image_view);
            messageSenderPicture = view.findViewById(R.id.message_sender_image_view);
        }
    }


    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.messages_layout, viewGroup, false);

        return new MessageViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final MessageViewHolder messageViewHolder, int i) {
        Integer messageSenderId = db.getUser("1").getUserNo();

        Chat chat = mUserMessagesList.get(i);

        Integer fromUserID = chat.getReceiverId();


        messageViewHolder.receiverMessageText.setVisibility(View.GONE);
        messageViewHolder.receiverProfileImage.setVisibility(View.GONE);
        messageViewHolder.senderMessageText.setVisibility(View.GONE);
        messageViewHolder.messageSenderPicture.setVisibility(View.GONE);
        messageViewHolder.messageReceiverPicture.setVisibility(View.GONE);


        if (fromUserID.equals(messageSenderId)) {
            messageViewHolder.senderMessageText.setVisibility(View.VISIBLE);

            messageViewHolder.senderMessageText.setBackgroundResource(R.drawable.sender_messages_layout);
            messageViewHolder.senderMessageText.setTextColor(Color.BLACK);
            messageViewHolder.senderMessageText.setText(chat.getChat() + "\n \n" + chat.getChat_time());
        } else {
            messageViewHolder.receiverProfileImage.setVisibility(View.VISIBLE);
            messageViewHolder.receiverMessageText.setVisibility(View.VISIBLE);

            messageViewHolder.receiverMessageText.setBackgroundResource(R.drawable.receiver_messages_layout);
            messageViewHolder.receiverMessageText.setTextColor(Color.BLACK);
            messageViewHolder.receiverMessageText.setText(chat.getChat() + "\n \n" + chat.getChat_time());
        }
    }




    @Override
    public int getItemCount()
    {
        return mUserMessagesList.size();
    }

}