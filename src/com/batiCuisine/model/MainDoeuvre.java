package com.batiCuisine.model;

public class MainDoeuvre extends Composant{

    //fields
    private double tauxHoraire;
    private double heuresTravail;
    private double productiviteOuvrier;

    //constructeur
    public MainDoeuvre(int id, String nom, double tauxTVA, double tauxHoraire, double heuresTravail, double productiviteOuvrier) {
        super(id,nom,TypeComposant.MainDoeuvre,tauxTVA);
        this.tauxHoraire = tauxHoraire;
        this.heuresTravail = heuresTravail;
        this.productiviteOuvrier = productiviteOuvrier;

    }


    //getters
    public double getTauxHoraire() {
        return tauxHoraire;
    }

    public double getHeuresTravail() {
        return heuresTravail;
    }

    public double getProductiviteOuvrier() {
        return productiviteOuvrier;
    }
}
