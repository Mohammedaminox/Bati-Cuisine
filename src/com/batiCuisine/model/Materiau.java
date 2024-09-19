package com.batiCuisine.model;

public class Materiau extends Composant {

    //fields
    private double coutUnitaire;
    private double quantite;
    private double coutTransport;
    private double coefficientQualite;



    //constructeur
    public Materiau(int id, String nom, double coutUnitaire, double quantite, double tauxTVA, double coutTransport, double coefficientQualite) {
        super(id, nom, "Materiel", tauxTVA);
        this.coutUnitaire = coutUnitaire;
        this.quantite = quantite;
        this.coutTransport = coutTransport;
        this.coefficientQualite = coefficientQualite;

    }


    //getters
    public double getCoutUnitaire() {
        return coutUnitaire;
    }

    public double getQuantite() {
        return quantite;
    }

    public double getCoutTransport() {
        return coutTransport;
    }

    public double getCoefficientQualite() {
        return coefficientQualite;
    }




}
