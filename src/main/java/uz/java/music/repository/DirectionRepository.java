package uz.java.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.java.music.entity.Direction;

public interface DirectionRepository extends JpaRepository<Direction, Long> {
}
