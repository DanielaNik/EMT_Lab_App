package mk.ukim.finki.lab.emt_lab.repository;

import mk.ukim.finki.lab.emt_lab.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {

    Optional<Country> findByName (String name);

    List<Country> findAll();

}
