package uz.java.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.java.music.entity.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    @Modifying
    @Query("DELETE FROM Event e where e.id = :id")
    void deleteEvent(@Param("id") Long id);
}
