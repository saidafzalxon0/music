package uz.java.music.service;

import org.springframework.http.ResponseEntity;
import uz.java.music.dto.AdminDto;
import uz.java.music.dto.Government_serviceDto;

import java.util.List;

public interface Government_serviceService {

    ResponseEntity<Government_serviceDto> add(Government_serviceDto serviceDto);

    ResponseEntity<Government_serviceDto> update(Government_serviceDto serviceDto);

    ResponseEntity<Government_serviceDto> update_link(Long id, String link);

    ResponseEntity<List<Government_serviceDto>> getAll();

    ResponseEntity<Government_serviceDto> delete(Long id);
}
