package com.batiCuisine.repository;

import com.batiCuisine.config.DatabaseConfig;

import java.sql.*;

public class ComposantRepository {
    

    public void setTva( int projetId, double tva) throws SQLException {
        String query = "UPDATE composants SET taux_tva = ? WHERE projet_id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDouble(1, tva);
            preparedStatement.setInt(2, projetId);
            preparedStatement.executeUpdate();
        }

    }
}
