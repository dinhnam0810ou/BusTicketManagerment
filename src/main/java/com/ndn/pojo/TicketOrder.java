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
public class TicketOrder {
    private int id;
    private Date orderDate;
    private int amount;
    private int userId;

    public TicketOrder(int id, Date orderDate, int amount, int userId) {
        this.id = id;
        this.orderDate = orderDate;
        this.amount = amount;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "TicketOrder{" + "id=" + id + ", orderDate=" + orderDate + ", amount=" + amount + ", userId=" + userId + '}';
    }
    
}
