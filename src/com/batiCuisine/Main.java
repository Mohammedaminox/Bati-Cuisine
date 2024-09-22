package com.batiCuisine;

import com.batiCuisine.controller.ClientController;
import com.batiCuisine.controller.MateriauController;
import com.batiCuisine.controller.ProjetController;
import com.batiCuisine.repository.ClientRepository;
import com.batiCuisine.repository.MateriauRepository;
import com.batiCuisine.repository.ProjetRepository;
import com.batiCuisine.service.ClientService;
import com.batiCuisine.service.MateriauService;
import com.batiCuisine.service.ProjetService;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        // Set up repository, service, and controller

        ProjetRepository projetRepository = new ProjetRepository();
        ClientRepository clientRepository = new ClientRepository();
        MateriauRepository materiauRepository = new MateriauRepository();


        ProjetService projetService = new ProjetService(projetRepository);
        ClientService clientService = new ClientService(clientRepository);
        MateriauService materiauService = new MateriauService(materiauRepository);


        ClientController clientController = new ClientController(clientService);
        MateriauController materiauController = new MateriauController(materiauService);

        ProjetController projetController = new ProjetController(projetService, clientController, materiauController);
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("=== Menu Principal ===");
            System.out.println("1. Créer un nouveau projet");
            System.out.println("2. Afficher les projets existants");
            System.out.println("3. Calculer le coût d'un projet");
            System.out.println("4. Quitter");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    projetController.createProjet();
                    break;

                case 2:

                    break;
                case 3:

                    break;

                case 4:
                    System.out.println("Quitter...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 4);

        scanner.close();
    }
}
