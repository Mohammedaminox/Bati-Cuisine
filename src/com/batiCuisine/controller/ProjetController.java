package com.batiCuisine.controller;

import com.batiCuisine.model.Client;
import com.batiCuisine.model.Projet;
import com.batiCuisine.service.ClientService;
import com.batiCuisine.service.ProjetService;

import java.sql.SQLException;
import java.util.Scanner;

public class ProjetController {
    private ProjetService projetService;
    private ClientController clientController;
    private Scanner scanner;

    public ProjetController(ProjetService projetService, ClientController clientController) {
        this.projetService = projetService;
        this.clientController = clientController;
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
                client = clientController.addClient();
                break;
            default:
                System.out.println("Choix invalide !");
                return;
        }

        if (client != null) {
            System.out.println("--- Création d'un Nouveau Projet ---");
            System.out.print("Entrez le nom du projet : ");
            String projectName = scanner.nextLine();
            // You might need to add surface or other attributes to the project
//            Projet projet = new Projet(); // Initialize with required attributes

//            // Add materials and labor
//            addMaterialsToProjet(projet);
//            addLaborToProjet(projet);
//
//            // Calculate total cost and possibly ask for VAT and margin
//            calculateAndDisplayCost(projet);
//
//            // Optionally save the project
//            saveProjet(projet, client);
        }
    }


}
