package com.batiCuisine.repository;

import com.batiCuisine.config.DatabaseConfig;
import com.batiCuisine.model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientRepository {

    public int insertClient(Client client) throws SQLException {
        String query = "INSERT INTO clients (nom, adresse, telephone, est_professionnel) VALUES (?, ?, ?, ?) RETURNING id";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, client.getNom());
            preparedStatement.setString(2, client.getAdresse());
            preparedStatement.setString(3, client.getTelephone());
            preparedStatement.setBoolean(4, client.isEstProfessionnel());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int clientId = resultSet.getInt(1);  // Get the generated client_id
                client.setId(clientId);  // Set the client ID in the Client object
                return clientId;
            } else {
                throw new SQLException("Failed to insert client, no ID obtained.");
            }


        }
    }

    public Client findClientByName(String clientName) throws SQLException {
        Client client = null;
        String query = "SELECT * FROM clients WHERE nom = ?"; // Corrected to match the field name in DB

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, clientName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String adresse = resultSet.getString("adresse");
                String telephone = resultSet.getString("telephone");
                boolean estProfessionnel = resultSet.getBoolean("est_professionnel");

                client = new Client(id, clientName, adresse, telephone, estProfessionnel);
            }
        }
        return client;
    }

}
