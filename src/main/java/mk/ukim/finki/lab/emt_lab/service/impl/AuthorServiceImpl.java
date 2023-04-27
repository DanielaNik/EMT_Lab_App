package mk.ukim.finki.lab.emt_lab.service.impl;

import mk.ukim.finki.lab.emt_lab.exceptions.NoSuchAuthorException;
import mk.ukim.finki.lab.emt_lab.model.Author;
import mk.ukim.finki.lab.emt_lab.model.Country;
import mk.ukim.finki.lab.emt_lab.model.dto.AuthorDto;
import mk.ukim.finki.lab.emt_lab.repository.AuthorRepository;
import mk.ukim.finki.lab.emt_lab.repository.CountryRepository;
import mk.ukim.finki.lab.emt_lab.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }


    @Override
    public Optional<Author> findByNameAndSurname(String name, String surname) {
       return this.authorRepository.findByNameAndSurname(name,surname);
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public Optional<Author> save(AuthorDto authorDto) {
        Country country = null;
        if (countryRepository.findByName(authorDto.getCountry().getName()).isEmpty()) {
            country = new Country(authorDto.getCountry().getName(), authorDto.getCountry().getContinent());
            countryRepository.save(country);
        }
        else country = authorDto.getCountry();

        Author author = new Author(authorDto.getName(), authorDto.getSurname(), country);
        authorRepository.save(author);

        return Optional.of(author);
    }

    @Override
    public Optional<Author> edit(Long id, AuthorDto authorDto) throws NoSuchAuthorException {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new NoSuchAuthorException(authorDto.getName(),authorDto.getSurname()));

        author.setName(authorDto.getName());
        author.setSurname(authorDto.getSurname());
        author.setCountry(authorDto.getCountry());
        authorRepository.save(author);

        return Optional.of(author);
    }
}
