package uz.java.music.service;

import org.springframework.http.ResponseEntity;
import uz.java.music.dto.DirectionDto;
import uz.java.music.dto.ResponseDto;
import uz.java.music.dto.SubjectDto;

import java.util.List;

public interface DirectionService {
    ResponseDto<DirectionDto> create(DirectionDto directionDto);
    ResponseDto<List<DirectionDto>> getAll();
    ResponseDto<DirectionDto> getById(Long direction_id);

    ResponseDto<DirectionDto> edit(DirectionDto directionDto);
    ResponseDto<DirectionDto> delete(Long direction_id);
}
