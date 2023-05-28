package uz.java.music.rest;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.java.music.dto.AspirantDto;
import uz.java.music.dto.DepartmentDetailDto;
import uz.java.music.dto.SubjectAndDirectionDto;
import uz.java.music.service.DepartmentDetailService;
import uz.java.music.service.DepartmentService;

import java.util.List;

public record DepartmentDetailRest(DepartmentDetailService service) {

    @PostMapping
    private ResponseEntity<DepartmentDetailDto> add(@Valid @RequestBody DepartmentDetailDto dto){
        return service.add(dto);
    }
    @PatchMapping
    private ResponseEntity<DepartmentDetailDto> update(@RequestBody DepartmentDetailDto dto){
        return service.update(dto);
    }

    @GetMapping("/all")
    private ResponseEntity<List<DepartmentDetailDto>> getAll(){
        return service.getAll();
    }
    @GetMapping("/{id}")
    private ResponseEntity<List<DepartmentDetailDto>> getById(@PathVariable Long id){
        return service.getById(id);
    }

    @GetMapping("/{ids}")
    private ResponseEntity<List<SubjectAndDirectionDto>> getSubjectAndDirection(@PathVariable Long id){
        return service.getSubjectAndDirection(id);
    }
    @DeleteMapping("/{id}")
    private ResponseEntity<DepartmentDetailDto> delete(@PathVariable Long id){
        return service.delete(id);
    }
}