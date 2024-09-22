package com.batiCuisine.repository;

import com.batiCuisine.config.DatabaseConfig;
import com.batiCuisine.model.Materiau;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.batiCuisine.model.TypeComposant;

public class MateriauRepository {

    public void insertMateriau(Materiau materiau, int composantId) throws SQLException {
        String materiauQuery = "INSERT INTO materiau(composant_id, nom, cout_unitaire, quantite, cout_transport, coefficient_qualite, type_composant, taux_tva) VALUES(?,?,?,?,?,?,?,?)";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(materiauQuery)) {

            preparedStatement.setInt(1, composantId);  // Link to the inserted composant id
            preparedStatement.setString(2, materiau.getNom());
            preparedStatement.setDouble(3, materiau.getCoutUnitaire());
            preparedStatement.setDouble(4, materiau.getQuantite());  // Assuming quantity is an integer
            preparedStatement.setDouble(5, materiau.getCoutTransport());
            preparedStatement.setDouble(6, materiau.getCoefficientQualite());
            preparedStatement.setString(7, materiau.getTypeComposant().name());
            preparedStatement.setDouble(8, materiau.getTauxTVA());

            preparedStatement.executeUpdate();
        }
    }
}

