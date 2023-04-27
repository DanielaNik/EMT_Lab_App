package mk.ukim.finki.lab.emt_lab.model.dto;

import lombok.Data;
import mk.ukim.finki.lab.emt_lab.model.Country;

@Data
public class AuthorDto {
    private String name;
    private String surname;
    private Country country;

    public AuthorDto(String name, String surname, Country country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
    }
}
