package uz.java.music.service;

import org.springframework.http.ResponseEntity;
import uz.java.music.dto.AdminDto;
import uz.java.music.dto.Government_serviceDto;
import uz.java.music.dto.ResponseDto;

import java.util.List;

public interface Government_serviceService {

    ResponseDto<Government_serviceDto> add(Government_serviceDto serviceDto);

    ResponseDto<Government_serviceDto> update(Government_serviceDto serviceDto);

    ResponseDto<Government_serviceDto> update_link(Long id, String link);

    ResponseDto<List<Government_serviceDto>> getAll();

    ResponseDto<Government_serviceDto> delete(Long id);
}
