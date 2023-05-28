package uz.java.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.java.music.entity.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    @Modifying
    @Query("DELETE FROM Subject s WHERE s.id = :id")
    void deleteSubject(@Param("id") Long id);
}
