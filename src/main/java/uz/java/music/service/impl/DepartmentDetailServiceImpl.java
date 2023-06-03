package uz.java.music.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.java.music.dto.*;
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
    public ResponseDto<DepartmentDetailDto> add(DepartmentDetailDto dto) {
        try{
            return  ResponseDto.<DepartmentDetailDto>builder().data(mapper.toDto(repository.save(mapper.toEntity(dto)))).status("success").build();
        }catch (InvalidDataAccessResourceUsageException e){
            throw new NotSaved("DepartmentDetail not saved");
        }catch (Exception e){
            throw new NotSaved(e.getMessage());
        }
    }

    @Override
    public ResponseDto<DepartmentDetailDto> update(DepartmentDetailDto dto) {
        if(dto.getId() == null){
            throw new NotFound("DepartmentDetail is not found");
        }else {
            if (repository.findById(dto.getId()).isPresent()) {
                return  ResponseDto.<DepartmentDetailDto>builder().data(mapper.toDto(repository.save(mapper.toEntity(dto)))).status("success").build();
            } else {
                throw new NotSaved("DepartmentDetail not updated");
            }
        }
    }

    @Override
    public ResponseDto<List<DepartmentDetailDto>> getAll() {
        try {
            return  ResponseDto.<List<DepartmentDetailDto>>builder().data(repository.findAll().stream().map(mapper::toDto).toList()).status("success").build();
        } catch (InvalidDataAccessResourceUsageException e) {
            throw new NotSaved("Database not connected");
        }
    }

    @Override
    public ResponseDto<List<DepartmentDetailDto>> getById(Long id) {
        if (id == null) {
            throw new NotFound("Id not found");
        }
        List<DepartmentDetail> byId = repository.findAllByDepartment_Id(id);
        if (byId.isEmpty()) {
            throw new NotFound("Id is empty");
        }
        try {
            return  ResponseDto.<List<DepartmentDetailDto>>builder().data(byId.stream().map(mapper::toDto).toList()).status("success").build();
        } catch (Exception e){
            throw new NotFound("Id is not available");
        }
    }

    @Override
    @Transactional
    public ResponseDto<DepartmentDetailDto> delete(Long id) {
        Optional<DepartmentDetail> admin = repository.findById(id);
        if(admin.isPresent()){
            try{
                repository.deleteDepartmentDetail(id);
                return ResponseDto.<DepartmentDetailDto>builder().data(mapper.toDto(admin.get())).status("success").build();
            }catch (Exception e){
                throw new NotSaved("DepartmentDetail not deleted");
            }
        }else{
            throw new NotFound("DepartmentDetail is not found");
        }
    }

    @Override
    public ResponseDto<List<SubjectAndDirectionDto>> getSubjectAndDirection(Long id) {
        if (id == null) {
            throw new NotFound("Id not found");
        }
        List<SubjectAndDirectionDto> byId = repository.getSubjectAndDirection(id);
        if (byId.isEmpty()) {
            throw new NotFound("Data is empty");
        }
        try {
            return  ResponseDto.<List<SubjectAndDirectionDto>>builder().data(byId).status("success").build();
        } catch (Exception e){
            throw new NotFound("Id is not available");
        }
    }

    @Override
    public ResponseDto<List<DepartmentEmployeeDto>> getDepartmentEmployee(Long id) {
        if (id == null) {
            throw new NotFound("Id not found");
        }
        List<DepartmentEmployeeDto> byId = repository.getDepartmentEmployee(id);
        if (byId.isEmpty()) {
            throw new NotFound("Data is empty");
        }
        try {
            return ResponseDto.<List<DepartmentEmployeeDto>>builder().data(byId).status("success").build();
        } catch (Exception e){
            throw new NotFound("Id is not available");
        }
    }
}
