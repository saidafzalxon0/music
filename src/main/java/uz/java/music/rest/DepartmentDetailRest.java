package uz.java.music.rest;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.java.music.dto.*;
import uz.java.music.service.DepartmentDetailService;
import uz.java.music.service.DepartmentService;

import java.util.List;

@RestController
@RequestMapping("/department_detail")

public record DepartmentDetailRest(DepartmentDetailService service) {

    @PostMapping
    @SecurityRequirement(name = "Authorization")
    private ResponseDto<DepartmentDetailDto> add(@Valid @RequestBody DepartmentDetailDto dto){
        return service.add(dto);
    }
    @PatchMapping
    @SecurityRequirement(name = "Authorization")
    private ResponseDto<DepartmentDetailDto> update(@RequestBody DepartmentDetailDto dto){
        return service.update(dto);
    }

    @GetMapping("/all")
    private ResponseDto<List<DepartmentDetailDto>> getAll(){
        return service.getAll();
    }
    @GetMapping("/{id}")
    private ResponseDto<List<DepartmentDetailDto>> getById(@PathVariable Long id){
        return service.getById(id);
    }

    @GetMapping("/getsub")
    private ResponseDto<List<SubjectAndDirectionDto>> getSubjectAndDirection(@RequestParam("subid") Long subid){
        return service.getSubjectAndDirection(subid);
    }
    @GetMapping("/getemp")
    private ResponseDto<List<DepartmentEmployeeDto>> getEmployee(@RequestParam Long empid){
        return service.getDepartmentEmployee(empid);
    }
    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Authorization")
    private ResponseDto<DepartmentDetailDto> delete(@PathVariable Long id){
        return service.delete(id);
    }
}
