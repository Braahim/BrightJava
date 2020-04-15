/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pij.entities;

/**
 *
 * @author Brahim
 */
public class Campement {

    
    private String libelle;
    private String location;
    private int capacity;
    private String lat;
    private String lng;

    public Campement(String libelle, String location, int capacity, String lat, String lng) {
        
        this.libelle = libelle;
        this.location = location;
        this.capacity = capacity;
        this.lat = lat;
        this.lng = lng;
    }

    public Campement(String libelle) {
        this.libelle = libelle;
    }

    public Campement() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "Campement{" + "libelle=" + libelle + ", location=" + location + ", capacity=" + capacity + ", lat=" + lat + ", lng=" + lng + '}';
    }





    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}


