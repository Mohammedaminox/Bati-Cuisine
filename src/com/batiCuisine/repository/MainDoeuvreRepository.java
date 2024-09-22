package com.batiCuisine.repository;

import com.batiCuisine.config.DatabaseConfig;
import com.batiCuisine.model.MainDoeuvre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MainDoeuvreRepository {

    public void insertMainDoeuvre(MainDoeuvre mainDoeuvre, int composantId) throws SQLException {
        String mainDoeuvreQuery = "INSERT INTO main_doeuvre(composant_id, taux_horaire, heure_travail, productivite_ouvrier) VALUES(?,?,?,?)";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(mainDoeuvreQuery)) {

            preparedStatement.setInt(1, composantId);  // Link to the inserted composant id
            preparedStatement.setDouble(2, mainDoeuvre.getTauxHoraire());
            preparedStatement.setDouble(3, mainDoeuvre.getHeuresTravail());
            preparedStatement.setDouble(4, mainDoeuvre.getProductiviteOuvrier());

            preparedStatement.executeUpdate();
        }
    }
}
