/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ndn.busticketmanagerment;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class FXMLLoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
   @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void switchAdmin(ActionEvent event) throws IOException {
             FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("FXMLChuyenDi.fxml"));     
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("ADMIN");
            stage.show();
    }
    public void switchNV(ActionEvent event) throws IOException {
             FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("FXMLVeXe.fxml"));     
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("NHANVIEN");
            stage.show();
    }
}


