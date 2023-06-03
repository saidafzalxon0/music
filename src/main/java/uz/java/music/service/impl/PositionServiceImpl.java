package uz.java.music.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.java.music.dto.PositionDto;
import uz.java.music.dto.ResponseDto;
import uz.java.music.entity.Position;
import uz.java.music.exception.Duplicate;
import uz.java.music.exception.NotFound;
import uz.java.music.exception.NotSaved;
import uz.java.music.repository.PositionRepository;
import uz.java.music.service.PositionService;
import uz.java.music.service.mapper.PositionMapper;

import java.util.List;
import java.util.Optional;
@Service
public class PositionServiceImpl implements PositionService {
    @Autowired
    private PositionMapper mapper;

    @Autowired
    private PositionRepository repository;

    @Override
    public ResponseDto<PositionDto> createSubject(PositionDto dto) {
        try{
            return ResponseDto.<PositionDto>builder().data(mapper.toDto(repository.save(mapper.toEntity(dto)))).status("success").build();
        }catch (InvalidDataAccessResourceUsageException e){
            throw new NotSaved("Position not saved");
        }catch (DataIntegrityViolationException e){
            throw new Duplicate("Position already exists");
        }
    }

    @Override
    public ResponseDto<List<PositionDto>> getAll() {
        return ResponseDto.<List<PositionDto>>builder().data(repository.findAll().stream().map(mapper::toDto).toList()).status("success").build();
    }

    @Override
    public ResponseDto<PositionDto> getSubjectById(Long position_id) {
        if (position_id == null) {
            throw new NotFound("Id not found");
        }
        Optional<Position> byId = repository.findById(position_id);
        if (byId.isEmpty()) {
            throw new NotFound("Id is empty");
        }
        try {
            return ResponseDto.<PositionDto>builder().data(mapper.toDto(byId.get())).status("success").build();
        } catch (Exception e){
            throw new NotFound("Id is not available");
        }
    }

    @Override
    public ResponseDto<PositionDto> editSubject(PositionDto dto) {
        if(dto.getId() == null){
            throw new NotFound("Position is not found");
        }else {
            if (repository.findById(dto.getId()).isPresent()) {
                return ResponseDto.<PositionDto>builder().data(mapper.toDto(repository.save(mapper.toEntity(dto)))).status("success").build();
            }else {
                throw new NotSaved("Position has not been updated");
            }
        }
    }

    @Override
    @Transactional
    public ResponseDto<PositionDto> deleteSubject(Long id) {
        Optional<Position> optional = repository.findById(id);
        if(optional.isPresent()){
            try{
                repository.deletePosition(id);
                return ResponseDto.<PositionDto>builder().data(mapper.toDto(optional.get())).status("success").build();
            }catch (Exception e){
                throw new NotSaved("Position has not been deleted");
            }
        }else{
            throw new NotFound("Position is not found");
        }
    }
}
