package mk.ukim.finki.lab.emt_lab.repository;

import mk.ukim.finki.lab.emt_lab.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
    Optional<Author> findByNameAndSurname(String name, String surname);
    List<Author> findAll();
    Optional<Author> findById(Long id);
}
