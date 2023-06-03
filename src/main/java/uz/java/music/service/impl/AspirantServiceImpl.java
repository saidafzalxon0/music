package uz.java.music.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.java.music.dto.AdminDto;
import uz.java.music.dto.AspirantDto;
import uz.java.music.dto.ResponseDto;
import uz.java.music.entity.Admin;
import uz.java.music.entity.Aspirant;
import uz.java.music.entity.Position;
import uz.java.music.exception.Duplicate;
import uz.java.music.exception.NotFound;
import uz.java.music.exception.NotSaved;
import uz.java.music.repository.AspirantRepository;
import uz.java.music.service.AspirantService;
import uz.java.music.service.mapper.AspirantMapper;

import java.util.List;
import java.util.Optional;

@Service
public class AspirantServiceImpl implements AspirantService {

    @Autowired
    private AspirantRepository repository;

    @Autowired
    private AspirantMapper mapper;
    @Override
    public ResponseDto<AspirantDto> add(AspirantDto dto) {
        try{
            return ResponseDto.<AspirantDto>builder().data(mapper.toDto(repository.save(mapper.toEntity(dto)))).status("success").build();
        }catch (InvalidDataAccessResourceUsageException e){
            throw new NotSaved("Aspirant not saved");
        }catch (Exception e){
            throw new NotSaved(e.getMessage());
        }
    }

    @Override
    public ResponseDto<AspirantDto> update(AspirantDto aspirantDto) {
        if(aspirantDto.getId() == null){
            throw new NotFound("Aspirant is not found");
        }else {
            if (repository.findById(aspirantDto.getId()).isPresent()) {
                return ResponseDto.<AspirantDto>builder().data(mapper.toDto(repository.save(mapper.toEntity(aspirantDto)))).status("success").build();
            } else {
                throw new NotSaved("Aspirant not updated");
            }
        }
    }

    @Override
    public ResponseDto<List<AspirantDto>> getAll() {
        try {
            return ResponseDto.<List<AspirantDto>>builder().data(repository.findAll().stream().map(mapper::toDto).toList()).status("success").build();
        } catch (InvalidDataAccessResourceUsageException e) {
            throw new NotSaved("Database not connected");
        }
    }

    @Override
    public ResponseDto<AspirantDto> getById(Long id) {
        if (id == null) {
            throw new NotFound("Id not found");
        }
        Optional<Aspirant> byId = repository.findById(id);
        if (byId.isEmpty()) {
            throw new NotFound("Id is empty");
        }
        try {
            return ResponseDto.<AspirantDto>builder().data(mapper.toDto(byId.get())).status("success").build();
        } catch (Exception e){
            throw new NotFound("Id is not available");
        }
    }

    @Override
    @Transactional
    public ResponseDto<AspirantDto> delete(Long id) {
        Optional<Aspirant> admin = repository.findById(id);
        if(admin.isPresent()){
            try{
                repository.deleteAspirant(id);
                return ResponseDto.<AspirantDto>builder().data(mapper.toDto(admin.get())).status("success").build();
            }catch (Exception e){
                throw new NotSaved("Aspirant not deleted");
            }
        }else{
            throw new NotFound("Aspirant is not found");
        }
    }
}
