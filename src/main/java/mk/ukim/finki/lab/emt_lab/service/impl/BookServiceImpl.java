package mk.ukim.finki.lab.emt_lab.service.impl;

import mk.ukim.finki.lab.emt_lab.exceptions.BookNotAvailableException;
import mk.ukim.finki.lab.emt_lab.exceptions.NoSuchAuthorException;
import mk.ukim.finki.lab.emt_lab.exceptions.NoSuchBookException;
import mk.ukim.finki.lab.emt_lab.model.Author;
import mk.ukim.finki.lab.emt_lab.model.Book;
import mk.ukim.finki.lab.emt_lab.model.dto.BookDto;
import mk.ukim.finki.lab.emt_lab.model.enums.BookCategory;
import mk.ukim.finki.lab.emt_lab.repository.AuthorRepository;
import mk.ukim.finki.lab.emt_lab.repository.BookRepository;
import mk.ukim.finki.lab.emt_lab.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAll() {
       return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> save(String name, BookCategory bookCategory, String authorName, String authorSurname, Integer copies) throws NoSuchAuthorException {
        Author author = authorRepository.findByNameAndSurname(authorName,authorSurname)
                .orElseThrow(() -> new NoSuchAuthorException(authorName,authorSurname));
        Book book = new Book(name,bookCategory,author,copies);
        bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> save(BookDto bookDto) throws NoSuchAuthorException {
        Author author = authorRepository.findByNameAndSurname(bookDto.getAuthorName(),bookDto.getAuthorSurname())
                .orElseThrow(() -> new NoSuchAuthorException(bookDto.getAuthorName(),bookDto.getAuthorSurname()));
        Book book = new Book(bookDto.getName(),bookDto.getCategory(),author,bookDto.getAvailableCopies());
        bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> edit(Long id, String name, BookCategory bookCategory, String authorName, String authorSurname, Integer copies) throws NoSuchBookException, NoSuchAuthorException {
        Book book = bookRepository.findById(id).orElseThrow(() -> new NoSuchBookException(id));
        Author author = authorRepository.findByNameAndSurname(authorName,authorSurname)
                .orElseThrow(() -> new NoSuchAuthorException(authorName,authorSurname));
        book.setName(name);
        book.setCategory(bookCategory);
        book.setAuthor(author);
        book.setAvailableCopies(copies);
        bookRepository.save(book);

        return Optional.of(book);
    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) throws NoSuchBookException, NoSuchAuthorException {
        Book book = bookRepository.findById(id).orElseThrow(() -> new NoSuchBookException(id));
        Author author = authorRepository.findByNameAndSurname(bookDto.getAuthorName(),bookDto.getAuthorSurname())
                .orElseThrow(() -> new NoSuchAuthorException(bookDto.getAuthorName(),bookDto.getAuthorSurname()));

        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());
        book.setAuthor(author);
        book.setAvailableCopies(bookDto.getAvailableCopies());
        bookRepository.save(book);

        return Optional.of(book);
    }

    @Override
    public Optional<Book> delete(Long id) throws NoSuchBookException {
        Book book = bookRepository.findById(id).orElseThrow(() -> new NoSuchBookException(id));
        bookRepository.delete(book);
        return Optional.of(book);
    }

    @Override
    @Transactional
    public Optional<Book> borrowBook(Long id) throws NoSuchBookException, BookNotAvailableException {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NoSuchBookException(id));
        int copies = book.getAvailableCopies();

        if(copies > 0)
            book.setAvailableCopies(copies-1);
        else
            throw new BookNotAvailableException(id);

        return Optional.of(book);
    }

    @Override
    public Page<Book> findAllWithPagination(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }
}
