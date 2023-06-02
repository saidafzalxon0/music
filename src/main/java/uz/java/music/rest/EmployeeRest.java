package uz.java.music.rest;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.java.music.dto.AspirantDto;
import uz.java.music.dto.EmployeeDto;
import uz.java.music.service.EmployeeService;

import java.util.List;
@RestController
@RequestMapping("/employee")
public record EmployeeRest(EmployeeService service) {
    @PostMapping
    @SecurityRequirement(name = "Authorization")
    private ResponseEntity<EmployeeDto> add(@Valid @RequestBody EmployeeDto dto){
        return service.add(dto);
    }
    @PatchMapping
    @SecurityRequirement(name = "Authorization")
    private ResponseEntity<EmployeeDto> update(@RequestBody EmployeeDto dto){
        return service.update(dto);
    }

    @GetMapping("/all")
    private ResponseEntity<List<EmployeeDto>> getAll(){
        return service.getAll();
    }
    @GetMapping("/{id}")
    private ResponseEntity<EmployeeDto> getById(@PathVariable Long id){
        return service.getById(id);
    }
    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Authorization")
    private ResponseEntity<EmployeeDto> delete(@PathVariable Long id){
        return service.delete(id);
    }
}
