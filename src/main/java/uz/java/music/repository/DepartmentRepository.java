package uz.java.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.java.music.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department,Long> {
    @Modifying
    @Query("DELETE FROM Department d where d.id = :id")
    void deleteDepartment(@Param("id") Long id);
}
