package uz.java.music.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<SubjectDto> createSubject(SubjectDto subjectDto) {
        Subject subject = subjectMapper.toEntity(subjectDto);
        try {
            Subject subjectSave = subjectRepository.save(subject);
            log.info("Subject added {}", subject.getName());
            return ResponseEntity.ok(subjectMapper.toDto(subjectSave));
        } catch (Exception e) {
            log.error("Subject don't added {}", e.getMessage());
            throw new NullPointerException("Subject don't added ");
        }
    }

    @Override
    public ResponseEntity<List<SubjectDto>> getAll() {
        List<SubjectDto> subjectList = subjectRepository.findAll().stream().map(subjectMapper::toDto).toList();
        return ResponseEntity.ok(subjectList);
    }

    @Override
    public ResponseEntity<SubjectDto> getSubjectById(Long subject_id) {
        if (subject_id == null) {
            throw new NullPointerException("Id is null");
        }
        Optional<Subject> byId = subjectRepository.findById(subject_id);
        if (byId.isEmpty()) {
            throw new NullPointerException("Id is empty");
        }
        try {
            return ResponseEntity.ok(subjectMapper.toDto(byId.get()));
        } catch (Exception e){
            throw new NullPointerException("Id is not available");
        }
    }

    @Override
    public ResponseEntity<SubjectDto> editSubject(SubjectDto subjectDto) {
        if (subjectDto.getId() == null) {
            throw new NullPointerException("Id is null");
        }
        Optional<Subject> byId = subjectRepository.findById((subjectDto.getId()));
        if (byId.isEmpty()){
            throw new NullPointerException("Id is empty");
        }
        try{
            Subject editSubject = byId.get();
            if (subjectDto.getName() != null){
                    editSubject.setName(subjectDto.getName());
            }
            subjectRepository.save(editSubject);
            return ResponseEntity.ok(subjectMapper.toDto(editSubject));
        }catch (Exception e) {
            throw new NullPointerException("Subject has not been updated");
        }
    }

    @Override
    public ResponseEntity<SubjectDto> deleteSubject(Long subject_id) {
        if (subject_id == null) {
            log.error("Subject has not been deleted null value");
            throw new NullPointerException("Id is null");
        }
        Optional<Subject> byId = subjectRepository.findById(subject_id);
        if (byId.isEmpty()) {
            log.info("not found id delete subject");
            throw new NullPointerException("Id is not found");
        }
        try {
            subjectRepository.delete(byId.get());
            log.info("Subject has been deleted {} ", subject_id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (NullPointerException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
