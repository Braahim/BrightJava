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

    public Campement(String libelle, String location, int capacity) {
        
        this.libelle = libelle;
        this.location = location;
        this.capacity = capacity;
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
}


