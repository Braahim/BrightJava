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
 * @author HP
 */
public class don_crud {
    Connection cn2;
    Statement st;

    public don_crud() {
        cn2 = MyConnection.getInstance().getCnx();
    }
 
     public ObservableList<don> displayALLDon() {
        ObservableList<don> myList = FXCollections.observableArrayList();
        try {
            String req ="select d.*,s.type from don d INNER JOIN stock s on d.Stock_id = s.id ";
            Statement pst = cn2.createStatement();
            ResultSet rs = pst.executeQuery(req);
            while (rs.next()) {
                don d = new don();
                d.setReference(rs.getInt("reference"));
                d.setLibelle(rs.getString("libelle"));
                d.setQuantite(rs.getInt("quantite"));
                d.setDate(rs.getDate("date"));
                d.setStock_id(rs.getInt("Stock_id"));
                d.setSto(rs.getString("type"));
                myList.add(d);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return myList;
    }
    public void ajouter(don a) {
        try {
            String requete = "INSERT INTO don (reference,libelle,quantite,date,Stock_id) VALUES (?,?,?,?,?)";
            PreparedStatement pst = cn2.prepareStatement(requete);
            pst.setInt(1, a.getReference());
            pst.setString(2, a.getLibelle());
            pst.setInt(3, a.getQuantite());
            pst.setDate(4, a.getDate());
            pst.setInt(5, a.getStock_id());
            pst.executeUpdate();
            System.out.println("don ajouter avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

   



        public ObservableList<don> afficher(don A) throws SQLException {

        ObservableList<don> arr = FXCollections.observableArrayList();
         
        st = cn2.createStatement();
        ResultSet rs = st.executeQuery("select * from don ");

        while (rs.next()) {
        
            arr.add(new don(rs.getInt("reference"),rs.getString("libelle"), rs.getInt("quantite"), rs.getDate("date"), rs.getInt("Stock_id")));

        }
         
              
        return arr;

    }


    public void supprimer(int id) throws SQLException {

        st = cn2.createStatement();
        String q = "delete from don where reference= " + id;
        PreparedStatement pre = cn2.prepareStatement(q);
        st.executeUpdate(q);
        System.out.println("tu as bien supprimé");

    }

    
    public void modifier(don A, int id) throws SQLException {

        try {
          if ((A.getQuantite() != 0) && (A.getLibelle() != "")  && (A.getStock_id() != 0) ) {
              String query = "update don set quantite='" + A.getQuantite() + "',libelle='" + A.getLibelle() + "',date='" + A.getDate() + "',Stock_id='" + A.getStock_id() + "' where reference=" + id;
              st = cn2.createStatement();
              st.executeUpdate(query);
              System.out.println("bien modifiée");

            } else {
                System.out.println("tu dois inserer tous les elements");
            }
           }catch (SQLException ex) {

            }

    }
    
               public ObservableList<don> rechercheDon(String recherche) throws SQLException {
                   don p = new don();

        ObservableList<don> list = FXCollections.observableArrayList();
        String requete = "select r.*,c.type from don r INNER JOIN stock c on r.Stock_id = c.id  WHERE  libelle LIKE '%"+recherche+"%' OR quantite < '%"+recherche+"%'   ";
        try {
            PreparedStatement ps = cn2.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                p.setReference(rs.getInt("reference"));
                  p.setLibelle(rs.getString("libelle"));
                    p.setQuantite(rs.getInt("quantite"));
               
              
                p.setDate(rs.getDate("date"));
                p.setStock_id(rs.getInt("Stock_id"));
              
               
                    p.setSto(rs.getString("type"));
                list.add(p);
 
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }
                public ObservableList<don> rechercheDons(String recherche) throws SQLException {
                   don p = new don();

        ObservableList<don> list = FXCollections.observableArrayList();
        String requete = "select r.*,c.type from don r INNER JOIN stock c on r.Stock_id = c.id  WHERE quantite LIKE '%"+recherche+"%'   ";
        try {
            PreparedStatement ps = cn2.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                p.setReference(rs.getInt("reference"));
                  p.setLibelle(rs.getString("libelle"));
                    p.setQuantite(rs.getInt("quantite"));
               
              
                p.setDate(rs.getDate("date"));
                p.setStock_id(rs.getInt("Stock_id"));
              
               
                    p.setSto(rs.getString("type"));
                list.add(p);
 
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }
     
     
}
