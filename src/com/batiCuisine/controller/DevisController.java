package com.batiCuisine.controller;

import com.batiCuisine.model.Devis;
import com.batiCuisine.model.Projet;
import com.batiCuisine.service.DevisService;
import com.batiCuisine.service.ProjetService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class DevisController {
    private DevisService devisService;
    private ProjetService projetService;  // Add the ProjetService to retrieve project details
    private Scanner scanner;

    public DevisController(DevisService devisService, ProjetService projetService) { // Add projetService to constructor
        this.devisService = devisService;
        this.projetService = projetService;
        this.scanner = new Scanner(System.in);
    }

    public void createDevis(int projetId) throws SQLException {
        // Retrieve the project based on the provided projetId
        Projet projet = projetService.getProjetById(projetId); // Assuming projetService provides this method

        // Use the coutTotal from the project as montantEstime
        double montantEstime = projet.getCoutTotal();
        System.out.println("Le coût total du projet utilisé pour le devis est : " + montantEstime + " €");

        // Get date inputs from the user
        System.out.print("Entrez la date d'émission du devis (format : jj/mm/aaaa) : ");
        String dateEmissionInput = scanner.nextLine();
        LocalDate dateEmission = LocalDate.parse(dateEmissionInput, java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        System.out.print("Entrez la date de validité du devis (format : jj/mm/aaaa) : ");
        String dateValiditeInput = scanner.nextLine();
        LocalDate dateValidite = LocalDate.parse(dateValiditeInput, java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        // Default value for 'accepte' is set to false
        boolean accepte = false;

        // Confirm if the user wants to save the quote
        System.out.print("Souhaitez-vous enregistrer le devis ? (y/n) : ");
        String confirmeDevis = scanner.nextLine();

        if (confirmeDevis.equalsIgnoreCase("y")) {
            // Create a new Devis instance and save it using the service
            Devis newDevis = new Devis(0, montantEstime, dateEmission, dateValidite, accepte);
            devisService.addDevis(newDevis, projetId);
            System.out.println("Devis enregistré avec succès !");
        } else {
            System.out.println("Enregistrement du devis annulé.");
        }
    }
}
