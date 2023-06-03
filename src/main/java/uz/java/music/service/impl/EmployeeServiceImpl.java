package uz.java.music.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.java.music.dto.DirectionDto;
import uz.java.music.dto.EmployeeDto;
import uz.java.music.dto.ResponseDto;
import uz.java.music.entity.Aspirant;
import uz.java.music.entity.Employee;
import uz.java.music.exception.NotFound;
import uz.java.music.exception.NotSaved;
import uz.java.music.repository.AspirantRepository;
import uz.java.music.repository.EmployeeRepository;
import uz.java.music.service.EmployeeService;
import uz.java.music.service.mapper.AspirantMapper;
import uz.java.music.service.mapper.EmployeeMapper;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private EmployeeMapper mapper;
    @Override
    public ResponseDto<EmployeeDto> add(EmployeeDto dto) {
        try{
            return ResponseDto.<EmployeeDto>builder().data(mapper.toDto(repository.save(mapper.toEntity(dto)))).status("success").build();
        }catch (InvalidDataAccessResourceUsageException e){
            throw new NotSaved("Employee not saved");
        }catch (Exception e){
            throw new NotSaved(e.getMessage());
        }
    }

    @Override
    public ResponseDto<EmployeeDto> update(EmployeeDto dto) {
        if(dto.getId() == null){
            throw new NotFound("Employee is not found");
        }else {
            if (repository.findById(dto.getId()).isPresent()) {
                return ResponseDto.<EmployeeDto>builder().data(mapper.toDto(repository.save(mapper.toEntity(dto)))).status("success").build();
            } else {
                throw new NotSaved("Employee not updated");
            }
        }
    }

    @Override
    public ResponseDto<List<EmployeeDto>> getAll() {
        try {
            return ResponseDto.<List<EmployeeDto>>builder().data(repository.findAll().stream().map(mapper::toDto).toList()).status("success").build();
        } catch (InvalidDataAccessResourceUsageException e) {
            throw new NotSaved("Database not connected");
        }
    }

    @Override
    public ResponseDto<EmployeeDto> getById(Long id) {
        if (id == null) {
            throw new NotFound("Id not found");
        }
        Optional<Employee> byId = repository.findById(id);
        if (byId.isEmpty()) {
            throw new NotFound("Id is empty");
        }
        try {
            return ResponseDto.<EmployeeDto>builder().data(mapper.toDto(byId.get())).status("success").build();
        } catch (Exception e){
            throw new NotFound("Id is not available");
        }
    }

    @Override
    @Transactional
    public ResponseDto<EmployeeDto> delete(Long id) {
        Optional<Employee> optional = repository.findById(id);
        if(optional.isPresent()){
            try{
                repository.deleteEmployee(id);
                return ResponseDto.<EmployeeDto>builder().data(mapper.toDto(optional.get())).status("success").build();
            }catch (Exception e){
                throw new NotSaved("Employee not deleted");
            }
        }else{
            throw new NotFound("Employee is not found");
        }
    }
}
