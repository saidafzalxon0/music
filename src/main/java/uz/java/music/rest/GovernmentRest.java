package uz.java.music.rest;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.java.music.dto.AdminDto;
import uz.java.music.dto.Government_serviceDto;
import uz.java.music.dto.ResponseDto;
import uz.java.music.service.Government_serviceService;

import java.util.List;

@RestController
@RequestMapping("/government")
public record GovernmentRest(Government_serviceService service) {
    @PostMapping
    @SecurityRequirement(name = "Authorization")
    public ResponseDto<Government_serviceDto> add(@Valid @RequestBody Government_serviceDto dto) {
        return service.add(dto);
    }

    @PutMapping
    @SecurityRequirement(name = "Authorization")
    public ResponseDto<Government_serviceDto> update_username(@RequestParam("id") @NotNull(message = "Government service id not found") Long id, @RequestParam("link") @NotNull(message = "Government service link not found") String link){
        return service.update_link(id,link);
    }

    @PatchMapping
    @SecurityRequirement(name = "Authorization")
    public ResponseDto<Government_serviceDto> update(@Valid @RequestBody Government_serviceDto dto) {
        return service.update(dto);
    }

    @GetMapping
    public ResponseDto<List<Government_serviceDto>> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Authorization")
    public ResponseDto<Government_serviceDto> delete(@NotNull @PathVariable Long id) {
        return service.delete(id);
    }
}
