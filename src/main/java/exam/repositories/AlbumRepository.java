package exam.repositories;

import exam.models.entities.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlbumRepository extends JpaRepository<Album,String> {
    @Query("select sum(a.copies) from Album as a")
    Long getAllCopies();
    @Query("SELECT a from Album as a ORDER BY a.copies DESC ")
    List<Album> findAll();
}
