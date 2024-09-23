package com.batiCuisine.repository;

import com.batiCuisine.config.DatabaseConfig;
import com.batiCuisine.model.Projet;

import java.sql.*;

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



}


