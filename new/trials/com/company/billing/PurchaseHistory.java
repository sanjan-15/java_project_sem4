package com.company.billing;

import java.sql.Date;
import java.sql.Time;

public class PurchaseHistory {
    private int id;
    private String customerPhone;
    private String customerItems;
    private Date purchasedDate;
    private Time purchaseTime;
    private String empEmail;

    public PurchaseHistory(int id, String customerPhone, String customerItems, Date purchasedDate, Time purchaseTime, String empEmail) {
        this.id = id;
        this.customerPhone = customerPhone;
        this.customerItems = customerItems;
        this.purchasedDate = purchasedDate;
        this.purchaseTime = purchaseTime;
        this.empEmail = empEmail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerItems() {
        return customerItems;
    }

    public void setCustomerItems(String customerItems) {
        this.customerItems = customerItems;
    }

    public Date getPurchasedDate() {
        return purchasedDate;
    }

    public void setPurchasedDate(Date purchasedDate) {
        this.purchasedDate = purchasedDate;
    }

    public Time getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(Time purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }
}
