package com.batiCuisine.model;

import java.time.LocalDate;

public class Devis {
    // Fields
    private int id;
    private double montantEstime;
    private LocalDate dateEmission;
    private LocalDate dateValidite;
    private boolean accepte;

    // Constructor
    public Devis(int id, double montantEstime, LocalDate dateEmission, LocalDate dateValidite, boolean accepte) {
        this.id = id;
        this.montantEstime = montantEstime;
        this.dateEmission = dateEmission;
        this.dateValidite = dateValidite;
        this.accepte = accepte;
    }

    // Getters
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
