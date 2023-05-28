package uz.java.music.service;

import org.springframework.http.ResponseEntity;
import uz.java.music.dto.AdminDto;
import uz.java.music.dto.AspirantDto;

import java.util.List;

public interface AspirantService {
    ResponseEntity<AspirantDto> add(AspirantDto dto);

    ResponseEntity<AspirantDto> update(AspirantDto aspirantDto);

    ResponseEntity<List<AspirantDto>> getAll();

    ResponseEntity<AspirantDto> getById(Long id);

    ResponseEntity<AspirantDto> delete(Long id);
}
