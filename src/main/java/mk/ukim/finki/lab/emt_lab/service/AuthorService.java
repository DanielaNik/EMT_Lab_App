package mk.ukim.finki.lab.emt_lab.service;

import mk.ukim.finki.lab.emt_lab.exceptions.NoSuchAuthorException;
import mk.ukim.finki.lab.emt_lab.model.Author;
import mk.ukim.finki.lab.emt_lab.model.dto.AuthorDto;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Optional<Author> findByNameAndSurname(String name, String surname);
    List<Author> findAll();
    Optional<Author> findById(Long id);

    Optional<Author> save(AuthorDto authorDto);
    Optional<Author> edit(Long id,AuthorDto authorDto) throws NoSuchAuthorException;
}
