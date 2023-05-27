package uz.java.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.java.music.entity.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
