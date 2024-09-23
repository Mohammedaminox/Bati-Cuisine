    package com.batiCuisine.controller;

    import com.batiCuisine.model.Client;
    import com.batiCuisine.model.MainDoeuvre;
    import com.batiCuisine.model.Materiau;
    import com.batiCuisine.model.Projet;
    import com.batiCuisine.service.ClientService;
    import com.batiCuisine.service.ProjetService;

    import java.sql.SQLException;
    import java.util.List;
    import java.util.Scanner;

    public class ProjetController {
        private ProjetService projetService;
        private ClientController clientController;
        private MateriauController materiauController;
        private MainDoeuvreController mainDoeuvreController;
        private Scanner scanner;

        public ProjetController(ProjetService projetService, ClientController clientController, MateriauController materiauController, MainDoeuvreController mainDoeuvreController) {
            this.projetService = projetService;
            this.clientController = clientController;
            this.materiauController = materiauController;
            this.mainDoeuvreController = mainDoeuvreController;
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
                String projectName = scanner.nextLine();
                System.out.print("Entrez la surface de la cuisine (en m²) : ");
                double surfaceCuisine = scanner.nextDouble();

                // Create the project instance
                Projet projet = new Projet(projectName, surfaceCuisine, client);

                int projetId = projetService.addProjet(projet);  // This returns the generated ID

                System.out.println("--- Ajout des matériaux ---");

                List<Materiau> materiaux = materiauController.createMateriaux(0, 0,projetId);

                System.out.println("--- Ajout de la main-d'oeuvre ---");

                List<MainDoeuvre> mainDoeuvres = mainDoeuvreController.createMainDoeuvres(0, 0,projetId);

                System.out.println("--- Calcul du coût total ---");

                System.out.print("Souhaitez-vous appliquer une TVA au projet ? (y/n) : ");
                String comfirmeTVA = scanner.nextLine();
                double tauxTva = 0;
                if (comfirmeTVA.equalsIgnoreCase("y")) {
                    System.out.print("Entrez le pourcentage de TVA (%) : ");
                    tauxTva = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                }

                System.out.print("Souhaitez-vous appliquer une marge bénéficiaire au projet ? (y/n) : ");
                String comfirmeMarge = scanner.nextLine();
                double margeBeneficiaire = 0;
                if (comfirmeMarge.equalsIgnoreCase("y")) {
                    System.out.print("Entrez le pourcentage de marge bénéficiaire (%) : ");
                    margeBeneficiaire = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline

                }


            }
        }


    }
