package com.example.overseasshopping.Model;

import java.util.Date;

public class Message {

    private String messageText;
    private String senderId;
    private String receiverId;
    private String message_time;

    public Message() {

    }

    public Message(String messageText, String receiverId, String senderId, String message_time) {
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

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getMessage_time() {
        return message_time;
    }

    public void setMessage_time(String message_time) {
        this.message_time = message_time;
    }
}
