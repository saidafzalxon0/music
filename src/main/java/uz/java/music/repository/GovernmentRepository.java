package uz.java.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.java.music.entity.Government_service;

public interface GovernmentRepository extends JpaRepository<Government_service,Long> {
}
