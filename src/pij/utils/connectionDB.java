/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pij.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Brahim
 */
public class connectionDB {
       public static PreparedStatement prepareStatement(String sql) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    String url = "jdbc:mysql://localhost:3306/refugees";
    Connection cnx; 
    String Login="root"; 
    String Password="" ; 
    static connectionDB con ; 
    
   public connectionDB() {
       try {
       
     cnx =DriverManager.getConnection(url,Login,Password);
     System.out.println("connected") ; 
       } catch (SQLException ex) {
                      System.out.println(ex.getMessage()) ; 

       }
       
    }
   public Connection getCnx() {
       return cnx ; 
   }
   public static connectionDB getInstance()  {
       if (con==null) {
           con=new connectionDB() ; 
       }
     return con ; 
       
   }
}
