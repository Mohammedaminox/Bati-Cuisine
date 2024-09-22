package com.batiCuisine.controller;

import com.batiCuisine.model.Materiau;
import com.batiCuisine.service.MateriauService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MateriauController {
    private MateriauService materiauService;
    private Scanner scanner;

    public MateriauController(MateriauService materiauService) {
        this.materiauService = materiauService;
        this.scanner = new Scanner(System.in);
    }

    public List<Materiau> addMateriaux(int composantId, int id, String nom, double tauxTVA) throws SQLException {
        List<Materiau> materiaux = new ArrayList<>();
        String addMore;

        do {
            // Get user inputs for the specific material details
            System.out.print("Entrez le nom du matériau : ");
            String nomMateriel = scanner.nextLine();
            System.out.print("Entrez le coût unitaire du matériau : ");
            double coutUnitaire = scanner.nextDouble();
            System.out.print("Entrez la quantité : ");
            double quantite = scanner.nextDouble();
            System.out.print("Entrez le coût de transport : ");
            double coutTransport = scanner.nextDouble();
            System.out.print("Entrez le coefficient de qualité : ");
            double coefficientQualite = scanner.nextDouble();
            scanner.nextLine();

            // Create a new Materiau instance
            Materiau materiau = new Materiau(id, nomMateriel, coutUnitaire, quantite, tauxTVA, coutTransport, coefficientQualite);

            // Add materiau to the service (and presumably to the database)
            materiauService.addMateriau(materiau, composantId);

            // Add the created materiau to the list
            materiaux.add(materiau);
            System.out.println("Matériau ajouté avec succès !");

            // Ask if the user wants to add more materials
            System.out.print("Voulez-vous ajouter un autre matériau ? (y/n) : ");
            addMore = scanner.nextLine();
        } while (addMore.equalsIgnoreCase("y"));

        return materiaux;
    }
}
