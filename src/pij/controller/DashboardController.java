/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pij.controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Brahim
 */
public class DashboardController implements Initializable {

    @FXML
    private VBox pnl_scroll;
    @FXML
    private Label lbl_currentprojects;
    @FXML
    private Label lbl_pending;
    @FXML
    private Label lbl_completed;
    @FXML
    private JFXButton association_btn;
    @FXML
    private JFXButton stock_btn;
    @FXML
    private JFXButton event_btn;
    @FXML
    private JFXButton sante_btn;
    @FXML
    private JFXButton don_btn;
    @FXML
    private JFXButton refugee_btn;
    @FXML
    private JFXButton camp_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
 
    private void refreshNodes(String node)
    {
        pnl_scroll.getChildren().clear();
        
        Node [] nodes = new  Node[15];
        
        for(int i = 0; i<10; i++)
        {
            try {
                nodes[i] = (Node)FXMLLoader.load(getClass().getResource(node));
               pnl_scroll.getChildren().add(nodes[i]);
                
            } catch (IOException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }  
    }



    @FXML
    private void handleButtonAction(MouseEvent event) {
    }

    @FXML
    private void click_association(ActionEvent event) {
         refreshNodes("/pij/views/dashboard/association.fxml");
    }

    @FXML
    private void click_stock(ActionEvent event) {
         refreshNodes("/pij/views/dashboard/stock.fxml");
    }

    @FXML
    private void click_event(ActionEvent event) {
         refreshNodes("/pij/views/dashboard/evenement.fxml");
    }

    @FXML
    private void click_sante(ActionEvent event) {
         refreshNodes("/pij/views/dashboard/sante.fxml");
    }

    @FXML
    private void click_don(ActionEvent event) {
         refreshNodes("/pij/views/dashboard/don.fxml");
    }

    @FXML
    private void click_refugee(ActionEvent event) {
         refreshNodes("/pij/views/dashboard/refugee.fxml");
    }

    @FXML
    private void click_camp(ActionEvent event) {
         refreshNodes("/pij/views/dashboard/campement.fxml");
    }
    
}
