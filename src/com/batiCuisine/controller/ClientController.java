package com.batiCuisine.controller;

import com.batiCuisine.model.Client;
import com.batiCuisine.service.ClientService;

import java.sql.SQLException;
import java.util.Scanner;

public class ClientController {
    private ClientService clientService;
    private Scanner scanner;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
        this.scanner = new Scanner(System.in);
    }

    public Client findClientByName() throws SQLException {
        System.out.print("Entrez le nom du client : ");
        String clientName = scanner.nextLine();

        Client client = clientService.findClientByName(clientName);

        if (client != null) {
            System.out.println("Client trouvé !");
            System.out.println("Nom : " + client.getNom());
            System.out.println("Adresse : " + client.getAdresse());
            System.out.println("Numéro de téléphone : " + client.getTelephone());

            System.out.print("Souhaitez-vous continuer avec ce client ? (y/n) : ");
            String confirmation = scanner.nextLine().trim();
            if (confirmation.equalsIgnoreCase("y")) {
                return client;
            }
        } else {
            System.out.println("Client non trouvé !");
        }

        return null;
    }

    public Client createClient() throws SQLException {
        System.out.print("Entrez le nom du client : ");
        String nom = scanner.nextLine();
        System.out.print("Entrez l'adresse : ");
        String adresse = scanner.nextLine();
        System.out.print("Entrez le numéro de téléphone : ");
        String telephone = scanner.nextLine();
        System.out.print("Est-ce un client professionnel ? (true/false) : ");
        boolean estProfessionnel = scanner.nextBoolean();
        scanner.nextLine(); // Consume newline

        Client newClient = new Client(0, nom, adresse, telephone, estProfessionnel);
        clientService.addClient(newClient);

        System.out.println("Client ajouté avec succès !");
        return newClient;
    }
}
