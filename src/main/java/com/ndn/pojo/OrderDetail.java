/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndn.pojo;

/**
 *
 * @author Nguyen Dinh Nam
 */
public class OrderDetail {
    private int id;
    private int orderId;
    private int ticketId;
    private double unitPrice;
    private int quantity;

    public OrderDetail(int id, int orderId, int ticketId, double unitPrice, int quantity) {
        this.id = id;
        this.orderId = orderId;
        this.ticketId = ticketId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "id=" + id + ", orderId=" + orderId + ", ticketId=" + ticketId + ", unitPrice=" + unitPrice + ", quantity=" + quantity + '}';
    }
    
}
