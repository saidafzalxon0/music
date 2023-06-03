package uz.java.music.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.java.music.dto.DepartmentDto;
import uz.java.music.dto.DirectionDto;
import uz.java.music.dto.ResponseDto;
import uz.java.music.entity.Direction;
import uz.java.music.exception.Duplicate;
import uz.java.music.exception.NotFound;
import uz.java.music.exception.NotSaved;
import uz.java.music.service.mapper.DirectionMapper;
import uz.java.music.repository.DirectionRepository;
import uz.java.music.service.DirectionService;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class DirectionServiceImpl implements DirectionService {

    private final DirectionMapper mapper;
    private final DirectionRepository repository;

    @Override
    public ResponseDto<DirectionDto> create(DirectionDto directionDto) {
        try{
            return ResponseDto.<DirectionDto>builder().data(mapper.toDto(repository.save(mapper.toEntity(directionDto)))).status("success").build();
        }catch (InvalidDataAccessResourceUsageException e){
            throw new NotSaved("Direction not saved");
        }catch (DataIntegrityViolationException e){
            throw new Duplicate("Direction already exists");
        }
    }

    @Override
    public ResponseDto<List<DirectionDto>> getAll() {
        List<DirectionDto> directionList = repository.findAll().stream().map(mapper::toDto).toList();
        return ResponseDto.<List<DirectionDto>>builder().data(directionList).status("success").build();
    }

    @Override
    public ResponseDto<DirectionDto> getById(Long direction_id) {
        if (direction_id == null) {
            throw new NotFound("Id is null");
        }
        Optional<Direction> byId = repository.findById(direction_id);
        if (byId.isPresent()) {
            throw new NotFound("Id is empty");
        }
        try {
            return ResponseDto.<DirectionDto>builder().data(mapper.toDto(byId.get())).status("success").build();
        } catch (Exception e) {
            throw new NotFound("Id does not available");

        }
    }

    @Override
    public ResponseDto<DirectionDto> edit(DirectionDto directionDto) {
        if(directionDto.getId() == null){
            throw new NotFound("Direction is not found");
        }else {
            if (repository.findById(directionDto.getId()).isPresent()) {
                return ResponseDto.<DirectionDto>builder().data(mapper.toDto(repository.save(mapper.toEntity(directionDto)))).status("success").build();
            }else {
                throw new NotSaved("Partner has not been updated");
            }
        }
    }

    @Override
    @Transactional
    public ResponseDto<DirectionDto> delete(Long direction_id) {
        Optional<Direction> direction = repository.findById(direction_id);
        if(direction.isPresent()){
            try{
                repository.deleteDirection(direction_id);
                return ResponseDto.<DirectionDto>builder().data(mapper.toDto(direction.get())).status("success").build();
            }catch (Exception e){
                throw new NotSaved("Direction not deleted");
            }
        }else{
            throw new NotFound("Direction is not found");
        }

    }
}

