/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pij.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pij.utils.MyConnection;

/**
 *
 * @author YURI
 */
public class stock_crud {
   Connection cn2;
    Statement st;

    public stock_crud() {
        cn2 = MyConnection.getInstance().getCnx();
    }
    public stock getStock(String a) throws SQLException {   
        stock an = new stock();
        PreparedStatement pre = cn2.prepareStatement("SELECT * FROM stock WHERE type LIKE ?  ;");

        pre.setString(1, a);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {

            an.setId(rs.getInt("id"));
           an.setType(a);


           
        }
        return an;

    }
        public stock getStockId(int a) throws SQLException {
          stock an = new stock();
        PreparedStatement pre = cn2.prepareStatement("SELECT * FROM stock WHERE id = ?  ;");

        pre.setInt(1, a);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {

            an.setId(a);
           an.setType(rs.getString("type"));


           
        }
        return an;
     }
    
     public String getStockType(int a) throws SQLException {
          String an="" ;
        PreparedStatement pre = cn2.prepareStatement("SELECT type FROM stock WHERE id = ?  ;");

        pre.setInt(1, a);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {

            an=rs.getString(1);


           
        }
        return an;
     }
    public ObservableList<stock> displayALLStock() {
        ObservableList<stock> myList = FXCollections.observableArrayList();
        try {
            String req = "SELECT * FROM stock";
            Statement pst = cn2.createStatement();
            ResultSet rs = pst.executeQuery(req);
            while (rs.next()) {
                stock p = new stock();
                p.setId(rs.getInt("id"));
                p.setType(rs.getString("type"));
                myList.add(p);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return myList;
    }
    public void ajouter(stock a) {
        try {
            String requete = "INSERT INTO stock (id,type) VALUES (?,?)";
            PreparedStatement pst = cn2.prepareStatement(requete);
            pst.setInt(1, a.getId());
            pst.setString(2, a.getType());
          
            pst.executeUpdate();
            System.out.println("good");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
        public ObservableList<stock> afficher(stock A) throws SQLException {

        ObservableList<stock> arr = FXCollections.observableArrayList();
        st = cn2.createStatement();
        ResultSet rs = st.executeQuery("select * from stock");
        while (rs.next()) {
            arr.add(new stock(rs.getInt("id"),rs.getString("type")));

        }
        return arr;

    }
    
        
        
        
        
        public void modifier(stock A, int id) throws SQLException {

        try {
            if ((A.getType() != "")) {
                String query = "update stock set type='" + A.getType() + "' where stock.id=" + id;

                st = cn2.createStatement();
                st.executeUpdate(query);

                System.out.println("bien modifiée");

            } else {
                System.out.println("tu dois inserer tous les elements");
            }
        } catch (SQLException ex) {

        }

    }

        
        
          public void supprimer(int id) throws SQLException {

        st = cn2.createStatement();
        String q = "delete from stock where id= " + id;
        PreparedStatement pre = cn2.prepareStatement(q);
        st.executeUpdate(q);
        System.out.println("tu as bien supprimé");

    }
            public ObservableList<stock> rechercheCours(String recherche) throws SQLException {
                   stock p = new stock();

        ObservableList<stock> list = FXCollections.observableArrayList();
        String requete = "select type from stock   WHERE type LIKE '%"+recherche+"%'    ";
        try {
            PreparedStatement ps = cn2.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
        
                p.setType(rs.getString("type"));
         
              
             
             
list.add(p);
 
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

         
}