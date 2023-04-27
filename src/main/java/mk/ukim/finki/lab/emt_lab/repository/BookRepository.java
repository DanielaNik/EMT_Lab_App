package mk.ukim.finki.lab.emt_lab.repository;

import mk.ukim.finki.lab.emt_lab.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    Optional<Book> findById (Long id);

    List<Book> findAll();

    Page<Book> findAll(Pageable pageable);
}
