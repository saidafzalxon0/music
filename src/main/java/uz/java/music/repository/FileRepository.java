package uz.java.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.java.music.entity.File;

public interface FileRepository extends JpaRepository<File,Long> {

    @Modifying
    @Query("DELETE FROM File f WHERE f.id = :id")
    void deleteFile(@Param("id") Long id);
}
