package uz.java.music.service;

import org.springframework.http.ResponseEntity;
import uz.java.music.dto.AspirantDto;
import uz.java.music.dto.DepartmentDto;
import uz.java.music.dto.ResponseDto;
import uz.java.music.dto.SubjectDto;

import java.util.List;

public interface DepartmentService {
    ResponseDto<DepartmentDto> add(DepartmentDto dto);

    ResponseDto<DepartmentDto> update(DepartmentDto dto);

    ResponseDto<List<DepartmentDto>> getAll();

    ResponseDto<DepartmentDto> getById(Long id);

    ResponseDto<DepartmentDto> delete(Long id);
}
