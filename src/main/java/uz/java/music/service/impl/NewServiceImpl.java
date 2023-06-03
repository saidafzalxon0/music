package uz.java.music.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.java.music.dto.Government_serviceDto;
import uz.java.music.dto.NewDto;
import uz.java.music.dto.ResponseDto;
import uz.java.music.entity.Aspirant;
import uz.java.music.entity.New;
import uz.java.music.exception.NotFound;
import uz.java.music.exception.NotSaved;
import uz.java.music.repository.AspirantRepository;
import uz.java.music.repository.NewRepository;
import uz.java.music.service.NewService;
import uz.java.music.service.mapper.AspirantMapper;
import uz.java.music.service.mapper.NewMapper;

import java.util.List;
import java.util.Optional;

@Service
public class NewServiceImpl implements NewService {
    @Autowired
    private NewRepository repository;

    @Autowired
    private NewMapper mapper;
    @Override
    public ResponseDto<NewDto> add(NewDto dto) {
        try{
            return ResponseDto.<NewDto>builder().data(mapper.toDto(repository.save(mapper.toEntity(dto)))).status("success").build();
        }catch (InvalidDataAccessResourceUsageException e){
            throw new NotSaved("News not saved");
        }catch (Exception e){
            throw new NotSaved(e.getMessage());
        }
    }

    @Override
    public ResponseDto<NewDto> update(NewDto aspirantDto) {
        if(aspirantDto.getId() == null){
            throw new NotFound("News id is not found");
        }else {
            if (repository.findById(aspirantDto.getId()).isPresent()) {
                return ResponseDto.<NewDto>builder().data(mapper.toDto(repository.save(mapper.toEntity(aspirantDto)))).status("success").build();
            } else {
                throw new NotSaved("News not updated");
            }
        }
    }

    @Override
    public ResponseDto<List<NewDto>> getAll() {
        try {
            return ResponseDto.<List<NewDto>>builder().data(repository.findAll().stream().map(mapper::toDto).toList()).status("success").build();
        } catch (InvalidDataAccessResourceUsageException e) {
            throw new NotSaved("Database not connected");
        }
    }

    @Override
    public ResponseDto<NewDto> getById(Long id) {
        if (id == null) {
            throw new NotFound("Id not found");
        }
        Optional<New> byId = repository.findById(id);
        if (byId.isEmpty()) {
            throw new NotFound("Id is empty");
        }
        try {
            return ResponseDto.<NewDto>builder().data(mapper.toDto(byId.get())).status("success").build();
        } catch (Exception e){
            throw new NotFound("Id is not available");
        }
    }

    @Override
    @Transactional
    public ResponseDto<NewDto> delete(Long id) {
        Optional<New> admin = repository.findById(id);
        if(admin.isPresent()){
            try{
                repository.deleteNew(id);
                return ResponseDto.<NewDto>builder().data(mapper.toDto(admin.get())).status("success").build();
            }catch (Exception e){
                throw new NotSaved("News not deleted");
            }
        }else{
            throw new NotFound("News is not found");
        }
    }
}
