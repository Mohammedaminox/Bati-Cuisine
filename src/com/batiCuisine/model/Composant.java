package com.batiCuisine.model;

public abstract class Composant {

    //fields
    protected int id;
    protected String nom;
    protected TypeComposant typeComposant;
    protected double tauxTVA;
    protected int projetId;


    //construsteur
    public Composant(int id, String nom, TypeComposant typeComposant, double tauxTVA, int projetId) {

        this.id = id;
        this.nom = nom;
        this.typeComposant = typeComposant;
        this.tauxTVA = tauxTVA;
        this.projetId = projetId;
    }


    //getters
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public TypeComposant getTypeComposant() {
        return typeComposant;
    }

    public double getTauxTVA() {
        return tauxTVA;
    }
    public int getProjetId() {
        return projetId;
    }
    public void setProjetId(int projetId) {
        this.projetId = projetId;
    }
}
