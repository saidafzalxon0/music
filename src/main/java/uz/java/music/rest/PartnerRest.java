package uz.java.music.rest;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.java.music.dto.PartnerDto;
import uz.java.music.dto.ResponseDto;
import uz.java.music.service.PartnerService;

import java.util.List;

@RestController
@RequestMapping("/partner")

public record PartnerRest(PartnerService partnerService) {
    @PostMapping
    @SecurityRequirement(name = "Authorization")
    public ResponseDto<PartnerDto> add(@Valid @RequestBody PartnerDto partnerDto) {
        return partnerService.add(partnerDto);
    }

    @PatchMapping
    @SecurityRequirement(name = "Authorization")
    public ResponseDto<PartnerDto> update(@RequestBody PartnerDto partnerDto) {
        return partnerService.update(partnerDto);
    }

    @GetMapping
    public ResponseDto<List<PartnerDto>> getAll() {
        return partnerService.getAll();
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Authorization")
    public ResponseDto<PartnerDto> delete(@NotNull @PathVariable Long id) {
        return partnerService.delete(id);
    }

}
