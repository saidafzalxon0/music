package uz.java.music.service;

import org.springframework.http.ResponseEntity;
import uz.java.music.dto.AspirantDto;
import uz.java.music.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    ResponseEntity<EmployeeDto> add(EmployeeDto dto);

    ResponseEntity<EmployeeDto> update(EmployeeDto dto);

    ResponseEntity<List<EmployeeDto>> getAll();

    ResponseEntity<EmployeeDto> getById(Long id);

    ResponseEntity<EmployeeDto> delete(Long id);
}
