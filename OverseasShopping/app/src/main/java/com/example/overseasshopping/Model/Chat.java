package com.example.overseasshopping.Model;

import java.util.Date;

public class Chat {

    private String chat;
    private int senderId;
    private int receiverId;
    private Date chat_time;

    public Chat() {

    }

    public Chat(String chat, int receiverId, int senderId, Date chat_time) {
        this.chat = chat;
        this.receiverId = receiverId;
        this.senderId = senderId;
        this.chat_time = chat_time;
    }

    public String getChat() {
        return chat;
    }

    public void setChatMessage(String chatMessage) {
        this.chat = chat;
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

    public Date getChat_time() {
        return chat_time;
    }

    public void setChat_time(Date chat_time) {
        this.chat_time = chat_time;
    }
}
