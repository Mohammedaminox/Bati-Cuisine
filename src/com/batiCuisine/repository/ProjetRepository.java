package com.batiCuisine.repository;

import com.batiCuisine.config.DatabaseConfig;
import com.batiCuisine.model.Projet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProjetRepository {


    // Insert a new project into the database
    public void insertProjet(Projet projet) throws SQLException {
        String query = "INSERT INTO projets (nom_projet, marge_benficiaire, cout_total, etat_projet, client_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Set the parameters
            preparedStatement.setString(1, projet.getNomProjet());
            preparedStatement.setDouble(2, projet.getMargeBeneficiaire());
            preparedStatement.setDouble(3, projet.getCoutTotal());
            preparedStatement.setString(4, projet.getEtatProjet().name());  // Convert EtatProjet to string
            preparedStatement.setInt(5, projet.getClient().getId());  // Assuming Client has a getId() method

            // Execute the query
            preparedStatement.executeUpdate();
        }
    }



}


