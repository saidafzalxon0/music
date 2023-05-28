package uz.java.music.rest;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.java.music.dto.NewDto;
import uz.java.music.service.NewService;

import java.util.List;

@RestController
@RequestMapping("/news")
public record NewRest(NewService service) {
    @PostMapping
    private ResponseEntity<NewDto> add(@Valid @RequestBody NewDto dto){
        return service.add(dto);
    }
    @PatchMapping
    private ResponseEntity<NewDto> update(@RequestBody NewDto dto){
        return service.update(dto);
    }

    @GetMapping("/all")
    private ResponseEntity<List<NewDto>> getAll(){
        return service.getAll();
    }
    @GetMapping("/{id}")
    private ResponseEntity<NewDto> getById(@PathVariable Long id){
        return service.getById(id);
    }
    @DeleteMapping("/{id}")
    private ResponseEntity<NewDto> delete(@PathVariable Long id){
        return service.delete(id);
    }
}
