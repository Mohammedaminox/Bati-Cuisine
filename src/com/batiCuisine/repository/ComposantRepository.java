package com.batiCuisine.repository;

import com.batiCuisine.config.DatabaseConfig;
import com.batiCuisine.model.Composant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ComposantRepository {


    public void insertComposant(Composant composant) throws SQLException {
        String query = "INSERT INTO composants(nom, type_composant, taux_tva) VALUES(?,?,?)";
        try(Connection connection = DatabaseConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, composant.getNom());
            preparedStatement.setString(2, composant.getTypeComposant().name());
            preparedStatement.setDouble(3, composant.getTauxTVA());

            preparedStatement.executeUpdate();
        }
    }
}
