package uz.java.music.service;

import org.springframework.http.ResponseEntity;
import uz.java.music.dto.AspirantDto;
import uz.java.music.dto.EmployeeDto;
import uz.java.music.dto.ResponseDto;

import java.util.List;

public interface EmployeeService {
    ResponseDto<EmployeeDto> add(EmployeeDto dto);

    ResponseDto<EmployeeDto> update(EmployeeDto dto);

    ResponseDto<List<EmployeeDto>> getAll();

    ResponseDto<EmployeeDto> getById(Long id);

    ResponseDto<EmployeeDto> delete(Long id);
}
