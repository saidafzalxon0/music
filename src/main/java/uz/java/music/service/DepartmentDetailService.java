package uz.java.music.service;

import org.springframework.http.ResponseEntity;
import uz.java.music.dto.DepartmentDetailDto;
import uz.java.music.dto.DepartmentDto;
import uz.java.music.dto.SubjectAndDirectionDto;

import java.util.List;

public interface DepartmentDetailService {
    ResponseEntity<DepartmentDetailDto> add(DepartmentDetailDto dto);

    ResponseEntity<DepartmentDetailDto> update(DepartmentDetailDto dto);

    ResponseEntity<List<DepartmentDetailDto>> getAll();

    ResponseEntity<List<DepartmentDetailDto>> getById(Long id);

    ResponseEntity<DepartmentDetailDto> delete(Long id);

    ResponseEntity<List<SubjectAndDirectionDto>> getSubjectAndDirection(Long id);
}
