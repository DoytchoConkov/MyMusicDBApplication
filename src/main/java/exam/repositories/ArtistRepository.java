package exam.repositories;

import exam.models.entities.Artist;
import exam.models.entities.ArtistName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<Artist,String> {
    Optional<Artist> findByName(ArtistName name);
}
