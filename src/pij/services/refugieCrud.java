/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pij.services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;
import pij.entities.Campement;
import pij.entities.Refugie;
import pij.utils.connectionDB;

/**
 *
 * @author Brahim
 */
public class refugieCrud {
        Connection cn2;
    Statement st;

    public refugieCrud() {
        cn2 =connectionDB.getInstance().getCnx();
    }
    
    public int addRefugie(Refugie c) {
        int st = 0;
        try {
            PreparedStatement pst;
            String requete2;
            requete2 = "INSERT INTO refuge (nom, prenom, nationalite, img, BirthD, birthLoc, sexe, socialSit)VALUES (?,?,?,?,?,?,?,?,?)";
            pst = cn2.prepareStatement(requete2);

            pst.setString(1, c.getNom());
            pst.setString(2, c.getPrenom());
            pst.setString(3, c.getNationality());
            pst.setString(4, c.getImg());
            pst.setDate(5, (Date) c.getBirthD());
            pst.setString(6, c.getBirthLoc());
            pst.setString(7, c.getSexe());
            pst.setString(8, c.getSocialSit());
            st = pst.executeUpdate();
            //cn2.close();

            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger("erreur dans l'ajout compement"+ex.getMessage());
        }
        return st;
    }
        public int deleteRefugie(String libelle) {
        
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
}
