package uz.java.music.rest;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.java.music.dto.PositionDto;
import uz.java.music.dto.SubjectDto;
import uz.java.music.service.PositionService;

import java.util.List;

@RestController
@RequestMapping("/position")
public record PositionRest(PositionService service) {
    @GetMapping
    public ResponseEntity<List<PositionDto>> getAllSubject(){
        return service.getAll();
    }

    @PostMapping
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<PositionDto> add(@Valid @RequestBody PositionDto dto){
        return service.createSubject(dto);
    }

    @PatchMapping
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<PositionDto> update(@RequestBody PositionDto dto){
        return service.editSubject(dto);
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<PositionDto> delete(@PathVariable Long id){
        return service.deleteSubject(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PositionDto> get(@PathVariable Long id) {
        return service.getSubjectById(id);
    }
}
