/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pij.controller;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXTimePicker;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javafx.scene.control.Label;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Window;
import javax.swing.JFileChooser;

import static jdk.nashorn.internal.objects.NativeString.search;
import pij.entity.don;
import pij.entity.don_crud;
import pij.entity.stock;
import pij.entity.stock_crud;
import pij.utils.MyConnection;

/**
 *
 * @author HP
 */
public class DonController implements Initializable {
    
     @FXML
    private TextField tritre;
    @FXML
    private TextField tville;
    @FXML
    private TableView<don> table;
    @FXML
    private TableColumn<don, String> col_libelle;
    @FXML
    private TableColumn<don, Integer> col_quantite;
    @FXML
    private TableColumn<don, Date> col_date;
    @FXML
    private TableColumn<don, String> col_stock;
    @FXML
    private DatePicker dated;
    @FXML
    private ComboBox<String> Cmatiere;
    @FXML
    private Label labelnom;
    @FXML
    private Label labelu;
    @FXML
    private Label labeldescription;
    @FXML
    private Label labeldatedebut;
    @FXML
    private Label labeldatefin;
    public ObservableList<don> tables = FXCollections.observableArrayList();
    private don ev=null;
stock s =new stock();
@FXML
    private TextField search;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    } 
   @FXML
    private void convertirEnPdf(ActionEvent event) throws FileNotFoundException, DocumentException {
    String file="E:\\don.pdf";
    Document document =new Document();
      Alert a2 = new Alert(Alert.AlertType.CONFIRMATION);
        a2.setTitle("Conversion PDF !");
        a2.setContentText("PDF telecharge avec succés!");
        a2.show();

     try{
           Font f = new Font(FontFactory.getFont(FontFactory.TIMES_BOLD, 24, Font.UNDERLINE));
            f.setColor(0, 153, 255);
      

            Font f2 = new Font(FontFactory.getFont(FontFactory.TIMES_BOLD, 20, Font.BOLD));
            f2.setColor(0, 0, 0);

            PdfWriter.getInstance(document, new FileOutputStream(new File(file)));
         document.open();
         Paragraph p =new Paragraph("LISTE  DES  DON  " ,f);
       
                    

       document.add(Image.getInstance("E:\\don.png"));
  Paragraph pm =new Paragraph();
         pm.add("   \n  ");
         p.setAlignment(Element.ALIGN_CENTER);
        
         document.add(p);
            document.add(pm);

document.add(pm);
Paragraph posss= new Paragraph("__________________________________________________");
document.add(posss);
Paragraph pos= new Paragraph("Libelle"+"      "+"Quantite "+"      "+" Date"+"      "+"Type",f2);
document.add(pos);
document.add(posss);
         Connection cn2 = MyConnection.getInstance().getCnx();
         String req ="select d.*,s.type from don d INNER JOIN stock s on d.Stock_id = s.id  ";
            Statement pst = cn2.createStatement();
            ResultSet rs = pst.executeQuery(req);
      while (rs.next()) {
              
Paragraph p1= new Paragraph( "   ");
Paragraph po= new Paragraph(rs.getString("libelle")+"                      "+rs.getString("quantite")+"                     "+rs.getString("date")+"               "+rs.getString("type"));

               


document.add(p1);
document.add(po);



                
           
            }
         document.close();
         System.out.println("Done");
        
     }catch(Exception e){
         e.printStackTrace();
     }
     
     
    }
    
  
  
    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
     labelnom.setText("");
     labeldescription.setText("");
     labeldatedebut.setText("");
     labeldatefin.setText(""); 
    System.out.println("date d'aujourdhui"+new java.util.Date());
    SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
    System.out.println(formater.format(new java.util.Date()));
    String aujourdhui=formater.format(new java.util.Date());
 if(tritre.getText().isEmpty()||dated.getValue().equals(LocalDate.now())||tville.getText().isEmpty()||Cmatiere.getSelectionModel().getSelectedItem().equals("Selectionner stock")){
         
         if (tritre.getText().isEmpty()) {
          labelnom.setText("Libelle Vide");
        }
         if (tville.getText().isEmpty()) {
          labeldescription.setText("Qantité Vide");
        }
         if (dated.getValue().equals(LocalDate.now())) {
        labeldatedebut.setText("Date Vide");
        } 
         if (Cmatiere.getSelectionModel().getSelectedItem().equals("Selectionner stock")) {
           labeldatefin.setText("Stock Vide");
        } 
    

        }else { 
     
        int nb_place= Integer.parseInt(tville.getText());
        if(nb_place<0 || dated.getValue().toString().compareTo(aujourdhui)>0 ){ 
            if (nb_place<0 ) {
            labeldescription.setText("quantite doit etre > 0 ");}
            if (dated.getValue().toString().compareTo(aujourdhui)>0) {
            labeldatedebut.setText("Date doit etre < a celle d'aujourdhui  ");}
       
       } else {
       
        String a = tritre.getText();
        LocalDate d= (LocalDate)dated.getValue();
        java.sql.Date sqlDate1 = java.sql.Date.valueOf(d);
        don_crud sp = new don_crud();
        stock_crud stocks = new stock_crud();
        stock s=new stock();
        s=stocks.getStock(Cmatiere.getValue());
        don_crud ac = new don_crud();
        don dd = new don(a,nb_place,sqlDate1,s.getId());
        ac.ajouter(dd); 
        Alert alert =new Alert(AlertType.INFORMATION);
            alert.setTitle("Add Don!");
            alert.setHeaderText("information!");
            alert.setContentText("Added  Don!");
            alert.showAndWait();}
 }
    
    }
    @FXML
    private void SelectItemes(MouseEvent event) {
        Connection cn2= MyConnection.getInstance().getCnx();
        ObservableList<don> oblist;
        oblist = table.getSelectionModel().getSelectedItems();
        ev = (don)table.getSelectionModel().getSelectedItem();
        stock_crud stocks = new stock_crud();
        stock b=new stock();
        if (oblist != null) {
            tritre.setText(oblist.get(0).getLibelle());
            tville.setText(""+oblist.get(0).getQuantite());
            dated.setValue(LocalDate.now());
            try {
                 Cmatiere.setValue(stocks.getStockType(ev.getStock_id()));
                } catch (SQLException ex) {
                   Logger.getLogger(DonController.class.getName()).log(Level.SEVERE, null, ex);
                }                        
            int max = oblist.get(0).getReference();

        }
        ObservableList<String> availableChoices = FXCollections.observableArrayList("Selectionner stock");
        stock_crud a=new stock_crud();
        String req = "SELECT * FROM stock";
        try {
            Statement pst = cn2.createStatement();
            ResultSet rs = pst.executeQuery(req);
            while (rs.next()) {
                s.setId(rs.getInt("id"));
                s.setType(rs.getString("type"));
                availableChoices.add(s.getType());
                Cmatiere.setItems(availableChoices);
                Cmatiere.getSelectionModel().selectFirst();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
         labelu.setText("");
        if(tritre.getText().isEmpty()||tville.getText().isEmpty()||Cmatiere.getSelectionModel().getSelectedItem().equals("Selectionner matiere")){
             labelu.setText("aucun Don n'est sélectionné  ");
        }else {
            try {
                don A = new don();
                A.setLibelle(tritre.getText());
                A.setQuantite(Integer.parseInt(tville.getText()));
                stock_crud stocks = new stock_crud();
                stock b=new stock();
                b=stocks.getStock(Cmatiere.getValue());  
                System.out.println("cle etranger"+b.getId());

               ObservableList<don> oblist;
               oblist = table.getSelectionModel().getSelectedItems();
               don_crud act = new don_crud();
               LocalDate d= (LocalDate)dated.getValue();
               java.sql.Date datee = java.sql.Date.valueOf(d);
               don a=new don(tritre.getText(),Integer.parseInt(tville.getText()),datee,b.getId());
               act.modifier(a,ev.getReference());
            
            Alert alert =new Alert(AlertType.INFORMATION);
            alert.setTitle("Update Don!");
            alert.setHeaderText("information!");
            alert.setContentText("Updated Don!");
            alert.showAndWait();
            } catch (SQLException ex) {
            System.out.println(ex);
        }
 }
        
    }

    @FXML
    private void supp(ActionEvent event) {
        ObservableList<don> oblist;
        oblist = table.getSelectionModel().getSelectedItems();
        int max = oblist.get(0).getReference();
        don A = new don();
        don_crud act = new don_crud();
        try {
            act.supprimer(max);
             Alert alert =new Alert(AlertType.INFORMATION);
            alert.setTitle("delete Don!");
            alert.setHeaderText("information!");
            alert.setContentText("Deleted  Don!");
            alert.showAndWait();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    @FXML
    private void afficher(ActionEvent event) {
     
        col_libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        col_quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_stock.setCellValueFactory(new PropertyValueFactory<>("Stock_id"));

        try {
            don_crud act = new don_crud();
            don An = new don();

            tables = act.afficher(An);

        } catch (SQLException ex) {

       }
        table.setItems((ObservableList<don>) tables);
    }
    
     public void vider (){
       tritre.clear();
       tville.clear();
       

    }
     @FXML
     private void recherche(KeyEvent event) {
          don_crud sp = new don_crud();
         search.setOnKeyReleased(
         (   KeyEvent e)->{
             if(search.getText().equals("")){
      
             }else{ 
                 try{
     col_libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        col_quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_stock.setCellValueFactory(new PropertyValueFactory<>("sto"));
              table.getItems().clear();
        table.setItems(sp.rechercheDon(search.getText()));
        
                 } catch (SQLException ex) {
                Logger.getLogger(DonController.class.getName()).log(Level.SEVERE, null, ex);

                }



             }
         });
    }

    private void initComponents() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
  
   
}
