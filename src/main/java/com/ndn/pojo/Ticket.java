/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndn.pojo;

import java.sql.Date;

/**
 *
 * @author Nguyen Dinh Nam
 */
public class Ticket {
    private int id;
    private String name;
    private double price;
    private Date createDate;
    private int active;
    private String description;
    private int categoryId;

    public Ticket(int id, String name, double price, Date createDate, int active, String description, int categoryId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.createDate = createDate;
        this.active = active;
        this.description = description;
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Ticket{" + "id=" + id + ", name=" + name + ", price=" + price + ", createDate=" + createDate + ", active=" + active + ", description=" + description + ", categoryId=" + categoryId + '}';
    }
    
}
