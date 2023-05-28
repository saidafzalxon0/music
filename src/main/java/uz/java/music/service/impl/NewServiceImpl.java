package uz.java.music.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.java.music.dto.NewDto;
import uz.java.music.entity.Aspirant;
import uz.java.music.entity.New;
import uz.java.music.exception.NotFound;
import uz.java.music.exception.NotSaved;
import uz.java.music.repository.AspirantRepository;
import uz.java.music.repository.NewRepository;
import uz.java.music.service.NewService;
import uz.java.music.service.mapper.AspirantMapper;
import uz.java.music.service.mapper.NewMapper;

import java.util.List;
import java.util.Optional;

@Service
public class NewServiceImpl implements NewService {
    @Autowired
    private NewRepository repository;

    @Autowired
    private NewMapper mapper;
    @Override
    public ResponseEntity<NewDto> add(NewDto dto) {
        try{
            return new ResponseEntity<>(mapper.toDto(repository.save(mapper.toEntity(dto))), HttpStatus.CREATED);
        }catch (InvalidDataAccessResourceUsageException e){
            throw new NotSaved("News not saved");
        }catch (Exception e){
            throw new NotSaved(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<NewDto> update(NewDto aspirantDto) {
        if(aspirantDto.getId() == null){
            throw new NotFound("News id is not found");
        }else {
            if (repository.findById(aspirantDto.getId()).isPresent()) {
                return new ResponseEntity<>(mapper.toDto(repository.save(mapper.toEntity(aspirantDto))), HttpStatus.OK);
            } else {
                throw new NotSaved("News not updated");
            }
        }
    }

    @Override
    public ResponseEntity<List<NewDto>> getAll() {
        try {
            return new ResponseEntity<>(repository.findAll().stream().map(mapper::toDto).toList(), HttpStatus.OK);
        } catch (InvalidDataAccessResourceUsageException e) {
            throw new NotSaved("Database not connected");
        }
    }

    @Override
    public ResponseEntity<NewDto> getById(Long id) {
        if (id == null) {
            throw new NotFound("Id not found");
        }
        Optional<New> byId = repository.findById(id);
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
    public ResponseEntity<NewDto> delete(Long id) {
        Optional<New> admin = repository.findById(id);
        if(admin.isPresent()){
            try{
                repository.deleteNew(id);
                return new ResponseEntity<>(mapper.toDto(admin.get()),HttpStatus.OK);
            }catch (Exception e){
                throw new NotSaved("News not deleted");
            }
        }else{
            throw new NotFound("News is not found");
        }
    }
}
