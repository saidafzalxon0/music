package uz.java.music.rest;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.java.music.dto.SubjectDto;
import uz.java.music.service.SubjectService;

import java.util.List;

@RestController
@RequestMapping("/subject")

public record SubjectRest(SubjectService service) {

    @GetMapping
    public ResponseEntity<List<SubjectDto>> getAllSubject(){
        return service.getAll();
    }

    @PostMapping
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<SubjectDto> add(@Valid @RequestBody SubjectDto subjectDto){
        return service.createSubject(subjectDto);
    }

    @PatchMapping
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<SubjectDto> update(@RequestBody SubjectDto subjectDto){
        return service.editSubject(subjectDto);
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<SubjectDto> delete(@PathVariable Long id){
        return service.deleteSubject(id);
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<SubjectDto> get(@PathVariable Long id) {
        return service.getSubjectById(id);
    }
}
