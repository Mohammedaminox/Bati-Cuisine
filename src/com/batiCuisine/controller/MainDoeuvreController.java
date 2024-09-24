package com.batiCuisine.controller;

import com.batiCuisine.model.MainDoeuvre;
import com.batiCuisine.service.MainDoeuvreService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainDoeuvreController {
    private final MainDoeuvreService mainDoeuvreService;
    private final Scanner scanner;

    public MainDoeuvreController(MainDoeuvreService mainDoeuvreService) {
        this.mainDoeuvreService = mainDoeuvreService;
        this.scanner = new Scanner(System.in);
    }

    public List<MainDoeuvre> createMainDoeuvres(int composantId, double tauxTVA, int projetId) throws SQLException {
        List<MainDoeuvre> mainDoeuvres = new ArrayList<>();
        String addMore;

        do {
            // Ask user for details of Main d'oeuvre
            System.out.print("Entrez le type de main-d'œuvre (e.g., Ouvrier de base, Spécialiste) : ");
            String nomMainDoeuvre = scanner.nextLine();

            System.out.print("Entrez le taux horaire de cette main-d'œuvre : ");
            double tauxHoraire = scanner.nextDouble();

            System.out.print("Entrez le nombre d'heures travaillées : ");
            double heuresTravail = scanner.nextDouble();

            System.out.print("Entrez le facteur de productivité : ");
            double productiviteOuvrier = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            // Create new MainDoeuvre instance
            MainDoeuvre mainDoeuvre = new MainDoeuvre(composantId, nomMainDoeuvre, tauxTVA, tauxHoraire, heuresTravail, productiviteOuvrier, projetId);

            mainDoeuvre.setProjetId(projetId);
            // Add mainDoeuvre to the service (and presumably to the database)
            mainDoeuvreService.addMainDoeuvre(mainDoeuvre); // Method to handle insertion with composant ID

            // Add the created mainDoeuvre to the list
            mainDoeuvres.add(mainDoeuvre);
            System.out.println("MainDoeuvre ajouté avec succès !");

            // Ask if the user wants to add more main d'oeuvre
            System.out.print("Voulez-vous ajouter un autre type de main-d'œuvre ? (y/n) : ");
            addMore = scanner.nextLine();
        } while (addMore.equalsIgnoreCase("y"));

        return mainDoeuvres;
    }
}
