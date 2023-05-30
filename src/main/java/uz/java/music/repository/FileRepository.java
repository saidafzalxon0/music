package uz.java.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.java.music.entity.File;

import java.util.List;
@Repository

public interface FileRepository extends JpaRepository<File,Long> {

    @Modifying
    @Query(value = "DELETE FROM File f WHERE f.id = :id",nativeQuery = true)
    void deleteFile(@Param("id") Long id);

    List<File> findAllByExt(String name);
}
