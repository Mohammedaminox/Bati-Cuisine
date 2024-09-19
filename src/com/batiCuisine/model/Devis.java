package com.batiCuisine.model;

import java.time.LocalDate;

public class Devis {

    //fields
    private int id;
    private double montantEstime;
    private LocalDate dateEmission;
    private LocalDate dateValidite;
    private boolean accepte;

    //constructeur
    public Devis(int id, double montantEstime, LocalDate dateEmission, LocalDate dateValidite, boolean accepte) {
        this.id = id;
        this.montantEstime = montantEstime;
        this.dateEmission = dateEmission;
        this.dateValidite = dateValidite;
        this.accepte = accepte;

    }

    //getters
    public int getId() {
        return id;
    }

    public double getMontantEstime() {
        return montantEstime;
    }

    public LocalDate getDateEmission() {
        return dateEmission;
    }

    public LocalDate getDateValidite() {
        return dateValidite;
    }

    public boolean isAccepte() {
        return accepte;
    }
}
