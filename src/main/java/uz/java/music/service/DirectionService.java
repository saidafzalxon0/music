package uz.java.music.service;

import org.springframework.http.ResponseEntity;
import uz.java.music.dto.DirectionDto;
import uz.java.music.dto.SubjectDto;

import java.util.List;

public interface DirectionService {
    ResponseEntity<DirectionDto> create(DirectionDto directionDto);
    ResponseEntity<List<DirectionDto>> getAll();
    ResponseEntity<DirectionDto> getById(Long direction_id);

    ResponseEntity<DirectionDto> edit(DirectionDto directionDto);
    ResponseEntity<DirectionDto> delete(Long direction_id);
}
