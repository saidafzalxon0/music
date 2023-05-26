package uz.java.music.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.java.music.dto.SubjectDto;
import uz.java.music.service.SubjectService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subject")
public class SubjectRest {

    private final SubjectService service;

    @GetMapping
    public ResponseEntity<?> getAllSubject(){
        return service.getAll();
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody SubjectDto subjectDto){
        return service.createSubject(subjectDto);
    }

    @PatchMapping
    public ResponseEntity<?> update(@RequestBody SubjectDto subjectDto){
        return service.editSubject(subjectDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        return service.deleteSubject(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Integer id) {
        return service.getSubjectById(id);
    }
}
