/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pij.utils;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
/**
 *
 * @author Brahim
 */

public class JSON_Reader
{
    String[] govName = null;
    List<String> stream = new ArrayList<String>();
    JSONParser parser = new JSONParser();
    //Object object;
    /**
     *
     * @return
     */
    
    
    
    public JSON_Reader(){
        
    }

    public List<String> govList() {
        try
        {
            Object object = parser.parse(new FileReader("C:\\Users\\mehdi\\Documents\\NetBeansProjects\\BrightJava\\src\\pij\\views\\ressources\\tn.json"));
            
            //convert Object to JSONObject
            JSONArray govList = (JSONArray) object;
            //   List<String> stream = new ArrayList<>();
            
            govList.forEach( (emp) -> stream.add(parseGovObject((JSONObject) emp, (String) "city")));
            
            System.out.println("test");
            
            
            
            
        }
        catch(FileNotFoundException fe)
        {
            fe.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return stream;
    }
    
    public String getAtrr(String attr,String ville){
         String s = null;
         List<String> str = new ArrayList<>();
         try
        {
            Object object = parser.parse(new FileReader("C:\\Users\\mehdi\\Documents\\NetBeansProjects\\BrightJava\\src\\pij\\views\\ressources\\tn.json"));
            
            //convert Object to JSONObject
            JSONArray govList = (JSONArray) object;
            
            for (Object o : govList)
            {
               JSONObject obj = (JSONObject) o;
               JSONObject ob = (JSONObject) obj.get("governorates");
               //String tmp = (String) ob.get("city");
               String tmp1 = (String) ob.get(attr);
               //System.out.println(tmp1);
               if (ob.get("city").equals(ville)){
                   //System.out.println(tmp1);
                   s=tmp1;
               }
               
            }
            
             
            
        
            
           
           // System.out.println(b);
            
            
            
            
            
            
            
        }
        catch(FileNotFoundException fe)
        {
            fe.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
       //return ;
       return s;
    }
    
    private static String parseGovObject(JSONObject gov,String name) {
        JSONObject cityObj = (JSONObject) gov.get("governorates");
         String Name = (String) cityObj.get(name);    
         //stream.add(Name);
         return Name;
       
    }
    private static JSONObject parseGovLoc(JSONObject gov,String attr){
        return (JSONObject) gov.get(attr);
            }
 
    
}
