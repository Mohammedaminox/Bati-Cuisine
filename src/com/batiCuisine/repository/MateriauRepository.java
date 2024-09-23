package com.batiCuisine.repository;

import com.batiCuisine.config.DatabaseConfig;
import com.batiCuisine.model.Materiau;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.batiCuisine.model.TypeComposant;

public class MateriauRepository {

    public void insertMateriau(Materiau materiau) throws SQLException {
        String materiauQuery = "INSERT INTO materiau(nom, cout_unitaire, quantite, cout_transport, coefficient_qualite, type_composant, taux_tva) VALUES(?,?,?,?,?,?,?)";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(materiauQuery)) {

//            preparedStatement.setInt(1, composantId);  // Link to the inserted composant id
            preparedStatement.setString(1, materiau.getNom());
            preparedStatement.setDouble(2, materiau.getCoutUnitaire());
            preparedStatement.setDouble(3, materiau.getQuantite());  // Assuming quantity is an integer
            preparedStatement.setDouble(4, materiau.getCoutTransport());
            preparedStatement.setDouble(5, materiau.getCoefficientQualite());
            preparedStatement.setString(6, materiau.getTypeComposant().name());
            preparedStatement.setDouble(7, materiau.getTauxTVA());

            preparedStatement.executeUpdate();
        }
    }
}

