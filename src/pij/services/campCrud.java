/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pij.services;

import pij.utils.connectionDB;
import pij.entities.Campement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import pij.utils.JSON_Reader;

/**
 *
 * @author Brahim
 */
public class campCrud {
    Connection cn2;
    Statement st;
    JSON_Reader jsonR = new JSON_Reader();

    public campCrud() {
        cn2 =connectionDB.getInstance().getCnx();
    }
    
    public int addCampement(Campement c) {
        int st = 0;
        try {
            PreparedStatement pst;
            String requete2;
            requete2 = "INSERT INTO camp (libelle, location, capacity, lat, lng)VALUES (?,?,?,?,?)";
            pst = cn2.prepareStatement(requete2);

            pst.setString(1, c.getLibelle());
            pst.setString(2, c.getLocation());
            pst.setInt(3, c.getCapacity());
            pst.setString(4, c.getLat());
            pst.setString(5, c.getLng());
            st = pst.executeUpdate();
            //cn2.close();

           // pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger("erreur dans l'ajout compement"+ex.getMessage());
        }
        return st;
    }
        public int deleteCampement(String libelle) {
        
            int st = 0;
            try {

            String reqDel = "DELETE FROM camp WHERE libelle=? ";
            PreparedStatement pst = cn2.prepareStatement(reqDel);
            pst.setString(1, libelle);
            st = pst.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
            return st;
    }
        public int updateCampement(Campement C){
    int st=0;
    try{
        String  sql =" UPDATE camp SET capacity=? WHERE libelle=?";
       

     
     PreparedStatement stat = cn2.prepareStatement(sql);
     stat= cn2.prepareStatement(sql);
     stat.setInt(1,C.getCapacity());
     stat.setString(2,C.getLibelle());
     st= stat.executeUpdate();
     System.out.println("Test");
       //con.close();
        }catch (SQLException e){
        e.printStackTrace();
    }
    return st;
    
}
        public Campement getCamp(String lib) throws SQLException{
           String query = "select * from camp where libelle  LIKE?";  
           List camps = new LinkedList();
          
            PreparedStatement ps = cn2.prepareStatement(query);
            ps.setString(1,lib);
            ResultSet rs = ps.executeQuery();
while (rs.next()){
       Campement c = new Campement();
       camps.add(new Campement(rs.getString("libelle"), rs.getString("location"),rs.getInt("capacity"), rs.getString("lat"), rs.getString("lng")));
}
ps.close();
return (Campement) camps.get(0);
}
}
