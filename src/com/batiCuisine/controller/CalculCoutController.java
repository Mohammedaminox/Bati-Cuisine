package com.batiCuisine.controller;

import com.batiCuisine.model.MainDoeuvre;
import com.batiCuisine.model.Materiau;
import com.batiCuisine.repository.ComposantRepository;
import com.batiCuisine.repository.ProjetRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CalculCoutController {

    private ComposantRepository composantRepository;
    private ProjetRepository projetRepository;


    public CalculCoutController(ComposantRepository composantRepository, ProjetRepository projetRepository) {
        this.composantRepository = composantRepository;
        this.projetRepository = projetRepository;
    }


    public double calculerCout(List<Materiau> materiaux, List<MainDoeuvre> mainDoeuvres, String projetNom, String clientNom, String adresse, double surface, int projetId) throws SQLException {
        Scanner scanner = new Scanner(System.in);


        System.out.println("--- Calcul du coût total ---");

        // TVA
        System.out.print("Souhaitez-vous appliquer une TVA au projet ? (y/n) : ");
        String confirmeTVA = scanner.nextLine();
        double tauxTva = 0;
        if (confirmeTVA.equalsIgnoreCase("y")) {
            System.out.print("Entrez le pourcentage de TVA (%) : ");
            tauxTva = scanner.nextDouble();
            scanner.nextLine();
            composantRepository.setTva(projetId, tauxTva);
        }

        // Marge
        System.out.print("Souhaitez-vous appliquer une marge bénéficiaire au projet ? (y/n) : ");
        String confirmeMarge = scanner.nextLine();
        double margeBeneficiaire = 0;
        if (confirmeMarge.equalsIgnoreCase("y")) {
            System.out.print("Entrez le pourcentage de marge bénéficiaire (%) : ");
            margeBeneficiaire = scanner.nextDouble();
            scanner.nextLine();
            projetRepository.setMargeBeneficiaire(projetId, margeBeneficiaire);
        }

        System.out.println("Calcul du coût en cours...");

        // --- Détail des Coûts ---
        System.out.println("--- Résultat du Calcul ---");
        System.out.println("Nom du projet : " + projetNom);
        System.out.println("Client : " + clientNom);
        System.out.println("Adresse du chantier : " + adresse);
        System.out.println("Surface : " + surface + " m²");

        // 1. Matériaux
        System.out.println("--- Détail des Coûts ---");
        System.out.println("1. Matériaux :");
        double coutTotalMateriauxAvantTVA = 0;
        for (Materiau materiau : materiaux) {
            double coutMateriau = (materiau.getCoutUnitaire() * materiau.getQuantite()) * materiau.getCoefficientQualite() + materiau.getCoutTransport();
            coutTotalMateriauxAvantTVA += coutMateriau;
            System.out.println("- " + materiau.getNom() + " : " + coutMateriau + " € (Quantité : " + materiau.getQuantite() +
                    ", Coût unitaire : " + materiau.getCoutUnitaire() + " €/m², Transport : " + materiau.getCoutTransport() + " €)");

        }

        System.out.println("**Coût total des matériaux avant TVA : " + coutTotalMateriauxAvantTVA + " €**");

        double coutTotalMateriauxAvecTVA = coutTotalMateriauxAvantTVA + (coutTotalMateriauxAvantTVA * tauxTva / 100);
        System.out.println("**Coût total des matériaux avec TVA (" + tauxTva + "%) : " + coutTotalMateriauxAvecTVA + " €**");

        // 2. Main-d'œuvre
        System.out.println("2. Main-d'œuvre :");
        double coutTotalMainDoeuvreAvantTVA = 0;
        for (MainDoeuvre mainDoeuvre : mainDoeuvres) {
            double coutMainDoeuvre = mainDoeuvre.getTauxHoraire() * mainDoeuvre.getHeuresTravail() * mainDoeuvre.getProductiviteOuvrier();
            coutTotalMainDoeuvreAvantTVA += coutMainDoeuvre;
            System.out.println("- " + mainDoeuvre.getNom() + " : " + coutMainDoeuvre + " € (taux horaire : " + mainDoeuvre.getTauxHoraire() +
                    " €/h, heures travaillées : " + mainDoeuvre.getHeuresTravail() + ", productivité : " + mainDoeuvre.getProductiviteOuvrier() + ")");
        }


        System.out.println("**Coût total de la main-d'œuvre avant TVA : " + coutTotalMainDoeuvreAvantTVA + " €**");
        double coutTotalMainDoeuvreAvecTVA = coutTotalMainDoeuvreAvantTVA + (coutTotalMainDoeuvreAvantTVA * tauxTva / 100);
        System.out.println("**Coût total de la main-d'œuvre avec TVA (" + tauxTva + "%) : " + coutTotalMainDoeuvreAvecTVA + " €**");


        // Total before margin
        double coutTotalAvantMarge = coutTotalMateriauxAvecTVA + coutTotalMainDoeuvreAvecTVA;
        System.out.println("3. Coût total avant marge : " + coutTotalAvantMarge + " €");

        // Applying margin
        double montantMargeBeneficiaire = coutTotalAvantMarge * margeBeneficiaire / 100;
        System.out.println("4. Marge bénéficiaire (" + margeBeneficiaire + "%) : " + montantMargeBeneficiaire + " €");

        // Final total cost
        double coutTotalFinal = coutTotalAvantMarge + montantMargeBeneficiaire;
        System.out.println("**Coût total final du projet : " + coutTotalFinal + " €**");
        projetRepository.setCoutTotal(projetId, coutTotalFinal);

        return coutTotalFinal;

    }

}
