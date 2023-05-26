package uz.java.music.service;

import org.springframework.http.ResponseEntity;
import uz.java.music.dto.SubjectDto;

public interface SubjectService {
    ResponseEntity<?> createSubject(SubjectDto subjectDto);
    ResponseEntity<?> getAll();
    ResponseEntity<?> getSubjectById(Integer subject_id);

    ResponseEntity<?> editSubject(SubjectDto subjectDto);
    ResponseEntity<?> deleteSubject(Integer subject_id);

}
