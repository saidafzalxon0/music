package uz.java.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.java.music.entity.Position;
@Repository
public interface PositionRepository extends JpaRepository<Position,Long> {
    @Modifying
    @Query("DELETE FROM Position p WHERE p.id = :id")
    void deletePosition(@Param("id") Long id);
}
