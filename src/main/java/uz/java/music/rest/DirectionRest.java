package uz.java.music.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.java.music.dto.DirectionDto;
import uz.java.music.dto.SubjectDto;
import uz.java.music.service.DirectionService;

import java.util.List;

@RestController
@RequestMapping("direction")
public record DirectionRest(DirectionService service) {

    @GetMapping
    public ResponseEntity<List<DirectionDto>> getAllSubject(){
        return service.getAll();
    }

    @PostMapping
    public ResponseEntity<DirectionDto> add(@Valid @RequestBody DirectionDto directionDto){
        return service.create(directionDto);
    }

    @PatchMapping
    public ResponseEntity<DirectionDto> update(@Valid @RequestBody DirectionDto directionDto){
        return service.edit(directionDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DirectionDto> delete(@PathVariable Long id){
        return service.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DirectionDto> get(@PathVariable Long id) {
        return service.getById(id);
    }
}
