package com.batiCuisine.model;

public class Projet {

    //fields
    private int idProjet;
    private String nomProjet;
    private double margeBeneficiaire;
    private double coutTotal;
    private EtatProjet etatProjet;
    private Client client;
    private double surfaceCuisine;

    //constructeur
    public Projet(int idProjet, String nomProjet, double margeBeneficiaire, double coutTotal, EtatProjet etatProjet, Client client, double surfaceCuisine) {
        this.idProjet = idProjet;
        this.nomProjet = nomProjet;
        this.margeBeneficiaire = margeBeneficiaire;
        this.coutTotal = coutTotal;
        this.etatProjet = etatProjet;
        this.client = client;
        this.surfaceCuisine = surfaceCuisine;
    }

    //getters
    public int getIdProjet() {
        return idProjet;
    }

    public String getNomProjet() {
        return nomProjet;
    }

    public double getMargeBeneficiaire() {
        return margeBeneficiaire;
    }

    public double getCoutTotal() {
        return coutTotal;
    }

    public EtatProjet getEtatProjet() {
        return etatProjet;
    }
    public Client getClient() {
        return client;
    }
    public double getSurfaceCuisine() {
        return surfaceCuisine;
    }
}
