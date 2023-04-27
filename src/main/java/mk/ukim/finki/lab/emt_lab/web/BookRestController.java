package mk.ukim.finki.lab.emt_lab.web;


import mk.ukim.finki.lab.emt_lab.exceptions.BookNotAvailableException;
import mk.ukim.finki.lab.emt_lab.exceptions.NoSuchAuthorException;
import mk.ukim.finki.lab.emt_lab.exceptions.NoSuchBookException;
import mk.ukim.finki.lab.emt_lab.model.Author;
import mk.ukim.finki.lab.emt_lab.model.Book;
import mk.ukim.finki.lab.emt_lab.model.dto.AuthorDto;
import mk.ukim.finki.lab.emt_lab.model.dto.BookDto;
import mk.ukim.finki.lab.emt_lab.model.enums.BookCategory;
import mk.ukim.finki.lab.emt_lab.service.AuthorService;
import mk.ukim.finki.lab.emt_lab.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/")
public class BookRestController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookRestController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    //Read
    @GetMapping({"/","/books"})
    public List<Book> findAll(Pageable pageable){
        return bookService.findAllWithPagination(pageable).getContent();
    }

    @GetMapping("/categories")
    public List<String> findAllCategories(){
        return Arrays.stream(BookCategory.values()).map(Enum::name).collect(Collectors.toList());
    }

    @GetMapping("/books/{id}")
    public Optional<Book> findById(@PathVariable Long id){
        return this.bookService.findById(id);
    }

    //Create
    @PostMapping("/books/add")
    public ResponseEntity<Book> save(@RequestBody BookDto bookDto) throws NoSuchAuthorException {
        return this.bookService.save(bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/authors/add")
    public ResponseEntity<Author> save(@RequestBody AuthorDto authorDto){
        return this.authorService.save(authorDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    //Update
    @PutMapping("/books/edit/{id}")
    public ResponseEntity<Book> edit(@PathVariable Long id,
                                     @RequestBody BookDto bookDto) throws NoSuchBookException, NoSuchAuthorException {

        return this.bookService.edit(id,bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    //Delete book
    @DeleteMapping("/books/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) throws NoSuchBookException {
        this.bookService.delete(id);
        if(bookService.findById(id).isEmpty()) {
           return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    //Borrow book
    @PostMapping("/books/borrow/{id}")
    public ResponseEntity<Book> borrowBook(@PathVariable Long id) throws NoSuchBookException, BookNotAvailableException {
        return this.bookService.borrowBook(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

}
