package uz.java.music.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.java.music.dto.PositionDto;
import uz.java.music.dto.ResponseDto;
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
    public ResponseDto<SubjectDto> createSubject(SubjectDto subjectDto) {
        try{
            return ResponseDto.<SubjectDto>builder().data(mapper.toDto(repository.save(mapper.toEntity(subjectDto)))).status("success").build();
        }catch (InvalidDataAccessResourceUsageException e){
            throw new NotSaved("Subject not saved");
        }catch (DataIntegrityViolationException e){
            throw new Duplicate("Subject already exists");
        }
    }

    @Override
    public ResponseDto<List<SubjectDto>> getAll() {
        List<SubjectDto> subjectList = repository.findAll().stream().map(mapper::toDto).toList();
        return ResponseDto.<List<SubjectDto>>builder().data(subjectList).status("success").build();
    }

    @Override
    public ResponseDto<SubjectDto> getSubjectById(Long subject_id) {
        if (subject_id == null) {
            throw new NullPointerException("Id is null");
        }
        Optional<Subject> byId = repository.findById(subject_id);
        if (byId.isEmpty()) {
            throw new NullPointerException("Id is empty");
        }
        try {
            return ResponseDto.<SubjectDto>builder().data(mapper.toDto(byId.get())).status("success").build();
        } catch (Exception e){
            throw new NullPointerException("Id is not available");
        }
    }

    @Override
    public ResponseDto<SubjectDto> editSubject(SubjectDto subjectDto) {
        if(subjectDto.getId() == null){
            throw new NotFound("Subject is not found");
        }else {
            if (repository.findById(subjectDto.getId()).isPresent()) {
                return ResponseDto.<SubjectDto>builder().data(mapper.toDto(repository.save(mapper.toEntity(subjectDto)))).status("success").build();
            }else {
                throw new NotSaved("Subject has not been updated");
            }
        }
    }

    @Override
    @Transactional
    public ResponseDto<SubjectDto> deleteSubject(Long subject_id) {
        Optional<Subject> subject = repository.findById(subject_id);
        if(subject.isPresent()){
            try{
                repository.deleteSubject(subject_id);
                return ResponseDto.<SubjectDto>builder().data(mapper.toDto(subject.get())).status("success").build();
            }catch (Exception e){
                throw new NotSaved("Subject has not been deleted");
            }
        }else{
            throw new NotFound("Subject is not found");
        }
    }
}
