package uz.java.music.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import uz.java.music.dto.FileDto;
import uz.java.music.dto.ResponseDto;
import uz.java.music.entity.File;

import java.io.IOException;
import java.util.List;

public interface FileService {


    ResponseDto<FileDto> add(MultipartFile multipartFile);

    ResponseDto<List<FileDto>> search(String ext);


    ResponseDto<FileDto> delete(Long id);

    ResponseDto<List<FileDto>> getAll();

}
