package uz.java.music.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.java.music.dto.SubjectDto;
import uz.java.music.service.SubjectService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subject")
public class SubjectRest {

    private final SubjectService service;

    @GetMapping
    public ResponseEntity<List<SubjectDto>> getAllSubject(){
        return service.getAll();
    }

    @PostMapping
    public ResponseEntity<SubjectDto> add(@Valid @RequestBody SubjectDto subjectDto){
        return service.createSubject(subjectDto);
    }

    @PatchMapping
    public ResponseEntity<SubjectDto> update(@Valid @RequestBody SubjectDto subjectDto){
        return service.editSubject(subjectDto);
    }

    @DeleteMapping("/{id}")

    public ResponseEntity<SubjectDto> delete(@PathVariable Long id){
        return service.deleteSubject(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectDto> get(@PathVariable Long id) {
        return service.getSubjectById(id);
    }
}
