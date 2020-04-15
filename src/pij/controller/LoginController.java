/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pij.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
<<<<<<< HEAD
import pij.utils.MyConnection;
=======
import pij.utils.connectionDB;
>>>>>>> shaima

/**
 * FXML Controller class
 *
 * @author Brahim
 */
public class LoginController implements Initializable {

<<<<<<< HEAD
    Connection con=MyConnection.getInstance().getCnx();
=======
    
    Connection con=connectionDB.getInstance().getCnx();
>>>>>>> shaima
    @FXML
    private JFXTextField txtF;
    @FXML
    private JFXPasswordField passF;
    @FXML
    private JFXButton button;
    @FXML
    private ImageView Logo;
    @FXML
    private Label lbletat;

    /**
     * Initializes the controller class.
     */
<<<<<<< HEAD
   /* @Override
=======
    @Override
>>>>>>> shaima
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void loginn(ActionEvent event) {
           PreparedStatement stat =null;
        ResultSet rs = null;
        String sql ="SELECT * FROM users WHERE name=? AND password =?";
        try{
            stat=con.prepareStatement(sql);
            stat.setString(1, txtF.getText().toString());
            stat.setString(2, passF.getText().toString());
            rs=stat.executeQuery();
            if (rs.next()){
                lbletat.setText("Connecté!");
                    
                
                Stage stage =new Stage();
                 Parent root = FXMLLoader.load(getClass().getResource("/pij/views/Dashboard.fxml"));
              
                 Scene scene = new Scene(root);
                 stage.setScene(scene);
               
                stage.setTitle("Home");
                stage.show();
                
            }else {
                lbletat.setText("Non connecté!");
                Alert alert= new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Erreur");
                alert.setTitle("Alert");
                alert.setContentText("Username ou password sont incorrect");
            alert.showAndWait();
            }
            
        }catch (SQLException e){
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
<<<<<<< HEAD
    }*/
       @FXML
   public void loginn (ActionEvent event )throws SQLException {
PreparedStatement stat =null;
        ResultSet rs = null;
        String sql ="SELECT * FROM users WHERE name=? AND password =?";
        try{
            stat=con.prepareStatement(sql);
            stat.setString(1, txtF.getText().toString());
            stat.setString(2, passF.getText().toString());
            rs=stat.executeQuery();
            if (rs.next()){
                lbletat.setText("Connecté!");
                Stage stage =new Stage();
                 Parent root = FXMLLoader.load(getClass().getResource("/pij/views/Front.fxml"));
                 Scene scene = new Scene(root);
                 stage.setScene(scene);
               
                stage.setTitle("Home");
                stage.show();
                
            }else {
                lbletat.setText("Non connecté!");
                Alert alert= new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Erreur");
                alert.setTitle("Alert");
                alert.setContentText("Username ou password sont incorrect");
            alert.showAndWait();
            }
            
        }catch (SQLException e){
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void initialize(URL location,ResourceBundle resources){
        
    } 

=======
    }
    
>>>>>>> shaima
}
