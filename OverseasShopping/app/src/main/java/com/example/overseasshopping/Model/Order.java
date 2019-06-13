package com.example.overseasshopping.Model;

import java.util.Date;

public class Order {

    private Integer orderNo;
    private String seller;
    private String buyer;
    private String time;
    private String productName;
    private Integer productNo;
    private Double totalPrice;
    private Integer purchaseQuantity;

    public Order() {
        
    }

    public Order(String seller, String buyer, String time, Integer productNo, String productName, Integer purchaseQuantity, Double totalPrice) {
        this.seller = seller;
        this.buyer = buyer;
        this.time = time;
        this. productNo = productNo;
        this.productName = productName;
        this.purchaseQuantity = purchaseQuantity;
        this.totalPrice = totalPrice;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer order_no) {
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String product_name) {
        this.productName = product_name;
    }

    public Integer getProductNo() {
        return productNo;
    }

    public void setProductNo(Integer product_no){
        this.productNo = product_no;
    }

    public Double getTotalPrice(){
        return totalPrice;
    }

    public void setTotalPrice(Double total_price){
        this.totalPrice = total_price;
    }

    public Integer getPurchaseQuantity(){
        return purchaseQuantity;
    }

    public void setPurchaseQuantity(Integer purchase_quantity){
        this.purchaseQuantity = purchase_quantity;
    }
}
