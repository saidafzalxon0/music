package uz.java.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.java.music.entity.Aspirant;

@Repository
public interface AspirantRepository extends JpaRepository<Aspirant,Long> {
    @Modifying
    @Query("DELETE FROM Aspirant a WHERE a.id = :id")
    void deleteAspirant(@Param("id") Long id);
}
