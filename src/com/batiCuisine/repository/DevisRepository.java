package com.batiCuisine.repository;

import com.batiCuisine.config.DatabaseConfig;
import com.batiCuisine.model.Devis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DevisRepository {


    public void insertDevis(Devis devis, int projetId) throws SQLException {
        String query = "INSERT INTO devis (montant_estime, date_emission, date_validite, accepte, projet_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setDouble(1, devis.getMontantEstime());
            preparedStatement.setDate(2, java.sql.Date.valueOf(devis.getDateEmission())); // Convert LocalDate to java.sql.Date
            preparedStatement.setDate(3, java.sql.Date.valueOf(devis.getDateValidite())); // Convert LocalDate to java.sql.Date
            preparedStatement.setBoolean(4, devis.isAccepte());
            preparedStatement.setInt(5, projetId);
            preparedStatement.executeUpdate();
        }
    }


}
