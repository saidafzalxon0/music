package uz.java.music.rest;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.java.music.dto.PartnerDto;
import uz.java.music.service.PartnerService;

import java.util.List;

@RestController
@RequestMapping("/partner")
public record PartnerRest(PartnerService partnerService) {
    @PostMapping
    public ResponseEntity<PartnerDto> add(@Valid @RequestBody PartnerDto partnerDto) {
        return partnerService.add(partnerDto);
    }

    @PatchMapping
    public ResponseEntity<PartnerDto> update(@RequestBody PartnerDto partnerDto) {
        return partnerService.update(partnerDto);
    }

    @GetMapping
    public ResponseEntity<List<PartnerDto>> getAll() {
        return partnerService.getAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PartnerDto> delete(@NotNull @PathVariable Long id) {
        return partnerService.delete(id);
    }

}
