package com.example.overseasshopping.Model;

import java.util.Date;

public class Message {

    private String messageText;
    private int senderId;
    private int receiverId;
    private String message_time;

    public Message() {

    }

    public Message(String messageText, int receiverId, int senderId, String message_time) {
        this.messageText = messageText;
        this.receiverId = receiverId;
        this.senderId = senderId;
        this.message_time = message_time;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public String getMessage_time() {
        return message_time;
    }

    public void setMessage_time(String message_time) {
        this.message_time = message_time;
    }
}
