/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pij.entities;

import java.sql.Date;
import java.util.Objects;


/**
 *
 * @author Med Amir Jday
 */
public class Demande {
    
     private int idDemande;
    private String titreDem;
    private String descDem;
    private double montantDem;
    private Date delaiFinal;

    public Demande(String titreDem, String descDem, double montantDem, Date delaiFinal) {
        this.titreDem = titreDem;
        this.descDem = descDem;
        this.montantDem = montantDem;
        this.delaiFinal = delaiFinal;
    }
    
      public Demande(int idDemande,String titreDem, String descDem, double montantDem, Date delaiFinal) {
        this.idDemande=idDemande;
        this.titreDem = titreDem;
        this.descDem = descDem;
        this.montantDem = montantDem;
        this.delaiFinal = delaiFinal;
    }
    
    public Demande(){
    }

    public int getIdDemande() {
        return idDemande;
    }

    public void setIdDemande(int idDemande) {
        this.idDemande = idDemande;
    }

    public String getTitreDem() {
        return titreDem;
    }

    public void setTitreDem(String titreDem) {
        this.titreDem = titreDem;
    }

    public String getDescDem() {
        return descDem;
    }

    public void setDescDem(String descDem) {
        this.descDem = descDem;
    }

    public double getMontantDem() {
        return montantDem;
    }

    public void setMontantDem(double montantDem) {
        this.montantDem = montantDem;
    }

    public Date getDelaiFinal() {
        return delaiFinal;
    }

    public void setDelaiFinal(Date delaiFinal) {
        this.delaiFinal = delaiFinal;
    }

    @Override
    public String toString() {
        return "Demande{" + "idDemande=" + idDemande + ", titreDem=" + titreDem + ", descDem=" + descDem + ", montantDem=" + montantDem + ", delaiFinal=" + delaiFinal + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.idDemande;
        hash = 59 * hash + Objects.hashCode(this.titreDem);
        hash = 59 * hash + Objects.hashCode(this.descDem);
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.montantDem) ^ (Double.doubleToLongBits(this.montantDem) >>> 32));
        hash = 59 * hash + Objects.hashCode(this.delaiFinal);
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
        final Demande other = (Demande) obj;
        if (this.idDemande != other.idDemande) {
            return false;
        }
        if (Double.doubleToLongBits(this.montantDem) != Double.doubleToLongBits(other.montantDem)) {
            return false;
        }
        if (!Objects.equals(this.titreDem, other.titreDem)) {
            return false;
        }
        if (!Objects.equals(this.descDem, other.descDem)) {
            return false;
        }
        if (!Objects.equals(this.delaiFinal, other.delaiFinal)) {
            return false;
        }
        return true;
    }
    
}
