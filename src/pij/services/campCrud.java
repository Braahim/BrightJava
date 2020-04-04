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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

/**
 *
 * @author Brahim
 */
public class campCrud {
    Connection cn2;
    Statement st;

    public campCrud() {
        cn2 =connectionDB.getInstance().getCnx();
    }
    
    public int addCampement(Campement c) {
        int st = 0;
        try {
            PreparedStatement pst;
            String requete2;
            requete2 = "INSERT INTO camp (libelle, location, capacity)VALUES (?,?,?)";
            pst = cn2.prepareStatement(requete2);

            pst.setString(1, c.getLibelle());
            pst.setString(2, c.getLocation());
            pst.setInt(3, c.getCapacity());
            st = pst.executeUpdate();
            cn2.close();

            pst.executeUpdate();
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
        public static int update(Campement C){
    int st=0;
    try{
        String  sql =" UPDATE camp SET capacity=? WHERE libelle=?";
       

     Connection con=connectionDB.getInstance().getCnx();
     PreparedStatement stat;
     stat= con.prepareStatement(sql);
     stat.setInt(1,C.getCapacity());
     stat.setString(2,C.getLibelle());
     st= stat.executeUpdate();
        con.close();
        }catch (SQLException e){
        e.printStackTrace();
    }
    return st;
    
}
    
}
