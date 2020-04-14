/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pij.services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pij.entities.Demande;
import pij.utils.connectionDB;

/**
 *
 * @author Med Amir Jday
 */
public class serviceDemande {
    
    private Connection con;
    private Statement ste;
    
    public serviceDemande() {
        con = connectionDB.getInstance().getCnx();
    }
    
    public List<Demande> afficherDemande() throws SQLException {
        
    List<Demande> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from demande");
    
     while (rs.next()) {                
         
              int idDemande=rs.getInt(1);
              String titreDem=rs.getString(2);
              String descDem=rs.getString(3);
              Double montantDem=rs.getDouble(4);
              Date delaifinal=rs.getDate(5);
                     
              Demande a = new Demande (idDemande,titreDem,descDem,montantDem,delaifinal);
              arr.add(a);
        }
        
        return arr;
    }
    
    public void ajouterDemande(Demande p) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("INSERT INTO `refugees`.`demande` ('titreDem','descDem','montantDem','delaiFinal') VALUES (?, ?, ?,?);");
      
    pre.setString(1, p.getTitreDem());
    pre.setString(2, p.getDescDem());
    pre.setDouble(3, p.getMontantDem());
    pre.setDate(4, p.getDelaiFinal());
    
    pre.executeUpdate();
    }
    
    
        public boolean supprimerDemande(Demande t) throws SQLException {
            
            ste = con.createStatement();
            String requeteDelete = "DELETE FROM `demande` WHERE idDemande = '"+ t.getIdDemande() +"'";
          
        
            if(ste.executeUpdate(requeteDelete) == 1)
                {
                    System.out.println("Cette demande a été supprimé avec succès");
                    return true;   
                }
            else 
                {
                    System.out.println("Cette demande n'existe pas");
                    return false;
                }
        }
        
        public boolean modifierDemande(Demande t) throws SQLException {
       
            ste = con.createStatement();
            String requeteDelete = "UPDATE `medecin` SET `titreDem`= '"+ t.getTitreDem()+"',`descDem`= '"+ t.getDescDem()+"',`montantDem`= '"+ t.getMontantDem()+ "',`delaiFinal`= '"+ t.getDelaiFinal()+"' WHERE id = '"+ t.getIdDemande() +"' ";
          
        
                if(ste.executeUpdate(requeteDelete) == 1)
                    {
                        System.out.println("La demande a été mise à jour !");
                        return true;   
                    }
                else 
                    {
                        System.out.println("Cette demande n'existe pas");
                        return false;
                    }
        }
       
}
