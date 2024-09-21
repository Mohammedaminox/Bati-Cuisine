package com.batiCuisine.service;

import com.batiCuisine.model.Projet;
import com.batiCuisine.repository.ProjetRepository;

import java.sql.SQLException;

public class ProjetService {
    private ProjetRepository projetRepository;

    public ProjetService(ProjetRepository projetRepository) {
        this.projetRepository = projetRepository;
    }

    // Create a new projet

    // Create a new project
    public void addProjet(Projet projet) throws SQLException {
        projetRepository.insertProjet(projet);
    }
}
