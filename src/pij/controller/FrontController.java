/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pij.controller;

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
import javafx.scene.layout.VBox;

/**
 *
 * @author HP
 */

public class FrontController  implements Initializable{
   
    @FXML
    private VBox pnl_scroll;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
     private void refreshNodes(String node)
    {
        pnl_scroll.getChildren().clear();
        
        Node [] nodes = new  Node[1];
        
      
            try {
                nodes[0] = (Node)FXMLLoader.load(getClass().getResource(node));
               pnl_scroll.getChildren().add(nodes[0]);
                
            } catch (IOException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        
    }
     @FXML
    private void click_Stock(ActionEvent event) {
         refreshNodes("/pij/views/dashboard/FrontStock.fxml");
    }

}
