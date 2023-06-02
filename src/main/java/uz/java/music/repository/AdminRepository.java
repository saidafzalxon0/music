package uz.java.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.java.music.entity.Admin;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {
    @Modifying
    @Query("UPDATE Admin a SET a.username = :username WHERE a.id = :id")
    void updateUsername(@Param("id") Long id, @Param("username") String username);
    @Modifying
    @Query("UPDATE Admin a SET a.password = :password WHERE a.id = :id")
    void updatePassword(@Param("id") Long id, @Param("password") String password);

    @Modifying
    @Query("DELETE FROM Admin a WHERE a.id = :id")
    void deleteAdmin(@Param("id") Long id);
    Optional<Admin> findFirstByUsername(String username);
}
