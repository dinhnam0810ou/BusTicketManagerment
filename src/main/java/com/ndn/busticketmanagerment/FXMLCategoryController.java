/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ndn.busticketmanagerment;

//import com.hcm.conf.jdbcUtils;
import com.ndn.pojo.Category;
import com.ndn.service.CategoryService;
import com.ndn.service.DV_Category;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class FXMLCategoryController implements Initializable {

    /**
     * Initializes the controller class.
     */

     @FXML
    private TextField txt_bienSo;

    @FXML
    private TextField txt_IdXe;

    @FXML
    private TextField txt_den;

    @FXML
    private TextField txt_di;

    @FXML
    private TextField txt_giaVe;

    @FXML
    private TableView<Category> table_InfoChuyenXe;

    //
    TableColumn col_IdXe;
    TableColumn col_Den;
    TableColumn col_Di;
    TableColumn col_bienSo;
    TableColumn col_giaVe;
    ObservableList<Category> listXe;

    int index = -1;
//Du lieu
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    
    //tác động trong giao diện
    @FXML
    void getSelected(MouseEvent event) {
//        System.out.println("*********************************************" + index);
        index = table_InfoChuyenXe.getSelectionModel().getSelectedIndex();
//        System.out.println("*********************************************" + index);
        if (index <= -1) {
            return;
        }
        
//        System.out.println("********************* col_IdXe "+ col_IdXe.toString());
//        System.out.println("*********************************************" + col_IdXe.getCellData(index).toString());
        txt_IdXe.setText(col_IdXe.getCellData(index).toString());
        txt_bienSo.setText(col_bienSo.getCellData(index).toString());
        txt_den.setText(col_Den.getCellData(index).toString());
        txt_di.setText(col_Di.getCellData(index).toString());
        txt_giaVe.setText(col_giaVe.getCellData(index).toString());
    }

    
    //Them Chuyen Di
    public void add_data(ActionEvent event) throws SQLException {
        conn = DV_Category.ConnectDbXe();
        String sql = "insert into xe (MaChuyenXe, NoiDi, NoiDen, BienSoXe, GiaVe)values(?,?,?,?,?)";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_IdXe.getText());
            pst.setString(2, txt_den.getText());
            pst.setString(3, txt_di.getText());
            pst.setString(4, txt_bienSo.getText());
            pst.setString(5, txt_giaVe.getText());
            pst.execute();

            loadTableData(null);

        } catch (Exception e) {
            System.out.println("Cant ADD " + e.getMessage());
        }
    }

//    Sửa Chuyến đi
    public void edit_data(ActionEvent event) {
        try {
            conn = DV_Category.ConnectDbXe(); // connect Db
            System.out.println("************** class FXML ChuyenDiControler edit_data()" );
            String MaChuyenXe = txt_IdXe.getText();
            String noiDi = txt_di.getText();
            String noiDen = txt_den.getText();
            String bienSo = txt_bienSo.getText();
            String giaVe = txt_giaVe.getText();
            
            //ràng buộc
            String sql1 = "update xe set MaChuyenXe = '" + MaChuyenXe + "',NoiDi = '" + noiDi + "',NoiDen = '"
                    + noiDen + "',BienSoXe = '" + bienSo + "',GiaVe = '" + giaVe + "' where MaChuyenXe= '" + MaChuyenXe + "' ";
            
            pst = conn.prepareStatement(sql1);
//            int Db = 0;
            pst.executeUpdate();
//            if (Db > 0){
//                 System.out.println("**************  edit_data() sql execution succesfull " + sql1 );
//            }else {
//                 System.out.println("**************  edit_data() sql execution failed " + sql1 );
//            }
            
            loadTableData(null);

        } catch (SQLException ex) {
            System.out.println("************** class FXML ChuyenDiControler edit_data() SQL Exception" + ex.getMessage());
            Logger.getLogger(FXMLTicketController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Xoa Chuyen di
    public void del_data(ActionEvent event) throws SQLException {
        try {
            conn = DV_Category.ConnectDbXe();
            String sql = "DELETE FROM xe where MaChuyenXe = ? ";
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_IdXe.getText());
//            pst.setString(2, txt_den.getText());
//            pst.setString(3, txt_di.getText());
//            pst.setString(4, txt_giaVe.getText());
            pst.execute();

            loadTableData(null);

        } catch (Exception e) {
            System.out.println("Can't delete " + e.getMessage());
        }
    }
    
    //Lam moi data
    public void reFresh_data(ActionEvent event) throws SQLException{
        loadTableData(null);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
        display();
    }

    
    private void upChuyenXe() {
        TableColumn col_idXe = new TableColumn("Mã Chuyến Xe");
        col_idXe.setCellValueFactory(new PropertyValueFactory("MaChuyenXe"));
        col_idXe.setPrefWidth(180);
//        System.out.println("********************* col_idXe local "+ col_idXe.toString());
        col_IdXe = col_idXe;
//        System.out.println("********************* col_IdXe local upChuyenXe() "+ col_IdXe.toString());
//        TableColumn col_chuyenXe = new TableColumn("Chuyến Xe");
//        col_chuyenXe.setCellValueFactory(new PropertyValueFactory("ChuyenXe"));
//        col_chuyenXe.setPrefWidth(150);

        TableColumn col_Di = new TableColumn("Nơi Đi");
        col_Di.setCellValueFactory(new PropertyValueFactory("NoiDi"));
        col_Di.setPrefWidth(150);
        this.col_Di = col_Di;

        TableColumn col_Den = new TableColumn("Nơi Đến");
        col_Den.setCellValueFactory(new PropertyValueFactory("NoiDen"));
        col_Den.setPrefWidth(150);
        this.col_Den = col_Den;

        TableColumn col_bienSo = new TableColumn("Biển Số Xe");
        col_bienSo.setCellValueFactory(new PropertyValueFactory("BienSoXe"));
        col_bienSo.setPrefWidth(150);
        this.col_bienSo = col_bienSo;

        TableColumn col_giaVe = new TableColumn("Giá Vé");
        col_giaVe.setCellValueFactory(new PropertyValueFactory("GiaVe"));
        col_giaVe.setPrefWidth(100);
        this.col_giaVe = col_giaVe;

        this.table_InfoChuyenXe.getColumns().addAll(col_idXe, col_Di, col_Den, col_bienSo, col_giaVe);
    }
    

    private void loadTableData(String info) throws SQLException {
        DV_Category x = new DV_Category();
        this.table_InfoChuyenXe.setItems(FXCollections.observableList(DV_Category.getListCategory(info)));
    }

    public void display() {
        this.upChuyenXe();
        try {
            this.loadTableData(null);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLCategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
