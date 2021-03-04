/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pij.controller;
looooooooooooool
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Statement;
import javafx.scene.control.Alert;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import pij.entity.don;
import pij.entity.don_crud;
import pij.entity.stock;
import pij.utils.MyConnection;

/**
 *
 * @author HP
 */
public class FrontStock  implements Initializable{ 
     
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
        List<String> type;
    public ObservableList<don> tables = FXCollections.observableArrayList();
    private don ev=null;
stock s =new stock();
@FXML
    private TextField search;
  


      @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    } 
    @FXML
    private void afficherr(ActionEvent event) {
     
       
      
        don_crud sp = new don_crud();
      List events=sp.displayALLDon();
       ObservableList et=FXCollections.observableArrayList(events);
    
      
        col_libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        col_quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_stock.setCellValueFactory(new PropertyValueFactory<>("sto"));
        
        don_crud act = new don_crud();
        don An = new don();
        tables = act.displayALLDon();
        table.setItems((ObservableList<don>) tables);
    }
 @FXML
    private void convertirEnPdf(ActionEvent event) throws FileNotFoundException, DocumentException {
           

    String file="E:\\ListeDon.pdf";
    Document document =new Document();
    Notifications notificationBuilder = Notifications.create()
            .title("Download completed")
            .text("saved In E:\\ ")
            .hideAfter(Duration.seconds(4))
            .position(Pos.BOTTOM_CENTER);
    notificationBuilder.showInformation();
      

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
        table.setItems(sp.rechercheDons(search.getText()));
        
                 } catch (SQLException ex) {
                Logger.getLogger(FrontStock.class.getName()).log(Level.SEVERE, null, ex);

                }



             }
         });
    }
  
    
    
    
    
    }

    

