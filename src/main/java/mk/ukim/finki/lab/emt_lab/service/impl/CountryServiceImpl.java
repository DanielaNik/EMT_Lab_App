package mk.ukim.finki.lab.emt_lab.service.impl;

import mk.ukim.finki.lab.emt_lab.model.Country;
import mk.ukim.finki.lab.emt_lab.repository.CountryRepository;
import mk.ukim.finki.lab.emt_lab.service.CountryService;

import java.util.List;
import java.util.Optional;

public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Optional<Country> save(String name, String continent) {
        return Optional.of(countryRepository.save(new Country(name,continent)));
    }

    @Override
    public Optional<Country> findByName(String name) {
        return countryRepository.findByName(name);
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }
}
