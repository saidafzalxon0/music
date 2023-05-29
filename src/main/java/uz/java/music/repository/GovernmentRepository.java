package uz.java.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.java.music.entity.Government_service;
@Repository

public interface GovernmentRepository extends JpaRepository<Government_service,Long> {
    @Modifying
    @Query("UPDATE Government_service g SET g.link = :link WHERE g.id = :id")
    void updateLink(@Param("id") Long id, @Param("link") String link);

    @Modifying
    @Query("DELETE FROM Government_service g WHERE g.id = :id")
    void deleteAdmin(@Param("id") Long id);
}
