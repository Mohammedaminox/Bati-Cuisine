package com.batiCuisine.model;

public abstract class Composant {

    //fields
    protected int id;
    protected String nom;
    protected TypeComposant typeComposant;
    protected double tauxTVA;


    //construsteur
    public Composant(int id, String nom, TypeComposant typeComposant, double tauxTVA) {

        this.id = id;
        this.nom = nom;
        this.typeComposant = typeComposant;
        this.tauxTVA = tauxTVA;
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
}
