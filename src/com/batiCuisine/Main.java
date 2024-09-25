package com.batiCuisine;

import com.batiCuisine.controller.*;
import com.batiCuisine.repository.*;
import com.batiCuisine.service.*;

import java.sql.SQLException; // Import the SQLException
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Set up repositories, services, and controllers
        ProjetRepository projetRepository = new ProjetRepository();
        ClientRepository clientRepository = new ClientRepository();
        MateriauRepository materiauRepository = new MateriauRepository();
        MainDoeuvreRepository mainDoeuvreRepository = new MainDoeuvreRepository();
        ComposantRepository composantRepository = new ComposantRepository();
        DevisRepository devisRepository = new DevisRepository();

        ProjetService projetService = new ProjetService(projetRepository);
        ClientService clientService = new ClientService(clientRepository);
        MateriauService materiauService = new MateriauService(materiauRepository);
        MainDoeuvreService mainDoeuvreService = new MainDoeuvreService(mainDoeuvreRepository);
        DevisService devisService = new DevisService(devisRepository);

        ClientController clientController = new ClientController(clientService);
        MateriauController materiauController = new MateriauController(materiauService);
        MainDoeuvreController mainDoeuvreController = new MainDoeuvreController(mainDoeuvreService);
        CalculCoutController calculCoutController = new CalculCoutController(composantRepository, projetRepository);
        DevisController devisController = new DevisController(devisService, projetService);

        ProjetController projetController = new ProjetController(projetService, clientController, materiauController, mainDoeuvreController, calculCoutController, devisController);

        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (true) {
            System.out.println("=== Menu Principal ===");
            System.out.println("1. Créer un nouveau projet");
            System.out.println("2. Afficher les projets existants");
            System.out.println("3. Quitter");
            System.out.print("Entrez votre choix: ");

            // Input validation
            try {
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        projetController.createProjet();
                        break;
                    case 2:
                        projetController.displayAllProjets();
                        break;

                    case 3:
                        System.out.println("Quitter...");
                        scanner.close();
                        return; // Exit the main method
                    default:
                        System.out.println("Choix invalide. Veuillez réessayer.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur: Veuillez entrer un nombre entier.");
                scanner.nextLine();
            } catch (SQLException e) {
                System.out.println("Erreur de base de données: " + e.getMessage());
            }
        }
    }
}
