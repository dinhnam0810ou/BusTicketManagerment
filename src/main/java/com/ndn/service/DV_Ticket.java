/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ndn.service;

import com.ndn.pojo.Ticket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author USer
 */
public class DV_Ticket {

    Connection conn = null;

    public static Connection ConnectDbVeXe() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/ticketdb", "root", "");
            return conn;
            
        } catch (ClassNotFoundException e) {
            System.out.println("Connect DbXe InCorect ClassNotFoundException" + e.getMessage());
            return null;
        } catch (SQLException ex) {
            System.out.println("Connect DbXe InCorect SQLException" + ex.getMessage());
            return null;
        }
    }

    public static ObservableList<Ticket> getListTicket() throws SQLException {
        Connection conn = ConnectDbVeXe();
        ObservableList<Ticket> listVX = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from quanlyvexe.vexe");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                listVX.add(new Ticket(Integer.parseInt(rs.getString("MaVe")),
                        rs.getString("ChuyenXe"), rs.getTime("GioKhoiHanh"), rs.getDate("NgayKhoiHanh"),
                        rs.getDouble("GiaVe"), rs.getString("HoTenKH"), rs.getString("BienSoXe")));
            }
        } catch (Exception e) {
            System.out.println("Cannot Connect" + e.getMessage());
        }
        return listVX;
    }
    
       public List<Ticket> getVeXe(String kw) throws SQLException{
           List<Ticket> veXe= new ArrayList<>();
           try(Connection conn = ConnectDbVeXe()){
               String sql = "SELECT * FROM quanlyvexe.vexe";
               if(kw != null && !kw.isEmpty())
                   sql += "WHERE ChuyenXe like concat('%',?,'%')";
               PreparedStatement stm = conn.prepareStatement(sql);
               if(kw != null && !kw.isEmpty())
                   stm.setString(1, kw);
               
               ResultSet rs = stm.executeQuery();
               while(rs.next()){
                   Ticket q = new Ticket(Integer.parseInt(rs.getString("MaVe")), rs.getString("ChuyenXe"), 
                      rs.getTime("GioKhoiHanh"), rs.getDate("NgayKhoiHanh"), rs.getDouble("GiaVe"), rs.getString("HoTenKH"),
                      rs.getString("BienSoXe"));
                   veXe.add(q);}
           }
           return veXe;
       }
       public Ticket GetVeXeByID(String MaVe) throws SQLException{
            Ticket vx = null;
            try(Connection conn = ConnectDbVeXe()){
                PreparedStatement stm = conn.prepareCall("SELECT * FROM quanlyvexe.vexe WHERE MaVe = ?");
                stm.setString(1, MaVe);
                
                ResultSet rs = stm.executeQuery();
                
                while(rs.next()){
                    vx = new Ticket(Integer.parseInt(rs.getString("MaVe")), rs.getString("ChuyenXe"), 
                      rs.getTime("GioKhoiHanh"), rs.getDate("NgayKhoiHanh"), rs.getDouble("GiaVe"), rs.getString("HoTenKH"),
                      rs.getString("BienSoXe"));
                    break;
                }
            }
            return vx;
       }
}
