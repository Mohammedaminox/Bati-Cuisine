package com.batiCuisine.controller;

import com.batiCuisine.model.Client;
import com.batiCuisine.model.MainDoeuvre;
import com.batiCuisine.model.Materiau;
import com.batiCuisine.model.Projet;
import com.batiCuisine.service.ProjetService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ProjetController {
    private final ProjetService projetService;
    private final ClientController clientController;
    private final MateriauController materiauController;
    private final MainDoeuvreController mainDoeuvreController;
    private final CalculCoutController calculCoutController;
    private final DevisController devisController;
    private final Scanner scanner;


    public ProjetController(ProjetService projetService, ClientController clientController,
                            MateriauController materiauController, MainDoeuvreController mainDoeuvreController,
                            CalculCoutController calculCoutController, DevisController devisController) {
        this.projetService = projetService;
        this.clientController = clientController;
        this.materiauController = materiauController;
        this.mainDoeuvreController = mainDoeuvreController;
        this.calculCoutController = calculCoutController;
        this.devisController = devisController;

        this.scanner = new Scanner(System.in);
    }

    public void createProjet() throws SQLException {
        System.out.println("--- Recherche de client ---");
        System.out.println("Souhaitez-vous chercher un client existant ou en ajouter un nouveau ?");
        System.out.println("1. Chercher un client existant");
        System.out.println("2. Ajouter un nouveau client");
        System.out.print("Choisissez une option : ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Client client = null;

        switch (choice) {
            case 1:
                client = clientController.findClientByName();
                break;
            case 2:
                client = clientController.createClient();
                break;
            default:
                System.out.println("Choix invalide !");
                return;
        }

        if (client != null) {
            System.out.println("--- Création d'un Nouveau Projet ---");
            System.out.print("Entrez le nom du projet : ");
            String projectNom = scanner.nextLine();
            System.out.print("Entrez la surface de la cuisine (en m²) : ");
            double surfaceCuisine = scanner.nextDouble();
            scanner.nextLine();

            // Create the project instance
            Projet projet = new Projet(projectNom, surfaceCuisine, client);
            int projetId = projetService.addProjet(projet);  // This returns the generated ID

            System.out.println("--- Ajout des matériaux ---");
            List<Materiau> materiaux = materiauController.createMateriaux(0, 0, projetId);

            System.out.println("--- Ajout de la main-d'oeuvre ---");
            List<MainDoeuvre> mainDoeuvres = mainDoeuvreController.createMainDoeuvres(0, 0, projetId);

            calculCoutController.calculerCout(materiaux, mainDoeuvres, projectNom, client.getNom(),
                    client.getAdresse(), projet.getSurfaceCuisine(), projetId);

            devisController.createDevis(projetId);


        }
    }

    public void displayAllProjets() throws SQLException {
        List<Projet> projets = projetService.getAllProjets();
        if (projets.isEmpty()) {
            System.out.println("Aucun projet trouvé.");
        } else {
            System.out.println("--- Liste des Projets ---");
            for (Projet projet : projets) {
                System.out.println("Projet ID: " + projet.getIdProjet() +
                        ", Nom: " + projet.getNomProjet() +
                        ", Surface: " + projet.getSurfaceCuisine() + " m²" +
                        ", État: " + projet.getEtatProjet().name());
            }
        }
    }


}

