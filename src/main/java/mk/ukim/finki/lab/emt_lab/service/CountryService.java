package mk.ukim.finki.lab.emt_lab.service;

import mk.ukim.finki.lab.emt_lab.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    Optional<Country> save(String name, String continent);
    Optional<Country> findByName (String name);
    List<Country> findAll();
}
