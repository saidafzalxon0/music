package uz.java.music.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.java.music.dto.PositionDto;
import uz.java.music.dto.SubjectDto;
import uz.java.music.entity.Position;
import uz.java.music.entity.Subject;
import uz.java.music.exception.Duplicate;
import uz.java.music.exception.NotFound;
import uz.java.music.exception.NotSaved;
import uz.java.music.repository.PositionRepository;
import uz.java.music.repository.SubjectRepository;
import uz.java.music.service.PositionService;
import uz.java.music.service.mapper.PositionMapper;
import uz.java.music.service.mapper.SubjectMapper;

import java.util.List;
import java.util.Optional;
@Service
public class PositionServiceImpl implements PositionService {
    @Autowired
    private PositionMapper mapper;

    @Autowired
    private PositionRepository repository;

    @Override
    public ResponseEntity<PositionDto> createSubject(PositionDto dto) {
        try{
            return new ResponseEntity<>(mapper.toDto(repository.save(mapper.toEntity(dto))), HttpStatus.CREATED);
        }catch (InvalidDataAccessResourceUsageException e){
            throw new NotSaved("Position not saved");
        }catch (DataIntegrityViolationException e){
            throw new Duplicate("Position already exists");
        }
    }

    @Override
    public ResponseEntity<List<PositionDto>> getAll() {
        return ResponseEntity.ok(repository.findAll().stream().map(mapper::toDto).toList());
    }

    @Override
    public ResponseEntity<PositionDto> getSubjectById(Long position_id) {
        if (position_id == null) {
            throw new NotFound("Id not found");
        }
        Optional<Position> byId = repository.findById(position_id);
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
    public ResponseEntity<PositionDto> editSubject(PositionDto dto) {
        if(dto.getId() == null){
            throw new NotFound("Position is not found");
        }else {
            if (repository.findById(dto.getId()).isPresent()) {
                return new ResponseEntity<>(mapper.toDto(repository.save(mapper.toEntity(dto))), HttpStatus.OK);
            }else {
                throw new NotSaved("Position has not been updated");
            }
        }
    }

    @Override
    @Transactional
    public ResponseEntity<PositionDto> deleteSubject(Long id) {
        Optional<Position> optional = repository.findById(id);
        if(optional.isPresent()){
            try{
                repository.deletePosition(id);
                return new ResponseEntity<>(mapper.toDto(optional.get()),HttpStatus.OK);
            }catch (Exception e){
                throw new NotSaved("Position has not been deleted");
            }
        }else{
            throw new NotFound("Position is not found");
        }
    }
}
