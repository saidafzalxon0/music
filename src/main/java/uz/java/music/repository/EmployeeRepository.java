package uz.java.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.java.music.dto.EmployeeDto;
import uz.java.music.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Modifying
    @Query("DELETE FROM Employee e WHERE e.id = :id")
    void deleteEmployee(@Param("id") Long id);
}
