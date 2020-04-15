
package pij.controller;


/**
 * FXML Controller class
 *

 */
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import pij.entity.stock;
import pij.entity.stock_crud;


public class StockController implements Initializable {
     @FXML
    private TextField type;
    @FXML
    private Label ltype;
     @FXML
    private Label lb;
      
    @FXML
    private TableView<stock> table;
   
    @FXML
    private TableColumn<stock, String> col_type;
  
    public ObservableList<stock> tables = FXCollections.observableArrayList();
   
@FXML
    private TextField search;
     @FXML
    private void ajouter(ActionEvent event) {
         ltype.setText("");
       
 if(type.getText().isEmpty()){
         
      ltype.setText("Champs Vide * ");
        }else {
        String titre = type.getText();
        stock_crud ac = new stock_crud();
        stock a = new stock(titre);
      
          ac.ajouter(a);
            Alert alert =new Alert(AlertType.INFORMATION);
            alert.setTitle("Add Stock!");
            alert.setHeaderText("information!");
            alert.setContentText("Stock bien Ajouter!");
            alert.showAndWait();
       
         }  
    }

     @FXML
    private void SelectItemes(MouseEvent event) {
         ObservableList<stock> oblist;
        oblist = table.getSelectionModel().getSelectedItems();
        if (oblist != null) {
            type.setText(oblist.get(0).getType());

          
                        int max = oblist.get(0).getId();

        }
    }
    @FXML
    private void modifier(ActionEvent event) {
        lb.setText("");
       
 if(type.getText().isEmpty()){
         
      lb.setText("aucun STOCK n'est sélectionné  ");
        }else {
     stock A = new stock();
        A.setType(type.getText());
       

        ObservableList<stock> oblist;
        oblist = table.getSelectionModel().getSelectedItems();
        int max = oblist.get(0).getId();

        stock_crud act = new stock_crud();
        try {
            act.modifier(A, max);
            Alert alert =new Alert(AlertType.INFORMATION);
            alert.setTitle("Update  Stock!");
            alert.setHeaderText("information!");
            alert.setContentText("updated Stock !");
            alert.showAndWait();
        

        } catch (SQLException ex) {
            System.out.println(ex);
        }}
    }

    @FXML
    private void supp(ActionEvent event) {
 
  
                ObservableList<stock> oblist;
                oblist = table.getSelectionModel().getSelectedItems();
                int max = oblist.get(0).getId();

                stock_crud act = new stock_crud();
                try {
             act.supprimer(max);
            Alert alert =new Alert(AlertType.INFORMATION);
            alert.setTitle("delete  Stock!");
            alert.setHeaderText("information!");
            alert.setContentText("deleted Stock !");
            alert.showAndWait();
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
               
 
        
    }

    @FXML
    private void afficher(ActionEvent event) {
        
        
        
        col_type.setCellValueFactory(new PropertyValueFactory<>("type"));

        try {
            stock_crud act = new stock_crud();
            stock An = new stock();
            tables = act.afficher(An);
        } catch (SQLException ex) {

        }
        table.setItems((ObservableList<stock>) tables);
    }
 @FXML
    private void recherche(KeyEvent event) {
          stock_crud sp = new stock_crud();
         search.setOnKeyReleased(
         (   KeyEvent e)->{
             if(search.getText().equals("")){
         col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
    
             }else{ 
                 try{
                 
      col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
              table.getItems().clear();
        table.setItems(sp.rechercheCours(search.getText()));
        
                 } catch (SQLException ex) {
                Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);

                }



             }
         });
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
     public void vider (){
        type.clear();
    }
   
}
