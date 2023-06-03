package uz.java.music.service;

import org.springframework.http.ResponseEntity;
import uz.java.music.dto.PositionDto;
import uz.java.music.dto.ResponseDto;
import uz.java.music.dto.SubjectDto;

import java.util.List;

public interface PositionService {
    ResponseDto<PositionDto> createSubject(PositionDto dto);
    ResponseDto<List<PositionDto>> getAll();
    ResponseDto<PositionDto> getSubjectById(Long position_id);

    ResponseDto<PositionDto> editSubject(PositionDto dto);
    ResponseDto<PositionDto> deleteSubject(Long id);
}
