package uz.java.music.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.java.music.dto.SubjectDto;
import uz.java.music.entity.Subject;
import uz.java.music.mapper.SubjectMapper;
import uz.java.music.repository.SubjectRepository;
import uz.java.music.service.SubjectService;
import uz.java.music.status.AppStatusMessage;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectMapper subjectMapper;
    private final SubjectRepository subjectRepository;

    @Override
    public ResponseEntity<?> createSubject(SubjectDto subjectDto) {
        Subject subject = subjectMapper.toEntity(subjectDto);
        try {
            Subject subjectSave = subjectRepository.save(subject);
            log.info("Subject added {}", subject.getName());
            return ResponseEntity.ok(subjectMapper.toDto(subjectSave));
        } catch (Exception e) {
            log.error("Subject don't added {}", e.getMessage());
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> getAll() {
        List<SubjectDto> subjectList = subjectRepository.findAll().stream().map(subjectMapper::toDto).toList();
        return ResponseEntity.ok(subjectList);
    }

    @Override
    public ResponseEntity<?> getSubjectById(Integer subject_id) {
        if (subject_id == null) {
            return ResponseEntity.badRequest().body(AppStatusMessage.NULL_VALUE);
        }
        Optional<Subject> byId = subjectRepository.findById(subject_id);
        if (byId.isEmpty()) {
            return ResponseEntity.ok(AppStatusMessage.NOT_FOUND);
        }
        try {
            return ResponseEntity.ok(subjectMapper.toDto(byId.get()));
        } catch (Exception e){
        return ResponseEntity.ok(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> editSubject(SubjectDto subjectDto) {
        if (subjectDto.getId() == null) {
            return ResponseEntity.badRequest().body("Subject id is null");
        }
        Optional<Subject> byId = subjectRepository.findById(Math.toIntExact(subjectDto.getId()));
        if (byId.isEmpty()){
            return ResponseEntity.ok("Subject not found");
        }
        try{
            Subject editSubject = byId.get();
            if (subjectDto.getName() != null){
                    editSubject.setName(subjectDto.getName());
            }
            subjectRepository.save(editSubject);
            return ResponseEntity.ok(subjectMapper.toDto(editSubject));
        }catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> deleteSubject(Integer subject_id) {
        if (subject_id == null) {
            log.error("Subject has not been deleted null value");
            return ResponseEntity.badRequest().body("Null value");
        }
        Optional<Subject> byId = subjectRepository.findById(subject_id);
        if (byId.isEmpty()) {
            log.info("not found id delete subject");
            return ResponseEntity.ok("Not found");
        }
        try {
            subjectRepository.delete(byId.get());
            log.info("Subject has been deleted {} ", subject_id);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }
}
