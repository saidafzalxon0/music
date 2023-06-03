package uz.java.music.service;

import org.springframework.http.ResponseEntity;
import uz.java.music.dto.ResponseDto;
import uz.java.music.dto.SubjectDto;

import java.util.List;

public interface SubjectService {
    ResponseDto<SubjectDto> createSubject(SubjectDto subjectDto);
    ResponseDto<List<SubjectDto>> getAll();
    ResponseDto<SubjectDto> getSubjectById(Long subject_id);

    ResponseDto<SubjectDto> editSubject(SubjectDto subjectDto);
    ResponseDto<SubjectDto> deleteSubject(Long subject_id);
}
