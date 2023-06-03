package uz.java.music.service;

import org.springframework.http.ResponseEntity;
import uz.java.music.dto.AspirantDto;
import uz.java.music.dto.NewDto;
import uz.java.music.dto.ResponseDto;

import java.util.List;

public interface NewService {
    ResponseDto<NewDto> add(NewDto dto);

    ResponseDto<NewDto> update(NewDto aspirantDto);

    ResponseDto<List<NewDto>> getAll();

    ResponseDto<NewDto> getById(Long id);

    ResponseDto<NewDto> delete(Long id);
}
