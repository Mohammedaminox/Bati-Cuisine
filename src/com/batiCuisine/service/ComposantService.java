package com.batiCuisine.service;

import com.batiCuisine.model.Composant;
import com.batiCuisine.repository.ComposantRepository;

import java.sql.SQLException;

public class ComposantService {
    private ComposantRepository composantRepository;
    public ComposantService(ComposantRepository composantRepository) {
        this.composantRepository = composantRepository;
    }

    public void addComposant(Composant composant) throws SQLException {
        composantRepository.insertComposant(composant);

    }
}
