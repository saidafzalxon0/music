package uz.java.music.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import uz.java.music.dto.FileDto;
import uz.java.music.entity.File;

import java.io.IOException;
import java.util.List;

public interface FileService {


    ResponseEntity<FileDto> add(MultipartFile multipartFile);

    ResponseEntity<List<FileDto>> search(String ext);


    ResponseEntity<FileDto> delete(Long id);

    ResponseEntity<List<FileDto>> getAll();

}
