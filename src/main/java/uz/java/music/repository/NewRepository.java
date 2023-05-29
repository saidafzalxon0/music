package uz.java.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.java.music.entity.New;
@Repository

public interface NewRepository extends JpaRepository<New,Long> {
    @Modifying
    @Query("DELETE FROM New n where n.id = :id")
    void deleteNew(@Param("id") Long id);
}
