package uz.java.music.service;

import org.springframework.http.ResponseEntity;
import uz.java.music.dto.AdminDto;
import uz.java.music.dto.AspirantDto;
import uz.java.music.dto.ResponseDto;

import java.util.List;

public interface AspirantService {
    ResponseDto<AspirantDto> add(AspirantDto dto);

    ResponseDto<AspirantDto> update(AspirantDto aspirantDto);

    ResponseDto<List<AspirantDto>> getAll();

    ResponseDto<AspirantDto> getById(Long id);

    ResponseDto<AspirantDto> delete(Long id);
}
