package uz.java.music.service;

import org.springframework.http.ResponseEntity;
import uz.java.music.dto.AspirantDto;
import uz.java.music.dto.NewDto;

import java.util.List;

public interface NewService {
    ResponseEntity<NewDto> add(NewDto dto);

    ResponseEntity<NewDto> update(NewDto aspirantDto);

    ResponseEntity<List<NewDto>> getAll();

    ResponseEntity<NewDto> getById(Long id);

    ResponseEntity<NewDto> delete(Long id);
}
