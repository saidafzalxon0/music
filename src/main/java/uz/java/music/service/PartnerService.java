package uz.java.music.service;

import org.springframework.http.ResponseEntity;
import uz.java.music.dto.PartnerDto;
import uz.java.music.dto.ResponseDto;

import java.util.List;

public interface PartnerService {
    ResponseDto<PartnerDto> add(PartnerDto partnerDto);

    ResponseDto<PartnerDto> update(PartnerDto partnerDto);

    ResponseDto<List<PartnerDto>> getAll();

    ResponseDto<PartnerDto> delete(Long id);

}
