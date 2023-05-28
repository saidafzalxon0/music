package uz.java.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.java.music.entity.Direction;

@Repository
public interface DirectionRepository extends JpaRepository<Direction, Long> {
    @Modifying
    @Query("DELETE FROM Direction d WHERE d.id = :id")
    void deleteDirection(@Param("id") Long id);
}
