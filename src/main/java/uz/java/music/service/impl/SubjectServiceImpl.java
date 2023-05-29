package uz.java.music.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.java.music.dto.SubjectDto;
import uz.java.music.entity.Direction;
import uz.java.music.entity.Subject;
import uz.java.music.exception.Duplicate;
import uz.java.music.exception.NotFound;
import uz.java.music.exception.NotSaved;
import uz.java.music.service.mapper.SubjectMapper;
import uz.java.music.repository.SubjectRepository;
import uz.java.music.service.SubjectService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectMapper mapper;
    private final SubjectRepository repository;

    @Override
    public ResponseEntity<SubjectDto> createSubject(SubjectDto subjectDto) {
        try{
            return new ResponseEntity<>(mapper.toDto(repository.save(mapper.toEntity(subjectDto))), HttpStatus.CREATED);
        }catch (InvalidDataAccessResourceUsageException e){
            throw new NotSaved("Subject not saved");
        }catch (DataIntegrityViolationException e){
            throw new Duplicate("Subject already exists");
        }
    }

    @Override
    public ResponseEntity<List<SubjectDto>> getAll() {
        List<SubjectDto> subjectList = repository.findAll().stream().map(mapper::toDto).toList();
        return ResponseEntity.ok(subjectList);
    }

    @Override
    public ResponseEntity<SubjectDto> getSubjectById(Long subject_id) {
        if (subject_id == null) {
            throw new NullPointerException("Id is null");
        }
        Optional<Subject> byId = repository.findById(subject_id);
        if (byId.isEmpty()) {
            throw new NullPointerException("Id is empty");
        }
        try {
            return ResponseEntity.ok(mapper.toDto(byId.get()));
        } catch (Exception e){
            throw new NullPointerException("Id is not available");
        }
    }

    @Override
    public ResponseEntity<SubjectDto> editSubject(SubjectDto subjectDto) {
        if(subjectDto.getId() == null){
            throw new NotFound("Subject is not found");
        }else {
            if (repository.findById(subjectDto.getId()).isPresent()) {
                return new ResponseEntity<>(mapper.toDto(repository.save(mapper.toEntity(subjectDto))), HttpStatus.OK);
            }else {
                throw new NotSaved("Subject has not been updated");
            }
        }
    }

    @Override
    @Transactional
    public ResponseEntity<SubjectDto> deleteSubject(Long subject_id) {
        Optional<Subject> subject = repository.findById(subject_id);
        if(subject.isPresent()){
            try{
                repository.deleteSubject(subject_id);
                return new ResponseEntity<>(mapper.toDto(subject.get()),HttpStatus.OK);
            }catch (Exception e){
                throw new NotSaved("Subject has not been deleted");
            }
        }else{
            throw new NotFound("Subject is not found");
        }
    }
}
