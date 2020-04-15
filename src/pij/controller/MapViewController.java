/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pij.controller;

import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker.State;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import pij.entities.Campement;
import pij.services.campCrud;
import pij.utils.JSON_Reader;
import pij.utils.connectionDB;


/**
 * FXML Controller class
 *
 * @author Brahim
 */
public class MapViewController implements Initializable {

    @FXML
    private WebView webview;
    JSON_Reader jsonR = new JSON_Reader();
    @FXML
    private JFXListView<String> campList;
    ObservableList<Campement> Clist = FXCollections.observableArrayList();
    ObservableList<String> stringList = FXCollections.observableArrayList();
    campCrud cC = new campCrud();
    WebEngine webEngine ;
    @FXML
    private WebView youtubeView;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Load Maps
           webEngine = webview.getEngine();
        //URL url1;
        //url1 = this.getClass().getResource("/pij/views/webmaps.html");
       /* webEngine.getLoadWorker().stateProperty().addListener(
                    new ChangeListener<State>() {
                public void changed(ObservableValue ov, State oldState, State newState) {
                    if (newState == State.SUCCEEDED) {
                        webEngine.executeScript("addpopup(" + jsonR.getAtrr("lat", "Zarzis") + "," + jsonR.getAtrr("lng", "Zarzis") + ",'" + "tunis" + "')");
                    }
                }
            });*/
       
      webEngine.load(getClass().getResource("/pij/views/webmaps.html").toString());
      //Load Youtube :
    // youtubeView.getEngine().load("http://127.0.0.1:8000/volunteer/association");
     
      //Load List String of Camp Libelle
      Clist = update_list();
     Clist.stream().forEach(c -> stringList.add(c.getLibelle()));
     campList.setItems(stringList);
    }
//Load all camps
public ObservableList<Campement> update_list() {
                           try{
                        Connection con=connectionDB.getInstance().getCnx();
                   
ResultSet rs =con.createStatement().executeQuery("SELECT * FROM camp ");


while (rs.next()){
    Clist.add(new Campement(rs.getString("libelle"), rs.getString("location"),rs.getInt("capacity"), rs.getString("lat"), rs.getString("lng")));
                        
                    }

          }catch (Exception ex) {
            Logger.getLogger(Campement.class.getName()).log(Level.SEVERE, null, ex);
        }
      return Clist;
                            
}    

    @FXML
    private void get_data(MouseEvent event) throws SQLException {
     //  Campement c = (Campement)C.getSelectionModel().getSelectedItem();
       //Connection con=connectionDB.getInstance().getCnx();
       String lib = campList.getSelectionModel().getSelectedItem();
        System.out.println(lib);
        Campement c = cC.getCamp(lib);
        System.out.println(c);
                webEngine.getLoadWorker().stateProperty().addListener(
                    new ChangeListener<State>() {
                public void changed(ObservableValue ov, State oldState, State newState) {
                    if (newState == State.SUCCEEDED) {
                        webEngine.executeScript("addpopup(" + c.getLat() + "," + c.getLng() + ",'" + c.getLocation()+ "')");
                    }
                }
            });
       
      webEngine.load(getClass().getResource("/pij/views/webmaps.html").toString());
        //PreparedStatement stat = con.prepareStatement(rs);
     
       
    }
    
}
