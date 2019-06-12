package com.example.overseasshopping.Model;

public class Message {

    private Integer MessageNo;
    private String messageText;
    private Integer senderId;
    private Integer receiverId;
    private String message_time;

    public Message() {

    }


    public Message(String messageText, Integer receiverId, Integer senderId, String message_time) {
        this.messageText = messageText;
        this.receiverId = receiverId;
        this.senderId = senderId;
        this.message_time = message_time;
    }

    public Integer getMessageNo() {
        return MessageNo;
    }

    public void setMessageNo(Integer messageNo) {
        MessageNo = messageNo;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public String getMessage_time() {
        return message_time;
    }

    public void setMessage_time(String message_time) {
        this.message_time = message_time;
    }
}
