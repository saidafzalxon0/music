package uz.java.music.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uz.java.music.dto.Government_serviceDto;
import uz.java.music.exception.Duplicate;
import uz.java.music.exception.NotSaved;
import uz.java.music.repository.GovernmentRepository;
import uz.java.music.service.Government_serviceService;
import uz.java.music.service.mapper.GovernmentMapper;

import java.util.List;

public class Government_serviceServiceImpl implements Government_serviceService {

    @Autowired
    private GovernmentRepository repository;
    @Autowired
    private GovernmentMapper mapper;
    @Override
    public ResponseEntity<Government_serviceDto> add(Government_serviceDto serviceDto) {
        try{
            return new ResponseEntity<>(mapper.toDto(repository.save(mapper.toEntity(serviceDto))), HttpStatus.CREATED);
        }catch (InvalidDataAccessResourceUsageException e){
            throw new NotSaved("Admin not saved");
        }catch (DataIntegrityViolationException e){
            throw new Duplicate("Admin username already exists");
        }
    }

    @Override
    public ResponseEntity<Government_serviceDto> update(Government_serviceDto serviceDto) {
        return null;
    }

    @Override
    public ResponseEntity<Government_serviceDto> update_link(Long id, String link) {
        return null;
    }

    @Override
    public ResponseEntity<List<Government_serviceDto>> getAll() {
        return null;
    }

    @Override
    public ResponseEntity<Government_serviceDto> delete(Long id) {
        return null;
    }
}
