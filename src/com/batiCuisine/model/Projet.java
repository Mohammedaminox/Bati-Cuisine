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
    public Projet(String nomProjet, double surfaceCuisine, Client client) {
        this.idProjet = idProjet;
        this.nomProjet = nomProjet;
        this.margeBeneficiaire = 0.0;
        this.coutTotal = 0.0;
        this.etatProjet = EtatProjet.EnCours;
        this.client = client;
        this.surfaceCuisine = surfaceCuisine;
    }

    //getters
    public int getIdProjet() {
        return idProjet;
    }

    public void setIdProjet(int idProjet) {
        this.idProjet = idProjet;
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
