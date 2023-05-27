package uz.java.music.service;

import org.springframework.http.ResponseEntity;
import uz.java.music.dto.SubjectDto;

import java.util.List;

public interface SubjectService {
    ResponseEntity<SubjectDto> createSubject(SubjectDto subjectDto);
    ResponseEntity<List<SubjectDto>> getAll();
    ResponseEntity<SubjectDto> getSubjectById(Long subject_id);

    ResponseEntity<SubjectDto> editSubject(SubjectDto subjectDto);
    ResponseEntity<SubjectDto> deleteSubject(Long subject_id);

}
