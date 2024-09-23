package com.batiCuisine.service;

import com.batiCuisine.model.Projet;
import com.batiCuisine.repository.ProjetRepository;

import java.sql.SQLException;

public class ProjetService {
    private ProjetRepository projetRepository;

    public ProjetService(ProjetRepository projetRepository) {
        this.projetRepository = projetRepository;
    }

    // Create a new project
    public int addProjet(Projet projet) throws SQLException {
        return projetRepository.insertProjet(projet);  // Return the ID generated by the repository
    }
}
