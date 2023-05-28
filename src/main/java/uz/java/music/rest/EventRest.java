package uz.java.music.rest;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.java.music.dto.EventDto;
import uz.java.music.service.EventService;

import java.util.List;

@RestController
@RequestMapping("/event")
public record EventRest(EventService service) {
    @GetMapping
    public ResponseEntity<List<EventDto>> getAll(){
        return service.getAll();
    }

    @PostMapping
    public ResponseEntity<EventDto> add(@Valid @RequestBody EventDto eventDto){
        return service.create(eventDto);
    }

    @PatchMapping
    public ResponseEntity<EventDto> update(@RequestBody EventDto eventDto){
        return service.edit(eventDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EventDto> delete(@PathVariable Long id){
        return service.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDto> get(@PathVariable Long id) {
        return service.getById(id);
    }
}