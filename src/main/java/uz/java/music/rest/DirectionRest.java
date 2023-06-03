package uz.java.music.rest;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.java.music.dto.DirectionDto;
import uz.java.music.dto.ResponseDto;
import uz.java.music.dto.SubjectDto;
import uz.java.music.service.DirectionService;

import java.util.List;

@RestController
@RequestMapping("direction")
public record DirectionRest(DirectionService service) {

    @GetMapping
    public ResponseDto<List<DirectionDto>> getAllSubject(){
        return service.getAll();
    }

    @PostMapping
    @SecurityRequirement(name = "Authorization")
    public ResponseDto<DirectionDto> add(@Valid @RequestBody DirectionDto directionDto){
        return service.create(directionDto);
    }

    @PatchMapping
    @SecurityRequirement(name = "Authorization")
    public ResponseDto<DirectionDto> update( @RequestBody DirectionDto directionDto){
        return service.edit(directionDto);
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Authorization")
    public ResponseDto<DirectionDto> delete(@PathVariable Long id){
        return service.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseDto<DirectionDto> get(@PathVariable Long id) {
        return service.getById(id);
    }
}
