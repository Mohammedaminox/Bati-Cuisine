package com.batiCuisine.model;

public class Client {

    //fields
    private int id;
    private String nom;
    private String adresse;
    private String telephone;
    private boolean estProfessionnel;


    //constructeur
    public Client(int id, String nom, String adresse, String telephone, boolean estProfessionnel) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.estProfessionnel = estProfessionnel;
    }


    //getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public boolean isEstProfessionnel() {
        return estProfessionnel;
    }
}
