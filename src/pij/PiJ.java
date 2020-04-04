/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pij;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pij.utils.connectionDB;


/**
 *
 * @author Brahim
 */
public class PiJ extends Application {
    
    @Override
    public void start(Stage stage) throws IOException {
       connectionDB c = connectionDB.getInstance() ; 

      //Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("views/login.fxml"));
       FXMLLoader loader = new FXMLLoader(getClass().getResource("views/login.fxml"));
       Parent root = loader.load();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
