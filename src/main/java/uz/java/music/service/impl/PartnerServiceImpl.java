package uz.java.music.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.java.music.dto.PartnerDto;
import uz.java.music.entity.Partner;
import uz.java.music.exception.Duplicate;
import uz.java.music.exception.NotFound;
import uz.java.music.exception.NotSaved;
import uz.java.music.repository.PartnerRepository;
import uz.java.music.service.PartnerService;
import uz.java.music.service.mapper.PartnerMapper;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PartnerServiceImpl implements PartnerService {

    @Autowired
    PartnerRepository repository;

    @Autowired
    PartnerMapper mapper;

    @Override
    public ResponseEntity<PartnerDto> add(PartnerDto partnerDto) {
        try{
            return new ResponseEntity<>(mapper.toDto(repository.save(mapper.toEntity(partnerDto))), HttpStatus.CREATED);
        }catch (InvalidDataAccessResourceUsageException e){
            throw new NotSaved("Partner not saved");
        }catch (DataIntegrityViolationException e){
            throw new Duplicate("Partner already exists");
        }    }

    @Override
    public ResponseEntity<PartnerDto> update(PartnerDto partnerDto) {
        if(partnerDto.getId() == null){
            throw new NotFound("Partner is not found");
        }else {
            if (repository.findById(partnerDto.getId()).isPresent()) {
                return new ResponseEntity<>(mapper.toDto(repository.save(mapper.toEntity(partnerDto))), HttpStatus.OK);
            } else {
                throw new NotSaved("Partner has not been updated");
            }
        }
    }

    @Override
    public ResponseEntity<List<PartnerDto>> getAll() {
        try {
            return new ResponseEntity<>(repository.findAll().stream().map(mapper::toDto).toList(), HttpStatus.OK);
        } catch (InvalidDataAccessResourceUsageException e) {
            throw new NotSaved("No database connection");
        }    }

    @Override
    @Transactional
    public ResponseEntity<PartnerDto> delete(Long id) {
        Optional<Partner> partner = repository.findById(id);
        if(partner.isPresent()){
            try{
                repository.deletePartner(id);
                return new ResponseEntity<>(mapper.toDto(partner.get()),HttpStatus.OK);
            }catch (Exception e){
                throw new NotSaved("Partner has not been deleted");
            }
        }else{
            throw new NotFound("Partner is not found");
        }    }
}
