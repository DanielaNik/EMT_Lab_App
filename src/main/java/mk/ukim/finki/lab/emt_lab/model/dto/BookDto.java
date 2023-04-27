package mk.ukim.finki.lab.emt_lab.model.dto;

import lombok.Data;
import mk.ukim.finki.lab.emt_lab.model.Author;
import mk.ukim.finki.lab.emt_lab.model.Book;
import mk.ukim.finki.lab.emt_lab.model.enums.BookCategory;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

/**
 * A DTO for the {@link Book} entity
 */
@Data
public class BookDto implements Serializable {
    private final String name;
    @Enumerated(EnumType.STRING)
    private final BookCategory category;
    private final String authorName;
    private final String authorSurname;
    private final Integer availableCopies;

    public BookDto(String name, BookCategory category, String authorName, String authorSurname, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.authorName = authorName;
        this.authorSurname = authorSurname;
        this.availableCopies = availableCopies;
    }
}