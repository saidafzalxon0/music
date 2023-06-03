package uz.java.music.service.impl;

import jakarta.transaction.Transactional;
import jdk.jfr.TransitionTo;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.java.music.dto.EmployeeDto;
import uz.java.music.dto.EventDto;
import uz.java.music.dto.ResponseDto;
import uz.java.music.entity.Event;
import uz.java.music.entity.Subject;
import uz.java.music.exception.Duplicate;
import uz.java.music.exception.NotFound;
import uz.java.music.exception.NotSaved;
import uz.java.music.repository.EventRepository;
import uz.java.music.service.EventService;
import uz.java.music.service.mapper.EventMapper;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventMapper mapper;
    private final EventRepository repository;

    @Override
    public ResponseDto<EventDto> create(EventDto eventDto) {
        try{
            return ResponseDto.<EventDto>builder().data(mapper.toDto(repository.save(mapper.toEntity(eventDto)))).status("success").build();
        }catch (InvalidDataAccessResourceUsageException e){
            throw new NotSaved("Event does not saved");
        }
    }

    @Override
    public ResponseDto<List<EventDto>> getAll() {
        try {
            return ResponseDto.<List<EventDto>>builder().data(repository.findAll().stream().map(mapper::toDto).toList()).status("success").build();
        } catch (InvalidDataAccessResourceUsageException e) {
            throw new NotSaved("No database connection");
        }
    }

    @Override
    public ResponseDto<EventDto> getById(Long event_id) {
        if (event_id == null) {
            throw new NotFound("Id is null");
        }
        Optional<Event> byId = repository.findById(event_id);
        if (byId.isEmpty()) {
            throw new NotFound("Id is empty");
        }
        try {
            return ResponseDto.<EventDto>builder().data(mapper.toDto(byId.get())).status("success").build();
        } catch (Exception e){
            throw new NotFound("Id is not available");
        }
    }

    @Override
    public ResponseDto<EventDto> edit(EventDto eventDto) {
        if(eventDto.getId() == null){
            throw new NotFound("Event is not found");
        }else {
            if (repository.findById(eventDto.getId()).isPresent()) {
                return ResponseDto.<EventDto>builder().data(mapper.toDto(repository.save(mapper.toEntity(eventDto)))).status("success").build();
            } else {
                throw new NotSaved("Event not updated");
            }
        }
    }

    @Override
    @Transactional
    public ResponseDto<EventDto> delete(Long event_id) {
        Optional<Event> subject = repository.findById(event_id);
        if(subject.isPresent()){
            try{
                repository.deleteEvent(event_id);
                return ResponseDto.<EventDto>builder().data(mapper.toDto(subject.get())).status("success").build();
            }catch (Exception e){
                throw new NotSaved("Event has not been deleted");
            }
        }else{
            throw new NotFound("Event is not found");
        }
    }
}
