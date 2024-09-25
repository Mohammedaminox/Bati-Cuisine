package com.batiCuisine.service;

import com.batiCuisine.model.Client;
import com.batiCuisine.repository.ClientRepository;

import java.sql.SQLException;

public class ClientService {

    private ClientRepository clientRepository;


    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client findClientByName(String clientName) throws SQLException {
        return clientRepository.findClientByName(clientName); // Implement this method to search by name
    }

    public void addClient(Client client) throws SQLException {
        clientRepository.insertClient(client);
    }
}
