package uz.java.music.service;

import org.springframework.http.ResponseEntity;
import uz.java.music.dto.*;

import java.util.List;

public interface DepartmentDetailService {
    ResponseDto<DepartmentDetailDto> add(DepartmentDetailDto dto);

    ResponseDto<DepartmentDetailDto> update(DepartmentDetailDto dto);

    ResponseDto<List<DepartmentDetailDto>> getAll();

    ResponseDto<List<DepartmentDetailDto>> getById(Long id);

    ResponseDto<DepartmentDetailDto> delete(Long id);

    ResponseDto<List<SubjectAndDirectionDto>> getSubjectAndDirection(Long id);

    ResponseDto<List<DepartmentEmployeeDto>> getDepartmentEmployee(Long id);
}
