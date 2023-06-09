package uz.java.music.rest;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.java.music.dto.AdminDto;
import uz.java.music.dto.EventDto;
import uz.java.music.dto.FileDto;
import uz.java.music.dto.ResponseDto;
import uz.java.music.service.FileService;

import java.util.List;

@RestController
@RequestMapping("/file")
public record FileRest(FileService service) {


    @PostMapping(consumes = "multipart/form-data")
    @SecurityRequirement(name = "Authorization")
    public ResponseDto<FileDto> add(@RequestParam("file") MultipartFile file){
        return service.add(file);
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Authorization")
    public ResponseDto<FileDto> delete(@NotNull @PathVariable Long id) {
        return service.delete(id);
    }

    @GetMapping
    public ResponseDto<List<FileDto>> getAll(){
        return service.getAll();
    }

    @GetMapping(value = "/search")
    public ResponseDto<List<FileDto>> getAll(@RequestParam String ext){
        return service.search(ext);
    }



}
