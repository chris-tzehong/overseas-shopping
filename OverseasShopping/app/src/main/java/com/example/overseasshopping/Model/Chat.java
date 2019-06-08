package com.example.overseasshopping.Model;

import java.util.Date;
import java.util.List;

public class Chat {

    private String chat;
    private String senderId;
    private String receiverId;
    private Date chat_date;

    public Chat() {

    }

    public Chat(String chat, String receiverId, String senderId, Date chat_date) {
        this.chat = chat;
        this.receiverId = receiverId;
        this.senderId = senderId;
        this.chat_date = chat_date;
    }

    public String getChat() {
        return chat;
    }

    public void setChatMessage(String chatMessage) {
        this.chat = chat;
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

    public Date getChat_date() {
        return chat_date;
    }

    public void setChat_date(Date chat_date) {
        this.chat_date = chat_date;
    }
}
