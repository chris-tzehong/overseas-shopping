package com.example.overseasshopping.Model;

import java.util.UUID;

public class Product {

    private Integer productNo;
    private String productName;
    private String photo;
    private String description;
    private Double price;
    private Integer productQuantity;
    private Integer userNo;
    private UUID photoID;

    public Product() {
        photoID = UUID.randomUUID();
    }

    public Product(String productName, String photo, Integer userNo, String description, Double price, Integer productQuantity) {
        this.productName = productName;
        this.photo = photo;
        this.description = description;
        this.price = price;
        this.productQuantity = productQuantity;
        this.userNo = userNo;
    }

    public UUID getPhotoID() {
        return photoID;
    }

    public Integer getUserNo() {
        return userNo;
    }

    public void setUserNo(Integer userNo) {
        this.userNo = userNo;
    }

    public Integer getProductNo() {
        return productNo;
    }

    public void setProductNo(int productNo) {
        this.productNo = productNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPhoto() {
        return photo;
    }

    public String getPhotoFileName() {
        return "IMG_" + getPhotoID().toString() + ".jpg";
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getProductQuantity () {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity){
        this.productQuantity = productQuantity;
    }
}
