package uz.java.music.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.java.music.dto.DepartmentDetailDto;
import uz.java.music.dto.DepartmentEmployeeDto;
import uz.java.music.dto.SubjectAndDirectionDto;
import uz.java.music.entity.Department;
import uz.java.music.entity.DepartmentDetail;
import uz.java.music.exception.NotFound;
import uz.java.music.exception.NotSaved;
import uz.java.music.repository.DepartmentDetailRepository;
import uz.java.music.service.DepartmentDetailService;
import uz.java.music.service.DepartmentService;
import uz.java.music.service.mapper.DepartmentDetailMapper;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentDetailServiceImpl implements DepartmentDetailService {

    @Autowired
    private DepartmentDetailRepository repository;
    @Autowired
    private DepartmentDetailMapper mapper;
    @Override
    public ResponseEntity<DepartmentDetailDto> add(DepartmentDetailDto dto) {
        try{
            return new ResponseEntity<>(mapper.toDto(repository.save(mapper.toEntity(dto))), HttpStatus.CREATED);
        }catch (InvalidDataAccessResourceUsageException e){
            throw new NotSaved("DepartmentDetail not saved");
        }catch (Exception e){
            throw new NotSaved(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<DepartmentDetailDto> update(DepartmentDetailDto dto) {
        if(dto.getId() == null){
            throw new NotFound("DepartmentDetail is not found");
        }else {
            if (repository.findById(dto.getId()).isPresent()) {
                return new ResponseEntity<>(mapper.toDto(repository.save(mapper.toEntity(dto))), HttpStatus.OK);
            } else {
                throw new NotSaved("DepartmentDetail not updated");
            }
        }
    }

    @Override
    public ResponseEntity<List<DepartmentDetailDto>> getAll() {
        try {
            return new ResponseEntity<>(repository.findAll().stream().map(mapper::toDto).toList(), HttpStatus.OK);
        } catch (InvalidDataAccessResourceUsageException e) {
            throw new NotSaved("Database not connected");
        }
    }

    @Override
    public ResponseEntity<List<DepartmentDetailDto>> getById(Long id) {
        if (id == null) {
            throw new NotFound("Id not found");
        }
        List<DepartmentDetail> byId = repository.findAllByDepartment_Id(id);
        if (byId.isEmpty()) {
            throw new NotFound("Id is empty");
        }
        try {
            return ResponseEntity.ok(byId.stream().map(mapper::toDto).toList());
        } catch (Exception e){
            throw new NotFound("Id is not available");
        }
    }

    @Override
    @Transactional
    public ResponseEntity<DepartmentDetailDto> delete(Long id) {
        Optional<DepartmentDetail> admin = repository.findById(id);
        if(admin.isPresent()){
            try{
                repository.deleteDepartmentDetail(id);
                return new ResponseEntity<>(mapper.toDto(admin.get()),HttpStatus.OK);
            }catch (Exception e){
                throw new NotSaved("DepartmentDetail not deleted");
            }
        }else{
            throw new NotFound("DepartmentDetail is not found");
        }
    }

    @Override
    public ResponseEntity<List<SubjectAndDirectionDto>> getSubjectAndDirection(Long id) {
        if (id == null) {
            throw new NotFound("Id not found");
        }
        List<SubjectAndDirectionDto> byId = repository.getSubjectAndDirection(id);
        if (byId.isEmpty()) {
            throw new NotFound("Id is empty");
        }
        try {
            return ResponseEntity.ok(byId);
        } catch (Exception e){
            throw new NotFound("Id is not available");
        }
    }

    @Override
    public ResponseEntity<List<DepartmentEmployeeDto>> getDepartmentEmployee(Long id) {
        if (id == null) {
            throw new NotFound("Id not found");
        }
        List<DepartmentEmployeeDto> byId = repository.getDepartmentEmployee(id);
        if (byId.isEmpty()) {
            throw new NotFound("Id is empty");
        }
        try {
            return ResponseEntity.ok(byId);
        } catch (Exception e){
            throw new NotFound("Id is not available");
        }
    }
}
