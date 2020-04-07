/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pij.entities;

import java.util.Date;

/**
 *
 * @author Brahim
 */
public class Refugie {
     //private int id;
    private String nom;
    private String prenom;
    private String nationality;
    private String img;
    private String addDate;
    private Date birthD;
    private String birthLoc;
    private String sexe;
    private String socialSit;
    private Campement camp;



    public Refugie() {
        
    }
    public Refugie(String nom, String prenom, String nationality, String img, Date birthD, String birthLoc, String sexe) {
        
        this.nom = nom;
        this.prenom = prenom;
        this.nationality = nationality;
        this.img = img;
        this.addDate = new Date().toString();
      
        this.birthD = birthD;
        this.birthLoc = birthLoc;
        this.sexe = sexe;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getAddDate() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

    public Date getBirthD() {
        return birthD;
    }

    public void setBirthD(Date birthD) {
        this.birthD = birthD;
    }

    public String getBirthLoc() {
        return birthLoc;
    }

    public void setBirthLoc(String birthLoc) {
        this.birthLoc = birthLoc;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getSocialSit() {
        return socialSit;
    }

    public void setSocialSit(String socialSit) {
        this.socialSit = socialSit;
    }
    
}
