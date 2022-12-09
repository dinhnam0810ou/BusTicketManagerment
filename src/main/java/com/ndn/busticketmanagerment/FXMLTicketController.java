/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ndn.busticketmanagerment;

import com.ndn.pojo.Ticket;
import com.ndn.service.CategoryService;
import com.ndn.service.DV_Ticket;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.DatePicker;

/**
 * FXML Controller class
 *
 * @author congm
 */
public class FXMLTicketController implements Initializable {
    
    @FXML
    private DatePicker txtDate;
     
    @FXML
    private TextField txtChuyenXe;
    
    @FXML
    private TextField txtGio;
            
    @FXML
    private TextField txtHoten;
    
    @FXML
    private TextField txtMaVe;
    
    @FXML
    private TableView<Ticket> table_ticket;
    
    @FXML
    private TextField txtKeyword;
    
    ObservableList<Ticket> listVeXe;
    int index = -1;
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    TableColumn colID;
    TableColumn colChuyenXe;
    TableColumn colTime;
    TableColumn colDate;
    TableColumn colCost;
    TableColumn colName;
    TableColumn colbienSo;
    
    
    public void themKH(ActionEvent event) throws SQLException{
        conn = DV_Ticket.ConnectDbVeXe();
        String sql = "insert into Ticket (id, Name, GioKhoiHanh,ChuyenXe)values(?,?,?,?)";
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1, txtMaVe.getText());
            pst.setString(2, txtHoten.getText());
            pst.setString(3, txtGio.getText());
//            pst.setDate(4, txt);
            pst.setString(5, txtChuyenXe.getText());
            pst.execute();
            loadTableData(sql);
        }catch(SQLException e){
            this.loadTableData(null);
        }catch(Exception e){
            System.out.println("Can't add data" + e.getMessage());
        }
    }
    
    
    public void ChinhSua(ActionEvent event){
        try {
            conn = DV_Ticket.ConnectDbVeXe();
            String maVe = txtMaVe.getText();
            String hoTenKH = txtHoten.getText();
            String Gio = txtGio.getText();
            String ChuyenXe = txtChuyenXe.getText();
            
            String sql1 = "update vexe set MaVe = '"+maVe+"',HoTenKH = '"+hoTenKH+"',GioKhoiHanh = '"+
                    Gio+"',ChuyenXe = '"+ChuyenXe+"' where MaVe= '"+maVe+"' ";
            pst = conn.prepareStatement(sql1);
            pst.execute();
            loadTableData(null);
            this.loadTableData(null);
        } catch (SQLException ex) {
             System.out.println("Can't edit data" + ex.getMessage());
        }
    }
    
    
    public void Xoa(ActionEvent event) throws SQLException{
        try{
            conn = DV_Ticket.ConnectDbVeXe();
        String sql = "DELETE FROM vexe WHERE MaVe = ?";
        pst = conn.prepareStatement(sql);
        pst.setString(1, txtMaVe.getText());
        pst.execute();
            loadTableData(sql);
        this.loadTableData(null);
        }
        catch(SQLException ex){
            System.out.println("Can't edit data" + ex.getMessage());
        }
    }

    public void Display(){
        this.loadTableView();
        try {
            this.loadTableData(null);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLTicketController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

   
    //lay khach hang voi cellclick 
    @FXML
    void getSelected (MouseEvent event){
        index = table_ticket.getSelectionModel().getSelectedIndex();
        if(index <= -1){
            return;
        }
        txtMaVe.setText(colID.getCellData(index).toString());
        txtHoten.setText(colName.getCellData(index).toString());
        txtGio.setText(colTime.getCellData(index).toString());
        txtChuyenXe.setText(colChuyenXe.getCellData(index).toString());
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            Display();
            this.txtKeyword.textProperty().addListener((evt) ->{
                try {
                    this.loadTableSearch(this.txtKeyword.getText());
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLTicketController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
    }
    
    
    private void loadTableView(){
        colID = new TableColumn("Mã Vé");
        colID.setCellValueFactory(new PropertyValueFactory("MaVe"));
        this.colID = colID;
        
        colChuyenXe = new TableColumn("Chuyến Xe");
        colChuyenXe.setCellValueFactory(new PropertyValueFactory("ChuyenXe"));
        this.colChuyenXe = colChuyenXe;
        
        colTime = new TableColumn("Giờ");
        colTime.setCellValueFactory(new PropertyValueFactory("GioKhoiHanh"));
        this.colTime = colTime;
        
        colDate = new TableColumn("Ngày");
        colDate.setCellValueFactory(new PropertyValueFactory("NgayKhoiHanh"));
        this.colDate = colDate;
        
        colCost = new TableColumn("Giá Vé");
        colCost.setCellValueFactory(new PropertyValueFactory("GiaVe"));
        this.colCost = colCost;
        
        colName = new TableColumn("Tên KH");
        colName.setCellValueFactory(new PropertyValueFactory("HoTenKH"));
        this.colName = colName;
        
        colbienSo = new TableColumn("Biển Số");
        colbienSo.setCellValueFactory(new PropertyValueFactory("BienSoXe"));
        this.colbienSo = colbienSo;
        
        this.table_ticket.getColumns().addAll(colID,colChuyenXe,colTime,colDate,colCost,colName,colbienSo);
    }
    
     private void loadTableData(String kw) throws SQLException{
         DV_Ticket vx = new DV_Ticket();
         this.table_ticket.setItems(FXCollections.observableList(vx.getListTicket()));
     }  
     
     private void loadTableSearch(String kw) throws SQLException{
         DV_Ticket vx = new DV_Ticket();
         this.table_ticket.setItems(FXCollections.observableList(vx.getVeXe(kw)));
     } 
}
