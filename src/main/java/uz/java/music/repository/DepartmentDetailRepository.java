package uz.java.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.java.music.dto.DepartmentEmployeeDto;
import uz.java.music.dto.SubjectAndDirectionDto;
import uz.java.music.entity.DepartmentDetail;

import java.util.List;
@Repository

public interface DepartmentDetailRepository extends JpaRepository<DepartmentDetail,Long> {
    @Modifying
    @Query("DELETE FROM DepartmentDetail d where d.id = :id")
    void deleteDepartmentDetail(@Param("id") Long id);


    List<DepartmentDetail> findAllByDepartment_Id(Long id);

    @Query(value = "select department_detail.id,subject.name as subject_name,direction.name as direction_name from department_detail join subject on department_detail.subject_id = subject.id join direction on department_detail.direction_id = direction.id where department_detail.department_id = :id;",nativeQuery = true)
    List<SubjectAndDirectionDto> getSubjectAndDirection(@Param("id") Long id);

    @Query(value = "select d.department_id,d.full_name,d.about,file.link from (select d.department_id,e.full_name,e.about,e.file_id from department_detail d join employee e on d.employee_id = e.id where d.department_id = :id) as d join file on d.file_id = file.id;", nativeQuery = true)
    List<DepartmentEmployeeDto> getDepartmentEmployee(@Param("id") Long id);
    }
