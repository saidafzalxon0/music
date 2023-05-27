package uz.java.music.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.java.music.dto.DirectionDto;
import uz.java.music.entity.Direction;
import uz.java.music.service.mapper.DirectionMapper;
import uz.java.music.repository.DirectionRepository;
import uz.java.music.service.DirectionService;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.ACCEPTED;

@Service
@RequiredArgsConstructor
@Slf4j
public class DirectionServiceImpl implements DirectionService {

    private final DirectionMapper mapper;
    private final DirectionRepository repository;

    @Override
    public ResponseEntity<DirectionDto> create(DirectionDto directionDto) {
        Direction direction = mapper.toEntity(directionDto);
        try {
            Direction directionSave = repository.save(direction);
            log.info("Direction added {}", direction.getName());
            return ResponseEntity.ok(mapper.toDto(directionSave));
        } catch (Exception e) {
            log.error("Direction don't added {}", e.getMessage());
            throw new NullPointerException("Direction don't added   ");
        }
    }

    @Override
    public ResponseEntity<List<DirectionDto>> getAll() {
        List<DirectionDto> directionList = repository.findAll().stream().map(mapper::toDto).toList();
        return ResponseEntity.ok(directionList);
    }

    @Override
    public ResponseEntity<DirectionDto> getById(Long direction_id) {
        if (direction_id == null) {
            throw new NullPointerException("Id is null");
        }
        Optional<Direction> byId = repository.findById(direction_id);
        if (byId.isEmpty()) {
            throw new NullPointerException("Id is empty");
        }
        try {
            return ResponseEntity.ok(mapper.toDto(byId.get()));
        } catch (Exception e) {
            throw new NullPointerException("Id does not available");

        }
    }

    @Override
    public ResponseEntity<DirectionDto> edit(DirectionDto directionDto) {
        if (directionDto.getId() == null) {
            throw new NullPointerException(directionDto.getId().toString());
        }
        Optional<Direction> byId = repository.findById((directionDto.getId()));
        if (byId.isEmpty()) {
            throw new NullPointerException("Id is empty");
        }
        try {
            Direction editDirection = byId.get();
            if (directionDto.getName() != null) {
                editDirection.setName(directionDto.getName());
            }
            repository.save(editDirection);
            return ResponseEntity.ok(mapper.toDto(editDirection));
        } catch (Exception e) {
            throw new NullPointerException("Direction has not been updated");
        }
    }

    @Override
    public ResponseEntity<DirectionDto> delete(Long direction_id) {
        if (direction_id == null) {
            log.error("Direction has not been deleted null value");
            throw new NullPointerException("Id is null");
        }
        Optional<Direction> byId = repository.findById(direction_id);
        if (byId.isPresent()) {
            log.info("not found id delete subject");
//            throw new NullPointerException("Id is not found");
        }
            try {
                repository.deleteById(direction_id);
                return new ResponseEntity<>(ACCEPTED);
            } catch (Exception e) {
                throw new NullPointerException("Direction has not been deleted");
            }

    }
}

