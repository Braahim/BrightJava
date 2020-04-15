/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pij.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import pij.utils.connectionDB;
import pij.entities.Campement;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.TableSelectionModel;
import pij.services.campCrud;
import javafx.scene.chart.*;
import pij.utils.JSON_Reader;

/**
 * FXML Controller class
 *
 * @author Brahim
 */
public class CampementController implements Initializable {

    @FXML
    private JFXButton ajouter_C;
    @FXML
    private JFXButton modifier_C;
    @FXML
    private JFXButton supprimer_C;
    @FXML
    private TableView<Campement> table_C;
    @FXML
    private TableColumn<Campement, String> libelle;
    @FXML
    private TableColumn<Campement, String> adresse;
    
   ObservableList<Campement> Clist  = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Campement, Integer> capacity;
    @FXML
    private JFXTextField libelle_txt;
    @FXML
    private JFXComboBox<String> adresse_txt;
    @FXML
    private JFXTextField capacity_txt;
    
    TableCell cell = TableColumn.DEFAULT_CELL_FACTORY.call(libelle);
    campCrud cC = new campCrud();
    @FXML
    private JFXTextField filterField;
  //  private JFXComboBox<String> locBox;
    private JSON_Reader jsonR = new JSON_Reader();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
                   update_ObList();
                   supprimer_C.setDisable(true);
                   modifier_C.setDisable(true); 
                   List<String> locList;
                    locList = jsonR.govList();
                   adresse_txt.setItems(FXCollections.observableArrayList(locList));
                   //ObservableList<PieChart.Data> pieChartData =(Data) Clist;
                 //  final PieChart chart = new PieChart(Clist);
                 String lng = jsonR.getAtrr("lng","Tunis");
                 String lat = jsonR.getAtrr("lat","Tunis");
                 System.out.println(lng + lat);
                   
                
    }    

    @FXML
    private void ajouter_C(ActionEvent event) throws IOException {
                    
                    if (capacity_txt.getText().trim().isEmpty() || libelle_txt.getText().trim().isEmpty() || adresse_txt.getValue().trim().isEmpty()) {
                        Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Add Campement");
            alert.setHeaderText("information!");
            alert.setContentText("Erreur lors de l'ajout , informations manquantes !");
            alert.showAndWait();
                    }
                    else {
                        int capacity = Integer.parseInt(capacity_txt.getText());
         Campement C = new Campement(libelle_txt.getText(),adresse_txt.getValue(),capacity,jsonR.getAtrr("lat",adresse_txt.getValue()),jsonR.getAtrr("lng",adresse_txt.getValue()));
           //campCrud cC = new campCrud();
           int status = cC.addCampement(C);
                      if (status > 0){
            Alert alert =new Alert(Alert.AlertType.INFORMATION);
            
//              // get a handle to the stage
//    Stage stage = (Stage) ajout_C.getScene().getWindow();
//    // do what you have to do
//    stage.close();
//         
         //  Clist.clear();
           update_ObList();
            alert.setTitle("Add Campement");
            alert.setHeaderText("information!");
            alert.setContentText("Campement ajouté avec succès !");
            alert.showAndWait();
            
        }else {
             Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Add Campement");
            alert.setHeaderText("information!");
            alert.setContentText("Campement NON ajouté!");
            alert.showAndWait();
        }
                    }
    }

    @FXML
    private void supprimer_C(ActionEvent event) throws IOException {
              //         int capacity = Integer.parseInt(capacity_txt.getText());
         //Campement C = new Campement(libelle_txt.getText(),adresse_txt.getText(),capacity);
           //campCrud cC = new campCrud();
           int status = cC.deleteCampement(libelle_txt.getText());
                      if (status > 0){
            Alert alert =new Alert(Alert.AlertType.INFORMATION);
            
//              // get a handle to the stage
//    Stage stage = (Stage) ajout_C.getScene().getWindow();
//    // do what you have to do
//    stage.close();
//         
           
            update_ObList();
            alert.setTitle("Delete Campement");
            alert.setHeaderText("information!");
            alert.setContentText("Campement supprimé avec succès !");
            alert.showAndWait();
            
        }else {
             Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Delete Campement");
            alert.setHeaderText("information!");
            alert.setContentText("Campement NON supprimé!");
            alert.showAndWait();
        }
        
    }
    

    @FXML
    private void modifier_C(ActionEvent event) throws IOException {
        int capacity = Integer.parseInt(capacity_txt.getText());
         Campement C = new Campement(libelle_txt.getText(),adresse_txt.getValue(),capacity,jsonR.getAtrr("lat",adresse_txt.getValue()),jsonR.getAtrr("lng",adresse_txt.getValue()));
           int status = cC.updateCampement(C);
                      if (status > 0){
            Alert alert =new Alert(Alert.AlertType.INFORMATION);
            
//              // get a handle to the stage
//    Stage stage = (Stage) ajout_C.getScene().getWindow();
//    // do what you have to do
//    stage.close();
//         
           
           update_ObList();
            alert.setTitle("Update Campement");
            alert.setHeaderText("information!");
            alert.setContentText("Campement modifié avec succès !");
            alert.showAndWait();
            
        }else {
             Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Update Campement");
            alert.setHeaderText("information!");
            alert.setContentText("Campement NON modifié!");
            alert.showAndWait();
        }
        
                         
    }

    @FXML
    private void get_data(MouseEvent event) {
       Campement c = (Campement)table_C.getSelectionModel().getSelectedItem();
          libelle_txt.setText(String.valueOf(c.getLibelle()));
          adresse_txt.setValue(String.valueOf(c.getLocation()));
          capacity_txt.setText(String.valueOf(c.getCapacity()));
          ajouter_C.setDisable(true);
          supprimer_C.setDisable(false);
          modifier_C.setDisable(false);
          libelle_txt.setDisable(true);
          adresse_txt.setDisable(true);
          
    }
    private void update_ObList(){
        Clist.clear();
          try{
                        Connection con=connectionDB.getInstance().getCnx();
                   
ResultSet rs =con.createStatement().executeQuery("SELECT * FROM camp ");
//ResultSet rs2 =con.createStatement().executeQuery("SELECT * FROM don ");

while (rs.next()){
    Clist.add(new Campement(rs.getString("libelle"), rs.getString("location"),rs.getInt("capacity"), rs.getString("lat"), rs.getString("lng")));
                        
                    }

          }catch (Exception ex) {
            Logger.getLogger(Campement.class.getName()).log(Level.SEVERE, null, ex);
        }    
                  
       libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
       adresse.setCellValueFactory(new PropertyValueFactory<>("location"));     
       capacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));    
       table_C.setItems(Clist);

    }

    @FXML
    private void disable_Ajout(MouseEvent event) {

            if(event.getClickCount() == 2){
                System.out.println("Double clicked");
                  ajouter_C.setDisable(false);
                  libelle_txt.clear();
                  capacity_txt.clear();
                 // adresse_txt.setPlaceholder();
                  supprimer_C.setDisable(true);
                  modifier_C.setDisable(true);
                  libelle_txt.setDisable(false);
                  adresse_txt.setDisable(false);
            }
  
      
        
    }

    @FXML
    private void exit_btn(MouseEvent event) {
        ajouter_C.setStyle("-fx-text-fill: #5b5858;");
       
    }

    @FXML
    private void enter_btn(MouseEvent event) {

        ajouter_C.setStyle("-fx-text-fill: WHITE;");
        
        
    
    }



    @FXML
    private void engageSearch(MouseEvent event) {
        // Wrap the ObservableList in a FilteredList (initially display all data).
        
        FilteredList<Campement> filteredData = new FilteredList<>(Clist, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(camp -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (camp.getLibelle().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (camp.getLocation().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(camp.getCapacity()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Campement> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(table_C.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		table_C.setItems(sortedData);
               
       
    }

    }



