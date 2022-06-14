package com.garage.Models;

public class Voiture {

    private int id;
    private String marque;
    private String modele;
    private String telephone;
    private String matricule;
    private String proprietaire;


    public Voiture()
    {

    }

    public Voiture(int id, String marque, String modele, String telephone, String matricule, String proprietaire) {
        this.id = id;
        this.marque = marque;
        this.modele = modele;
        this.telephone = telephone;
        this.matricule = matricule;
        this.proprietaire = proprietaire;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String couleur) {
        this.telephone = couleur;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(String proprietaire) {
        this.proprietaire = proprietaire;
    }
}
