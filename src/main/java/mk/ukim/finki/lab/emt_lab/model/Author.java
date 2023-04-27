package mk.ukim.finki.lab.emt_lab.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    String surname;

    @ManyToOne(cascade=CascadeType.ALL)
    Country country;

    public Author(String name, String surname, Country country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
    }

    public Author(String name, String surname){
        this.name = name;
        this.surname = surname;
    }

    public Author() {

    }
}
