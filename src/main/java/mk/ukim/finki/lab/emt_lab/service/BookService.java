package mk.ukim.finki.lab.emt_lab.service;

import mk.ukim.finki.lab.emt_lab.exceptions.BookNotAvailableException;
import mk.ukim.finki.lab.emt_lab.exceptions.NoSuchAuthorException;
import mk.ukim.finki.lab.emt_lab.exceptions.NoSuchBookException;
import mk.ukim.finki.lab.emt_lab.model.Book;
import mk.ukim.finki.lab.emt_lab.model.dto.BookDto;
import mk.ukim.finki.lab.emt_lab.model.enums.BookCategory;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();

    Optional<Book> findById(Long id);
    Optional<Book> save(String name, BookCategory bookCategory, String authorName, String authorSurname, Integer copies) throws NoSuchAuthorException;
    Optional<Book> save(BookDto bookDto) throws NoSuchAuthorException;

    Optional<Book> edit(Long id, String name, BookCategory bookCategory, String authorName, String authorSurname, Integer copies) throws NoSuchBookException, NoSuchAuthorException;
    Optional<Book> edit(Long id, BookDto bookDto) throws NoSuchBookException, NoSuchAuthorException;

    Optional<Book> delete (Long id) throws NoSuchBookException;

    Optional<Book> borrowBook(Long id) throws NoSuchBookException, BookNotAvailableException;

    Page<Book> findAllWithPagination(Pageable pageable);
}
