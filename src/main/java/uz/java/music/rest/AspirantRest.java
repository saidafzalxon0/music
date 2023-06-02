package uz.java.music.rest;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.java.music.dto.AspirantDto;
import uz.java.music.service.AspirantService;

import java.util.List;
@RestController
@RequestMapping("/aspirant")

public record AspirantRest(AspirantService service) {

    @PostMapping
    @SecurityRequirement(name = "Authorization")
    private ResponseEntity<AspirantDto> add(@Valid @RequestBody AspirantDto dto){
        return service.add(dto);
    }
    @PatchMapping
    @SecurityRequirement(name = "Authorization")
    private ResponseEntity<AspirantDto> update(@RequestBody AspirantDto aspirantDto){
        return service.update(aspirantDto);
    }

    @GetMapping("/all")
    private ResponseEntity<List<AspirantDto>> getAll(){
       return service.getAll();
    }
    @GetMapping("/{id}")
    private ResponseEntity<AspirantDto> getById(@PathVariable Long id){
        return service.getById(id);
    }
    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Authorization")
    private ResponseEntity<AspirantDto> delete(@PathVariable Long id){
        return service.delete(id);
    }
}
