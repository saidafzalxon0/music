package uz.java.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.java.music.dto.SubjectAndDirectionDto;
import uz.java.music.entity.DepartmentDetail;

import java.util.List;

public interface DepartmentDetailRepository extends JpaRepository<DepartmentDetail,Long> {
    @Modifying
    @Query("DELETE FROM DepartmentDetail d where d.id = :id")
    void deleteDepartmentDetail(@Param("id") Long id);


    List<DepartmentDetail> findAllByDepartment_Id(Long id);

    @Query(value = "select department_detail.id,subject.name as subject_name,direction.name as direction_name from department_detail join subject on department_detail.subject_id = subject.id join direction on department_detail.direction_id = direction.id where department_detail.department_id = :id;",nativeQuery = true)
    List<SubjectAndDirectionDto> getSubjectAndDirection(@Param("id") Long id);
}
