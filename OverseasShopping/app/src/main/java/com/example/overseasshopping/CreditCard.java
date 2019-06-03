package com.example.overseasshopping;

import java.sql.Date;

public class CreditCard {

    private int creditCardId;
    private int creditCardNo;
    private int securityNo;
    private Date expiryDate;
    private String message;

    public CreditCard() {
    }

    public CreditCard(int creditCardId, int creditCardNo, int securityNo, Date expiryDate, String message) {
        this.creditCardId = creditCardId;
        this.creditCardNo = creditCardNo;
        this.securityNo = securityNo;
        this.expiryDate = expiryDate;
        this.message = message;
    }

    public int getCreditCardNo() {
        return creditCardNo;
    }

    public void setCreditCardNo(int creditCardNo) {
        this.creditCardNo = creditCardNo;
    }

    public int getCreditCardId() {
        return creditCardId;
    }

    public void setCreditCardId(int creditCardId) {
        this.creditCardId = creditCardId;
    }

    public int getSecurityNo() {
        return securityNo;
    }

    public void setSecurityNo(int securityNo) {
        this.securityNo = securityNo;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

