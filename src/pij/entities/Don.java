/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pij.entities;

import java.util.Objects;

/**
 *
 * @author Med Amir Jday
 */
public class Don {
    
    private int idDon;
    private double montantDon;
    private String dateDon;

    public Don(double montantDon, String dateDon) {
        this.montantDon = montantDon;
        this.dateDon = dateDon;
    }
    
    public Don(double montantDon) {
        this.montantDon = montantDon;
     
    }

    public Don(){
    }

      public Don(int idDon,double montantDon, String dateDon) {
        this.montantDon = montantDon;
        this.dateDon = dateDon;
        this.idDon = idDon;
    }
    
    
    public int getIdDon() {
        return idDon;
    }

    public void setIdDon(int idDon) {
        this.idDon = idDon;
    }

    public double getMontantDon() {
        return montantDon;
    }

    public void setMontantDon(double montantDon) {
        this.montantDon = montantDon;
    }

    public String getDateDon() {
        return dateDon;
    }

    public void setDateDon(String dateDon) {
        this.dateDon = dateDon;
    }

    @Override
    public String toString() {
        return "Don{" + "idDon=" + idDon + ", montantDon=" + montantDon + ", dateDon=" + dateDon + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.idDon;
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.montantDon) ^ (Double.doubleToLongBits(this.montantDon) >>> 32));
        hash = 89 * hash + Objects.hashCode(this.dateDon);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Don other = (Don) obj;
        if (this.idDon != other.idDon) {
            return false;
        }
        if (Double.doubleToLongBits(this.montantDon) != Double.doubleToLongBits(other.montantDon)) {
            return false;
        }
        if (!Objects.equals(this.dateDon, other.dateDon)) {
            return false;
        }
        return true;
    }
    
    
    
}
