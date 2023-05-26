package uz.java.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.java.music.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin,Long> {
}
