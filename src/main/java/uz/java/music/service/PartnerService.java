package uz.java.music.service;

import org.springframework.http.ResponseEntity;
import uz.java.music.dto.PartnerDto;

import java.util.List;

public interface PartnerService {
    ResponseEntity<PartnerDto> add(PartnerDto partnerDto);

    ResponseEntity<PartnerDto> update(PartnerDto partnerDto);

    ResponseEntity<List<PartnerDto>> getAll();

    ResponseEntity<PartnerDto> delete(Long id);

}
