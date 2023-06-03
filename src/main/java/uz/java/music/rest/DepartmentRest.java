package uz.java.music.rest;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.java.music.dto.AspirantDto;
import uz.java.music.dto.DepartmentDto;
import uz.java.music.dto.ResponseDto;
import uz.java.music.service.DepartmentService;

import java.util.List;

@RestController
@RequestMapping("/department")

public record DepartmentRest(DepartmentService service) {
    @PostMapping
    @SecurityRequirement(name = "Authorization")
    private ResponseDto<DepartmentDto> add(@Valid @RequestBody DepartmentDto dto){
        return service.add(dto);
    }
    @PatchMapping
    @SecurityRequirement(name = "Authorization")
    private ResponseDto<DepartmentDto> update(@RequestBody DepartmentDto dto){
        return service.update(dto);
    }

    @GetMapping("/all")
    private ResponseDto<List<DepartmentDto>> getAll(){
        return service.getAll();
    }
    @GetMapping("/{id}")
    private ResponseDto<DepartmentDto> getById(@PathVariable Long id){
        return service.getById(id);
    }
    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Authorization")
    private ResponseDto<DepartmentDto> delete(@PathVariable Long id){
        return service.delete(id);
    }
}
