package com.batiCuisine.repository;

import com.batiCuisine.config.DatabaseConfig;
import com.batiCuisine.model.Composant;

import java.sql.*;

public class ComposantRepository {

    public int insertComposant(Composant composant) throws SQLException {
        String composantQuery = "INSERT INTO composants(nom, type_composant, taux_tva) VALUES(?,?,?)";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(composantQuery, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, composant.getNom());
            preparedStatement.setString(2, composant.getTypeComposant().name());
            preparedStatement.setDouble(3, composant.getTauxTVA());

            preparedStatement.executeUpdate();

            // Retrieve the generated key (composant_id)
            ResultSet composant_id = preparedStatement.getGeneratedKeys();
            if (composant_id.next()) {
                return composant_id.getInt(1);  // Return the generated ID for the inserted composant
            } else {
                throw new SQLException("Failed to insert composant, no ID obtained.");
            }
        }
    }
}
