package com.batiCuisine.service;

import com.batiCuisine.model.Devis;
import com.batiCuisine.repository.DevisRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DevisService {
    private DevisRepository devisRepository;

    public DevisService(DevisRepository devisRepository) {
        this.devisRepository = new DevisRepository();
    }

    public void addDevis(Devis devis, int projetId) throws SQLException {
        devisRepository.insertDevis(devis, projetId);
    }

}
