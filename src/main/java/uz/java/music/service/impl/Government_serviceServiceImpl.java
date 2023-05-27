package uz.java.music.service.impl;

import jakarta.validation.UnexpectedTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.java.music.dto.Government_serviceDto;
import uz.java.music.entity.Admin;
import uz.java.music.entity.Government_service;
import uz.java.music.exception.Duplicate;
import uz.java.music.exception.NotFound;
import uz.java.music.exception.NotSaved;
import uz.java.music.repository.GovernmentRepository;
import uz.java.music.service.Government_serviceService;
import uz.java.music.service.mapper.GovernmentMapper;

import java.util.List;
import java.util.Optional;

@Service
public class Government_serviceServiceImpl implements Government_serviceService {

    @Autowired
    private GovernmentRepository repository;
    @Autowired
    private GovernmentMapper mapper;
    @Override
    public ResponseEntity<Government_serviceDto> add(Government_serviceDto serviceDto) {
        try{
            return new ResponseEntity<>(mapper.toDto(repository.save(mapper.toEntity(serviceDto))), HttpStatus.CREATED);
        }catch (InvalidDataAccessResourceUsageException e) {
            throw new NotSaved("Government service not saved");
        }catch (DataIntegrityViolationException e){
            throw new NotSaved("File id not found");
        }
    }

    @Override
    public ResponseEntity<Government_serviceDto> update(Government_serviceDto serviceDto) {
            if(serviceDto.getId() == null){
                throw new NotFound("Government service is not found");
            }else {
                if (repository.findById(serviceDto.getId()).isPresent()) {
                    //TODO file servise tekshirish qo'shish kerak.
                    return new ResponseEntity<>(mapper.toDto(repository.save(mapper.toEntity(serviceDto))), HttpStatus.OK);
                } else {
                    throw new NotSaved("Government service not updated");
                }
            }
    }
    @Transactional
    @Override
    public ResponseEntity<Government_serviceDto> update_link(Long id, String link) {
        try{
            repository.updateLink(id,link);
            Optional<Government_service> service = repository.findById(id);
            if(service.isPresent()){
                return new ResponseEntity<>(mapper.toDto(service.get()),HttpStatus.OK);
            }else{
                throw new NotSaved("Government service name not updated");
            }
        }catch (InvalidDataAccessResourceUsageException e){
            throw new NotSaved("Government service not updated");
        }
    }

    @Override
    public ResponseEntity<List<Government_serviceDto>> getAll() {
        try {
            return new ResponseEntity<>(repository.findAll().stream().map(mapper::toDto).toList(), HttpStatus.OK);
        } catch (InvalidDataAccessResourceUsageException e) {
            throw new NotSaved("Database not connected");
        }
    }
    @Transactional
    @Override
    public ResponseEntity<Government_serviceDto> delete(Long id) {
        Optional<Government_service> service = repository.findById(id);
        if(service.isPresent()){
            try{
                repository.deleteAdmin(id);
                return new ResponseEntity<>(mapper.toDto(service.get()),HttpStatus.OK);
            }catch (Exception e){
                throw new NotSaved("Government service not deleted");
            }
        }else{
            throw new NotFound("Government service is not found");
        }
    }
}
