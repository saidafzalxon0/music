package uz.java.music.service;

import org.springframework.http.ResponseEntity;
import uz.java.music.dto.AspirantDto;
import uz.java.music.dto.DepartmentDto;
import uz.java.music.dto.SubjectDto;

import java.util.List;

public interface DepartmentService {
    ResponseEntity<DepartmentDto> add(DepartmentDto dto);

    ResponseEntity<DepartmentDto> update(DepartmentDto dto);

    ResponseEntity<List<DepartmentDto>> getAll();

    ResponseEntity<DepartmentDto> getById(Long id);

    ResponseEntity<DepartmentDto> delete(Long id);
}
