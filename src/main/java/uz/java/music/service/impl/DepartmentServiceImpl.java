package uz.java.music.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.java.music.dto.AspirantDto;
import uz.java.music.dto.DepartmentDto;
import uz.java.music.entity.Aspirant;
import uz.java.music.entity.Department;
import uz.java.music.exception.NotFound;
import uz.java.music.exception.NotSaved;
import uz.java.music.repository.DepartmentRepository;
import uz.java.music.service.DepartmentService;
import uz.java.music.service.mapper.DepartmentMapper;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository repository;

    @Autowired
    private DepartmentMapper mapper;
    @Override
    public ResponseEntity<DepartmentDto> add(DepartmentDto dto) {
        try{
            return new ResponseEntity<>(mapper.toDto(repository.save(mapper.toEntity(dto))), HttpStatus.CREATED);
        }catch (InvalidDataAccessResourceUsageException e){
            throw new NotSaved("Aspirant not saved");
        }catch (Exception e){
            throw new NotSaved(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<DepartmentDto> update(DepartmentDto dto) {
        if(dto.getId() == null){
            throw new NotFound("Department is not found");
        }else {
            if (repository.findById(dto.getId()).isPresent()) {
                return new ResponseEntity<>(mapper.toDto(repository.save(mapper.toEntity(dto))), HttpStatus.OK);
            } else {
                throw new NotSaved("Department not updated");
            }
        }
    }

    @Override
    public ResponseEntity<List<DepartmentDto>> getAll() {
        try {
            return new ResponseEntity<>(repository.findAll().stream().map(mapper::toDto).toList(), HttpStatus.OK);
        } catch (InvalidDataAccessResourceUsageException e) {
            throw new NotSaved("Database not connected");
        }
    }

    @Override
    public ResponseEntity<DepartmentDto> getById(Long id) {
        if (id == null) {
            throw new NotFound("Id not found");
        }
        Optional<Department> byId = repository.findById(id);
        if (byId.isEmpty()) {
            throw new NotFound("Id is empty");
        }
        try {
            return ResponseEntity.ok(mapper.toDto(byId.get()));
        } catch (Exception e){
            throw new NotFound("Id is not available");
        }
    }

    @Override
    @Transactional
    public ResponseEntity<DepartmentDto> delete(Long id) {
        Optional<Department> admin = repository.findById(id);
        if(admin.isPresent()){
            try{
                repository.deleteDepartment(id);
                return new ResponseEntity<>(mapper.toDto(admin.get()),HttpStatus.OK);
            }catch (Exception e){
                throw new NotSaved("Department not deleted");
            }
        }else{
            throw new NotFound("Department is not found");
        }
    }
}
