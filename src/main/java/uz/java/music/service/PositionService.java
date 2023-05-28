package uz.java.music.service;

import org.springframework.http.ResponseEntity;
import uz.java.music.dto.PositionDto;
import uz.java.music.dto.SubjectDto;

import java.util.List;

public interface PositionService {
    ResponseEntity<PositionDto> createSubject(PositionDto dto);
    ResponseEntity<List<PositionDto>> getAll();
    ResponseEntity<PositionDto> getSubjectById(Long position_id);

    ResponseEntity<PositionDto> editSubject(PositionDto dto);
    ResponseEntity<PositionDto> deleteSubject(Long id);
}
