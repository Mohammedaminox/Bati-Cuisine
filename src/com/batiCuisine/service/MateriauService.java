package com.batiCuisine.service;

import com.batiCuisine.model.Materiau;
import com.batiCuisine.repository.MainDoeuvreRepository;
import com.batiCuisine.repository.MateriauRepository;

import java.sql.SQLException;

public class MateriauService {
    private MateriauRepository materiauRepository;
    public MateriauService(MateriauRepository materiauRepository) {
        this.materiauRepository = materiauRepository;
    }

    public void addMateriau(Materiau materiau) throws SQLException {
        materiauRepository.insertMateriau(materiau);
    }
}
