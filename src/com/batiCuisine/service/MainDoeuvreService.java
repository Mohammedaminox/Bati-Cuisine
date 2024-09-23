package com.batiCuisine.service;

import com.batiCuisine.model.MainDoeuvre;
import com.batiCuisine.repository.MainDoeuvreRepository;

import java.sql.SQLException;

public class MainDoeuvreService {
    private MainDoeuvreRepository mainDoeuvreRepository;

    public MainDoeuvreService(MainDoeuvreRepository mainDoeuvreRepository) {
        this.mainDoeuvreRepository = mainDoeuvreRepository;
    }

    public void addMainDoeuvre(MainDoeuvre mainDoeuvre) throws SQLException{
        mainDoeuvreRepository.insertMainDoeuvre(mainDoeuvre);
    }
}
