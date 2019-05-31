package com.example.overseasshopping.Model;

public class Product {

    private int productNo;
    private String productName;
    private String photo;
    private String description;
    private int price;
    private int productQuantity;

    public Product() {

    }

    public Product(String productName, String photo, String description, int price, int productQuantity) {
        this.productName = productName;
        this.photo = photo;
        this.description = description;
        this.price = price;
        this.productQuantity = productQuantity;
    }

    public int getProductNo() {
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

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getProductQuantity () {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity){
        this.productQuantity = productQuantity;
    }
}
