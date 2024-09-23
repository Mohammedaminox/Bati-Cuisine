package com.batiCuisine.repository;

import com.batiCuisine.config.DatabaseConfig;
import com.batiCuisine.model.MainDoeuvre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MainDoeuvreRepository {

    public void insertMainDoeuvre(MainDoeuvre mainDoeuvre, int composantId) throws SQLException {
        String mainDoeuvreQuery = "INSERT INTO main_doeuvre(nom, taux_horaire, heures_travail, productivite_ouvrier, type_composant, taux_tva) VALUES(?,?,?,?,?,?)";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(mainDoeuvreQuery)) {

            preparedStatement.setString(1, mainDoeuvre.getNom());
            preparedStatement.setDouble(2, mainDoeuvre.getTauxHoraire());
            preparedStatement.setDouble(3, mainDoeuvre.getHeuresTravail());
            preparedStatement.setDouble(4, mainDoeuvre.getProductiviteOuvrier());
            preparedStatement.setString(5, mainDoeuvre.getTypeComposant().name());
            preparedStatement.setDouble(6, mainDoeuvre.getTauxTVA());

            preparedStatement.executeUpdate();
        }
    }
}
