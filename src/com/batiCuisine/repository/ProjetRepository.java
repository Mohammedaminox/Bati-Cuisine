package com.batiCuisine.repository;

import com.batiCuisine.config.DatabaseConfig;
import com.batiCuisine.model.EtatProjet;
import com.batiCuisine.model.Projet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjetRepository {


    // Insert a new project into the database
    public int insertProjet(Projet projet) throws SQLException {
        String query = "INSERT INTO projets (nom_projet, marge_beneficiaire, cout_total, etat_projet, client_id, surface_cuisine) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            // Set the parameters
            preparedStatement.setString(1, projet.getNomProjet());
            preparedStatement.setDouble(2, projet.getMargeBeneficiaire());
            preparedStatement.setDouble(3, projet.getCoutTotal());
            preparedStatement.setString(4, projet.getEtatProjet().name());  // Convert EtatProjet to string
            preparedStatement.setInt(5, projet.getClient().getId());  // Assuming Client has a getId() method
            preparedStatement.setDouble(6, projet.getSurfaceCuisine());

            // Execute the query
            preparedStatement.executeUpdate();

            // Get the generated project ID from the database
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int generatedId = generatedKeys.getInt(1);  // Get the generated ID
                projet.setIdProjet(generatedId);
                return generatedId;
            } else {
                throw new SQLException("Failed to insert project, no ID obtained.");
            }
        }
    }

    public List<Projet> findAll() throws SQLException {
        List<Projet> projets = new ArrayList<>();
        String query = "SELECT * FROM projets";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Projet projet = new Projet(
                        resultSet.getString("nom_projet"),
                        resultSet.getDouble("surface_cuisine"),
                        null // Replace this with actual Client retrieval if needed
                );
                projet.setIdProjet(resultSet.getInt("id"));
                projet.setMargeBeneficiaire(resultSet.getDouble("marge_beneficiaire"));
                projet.setCoutTotal(resultSet.getDouble("cout_total"));
                projet.setEtatProjet(EtatProjet.valueOf(resultSet.getString("etat_projet")));
                projets.add(projet);
            }
        }
        return projets;
    }



    public void setMargeBeneficiaire(int projetId, double margeBeneficiaire) throws SQLException {
        String query = "UPDATE projets SET marge_beneficiaire = ? WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDouble(1, margeBeneficiaire);
            preparedStatement.setInt(2, projetId);
            preparedStatement.executeUpdate();
        }
    }

    public void setCoutTotal(int projetId, double coutTotal) throws SQLException {
        String query = "UPDATE projets SET cout_total = ? WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDouble(1, coutTotal);
            preparedStatement.setInt(2, projetId);
            preparedStatement.executeUpdate();
        }
    }



}


