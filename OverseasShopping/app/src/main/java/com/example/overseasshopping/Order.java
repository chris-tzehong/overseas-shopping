package com.example.overseasshopping;

import java.sql.Date;
import java.sql.Time;

public class Order {

    private int orderNo;
    private String seller;
    private String buyer;
    private Date time;

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int order_no) {
        this.orderNo = order_no;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
