package uz.java.music.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.java.music.dto.AspirantDto;
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
    public ResponseEntity<AspirantDto> add(AspirantDto dto) {
        try{
            return new ResponseEntity<>(mapper.toDto(repository.save(mapper.toEntity(dto))), HttpStatus.CREATED);
        }catch (InvalidDataAccessResourceUsageException e){
            throw new NotSaved("Aspirant not saved");
        }catch (Exception e){
            throw new NotSaved(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<AspirantDto> update(AspirantDto aspirantDto) {
        if(aspirantDto.getId() == null){
            throw new NotFound("Aspirant is not found");
        }else {
            if (repository.findById(aspirantDto.getId()).isPresent()) {
                return new ResponseEntity<>(mapper.toDto(repository.save(mapper.toEntity(aspirantDto))), HttpStatus.OK);
            } else {
                throw new NotSaved("Aspirant not updated");
            }
        }
    }

    @Override
    public ResponseEntity<List<AspirantDto>> getAll() {
        try {
            return new ResponseEntity<>(repository.findAll().stream().map(mapper::toDto).toList(), HttpStatus.OK);
        } catch (InvalidDataAccessResourceUsageException e) {
            throw new NotSaved("Database not connected");
        }
    }

    @Override
    public ResponseEntity<AspirantDto> getById(Long id) {
        if (id == null) {
            throw new NotFound("Id not found");
        }
        Optional<Aspirant> byId = repository.findById(id);
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
    public ResponseEntity<AspirantDto> delete(Long id) {
        Optional<Aspirant> admin = repository.findById(id);
        if(admin.isPresent()){
            try{
                repository.deleteAspirant(id);
                return new ResponseEntity<>(mapper.toDto(admin.get()),HttpStatus.OK);
            }catch (Exception e){
                throw new NotSaved("Aspirant not deleted");
            }
        }else{
            throw new NotFound("Aspirant is not found");
        }
    }
}
