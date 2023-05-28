package uz.java.music.service.impl;

import jakarta.transaction.Transactional;
import jdk.jfr.TransitionTo;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.java.music.dto.EventDto;
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
    public ResponseEntity<EventDto> create(EventDto eventDto) {
        try{
            return new ResponseEntity<>(mapper.toDto(repository.save(mapper.toEntity(eventDto))), HttpStatus.CREATED);
        }catch (InvalidDataAccessResourceUsageException e){
            throw new NotSaved("Event does not saved");
        }catch (DataIntegrityViolationException e){
            throw new Duplicate("Event already exists");
        }
    }

    @Override
    public ResponseEntity<List<EventDto>> getAll() {
        try {
            return new ResponseEntity<>(repository.findAll().stream().map(mapper::toDto).toList(), HttpStatus.OK);
        } catch (InvalidDataAccessResourceUsageException e) {
            throw new NotSaved("No database connection");
        }
    }

    @Override
    public ResponseEntity<EventDto> getById(Long event_id) {
        if (event_id == null) {
            throw new NotFound("Id is null");
        }
        Optional<Event> byId = repository.findById(event_id);
        if (byId.isEmpty()) {
            throw new NotFound("Id is empty");
        }
        try {
            return ResponseEntity.ok(mapper.toDto(byId.get()));
        } catch (Exception e){
            throw new NotFound("Id is not available");
        }
    }

    @Override
    public ResponseEntity<EventDto> edit(EventDto eventDto) {
        if(eventDto.getId() == null){
            throw new NotFound("Event is not found");
        }else {
            if (repository.findById(eventDto.getId()).isPresent()) {
                return new ResponseEntity<>(mapper.toDto(repository.save(mapper.toEntity(eventDto))), HttpStatus.OK);
            } else {
                throw new NotSaved("Event not updated");
            }
        }
    }

    @Override
    @Transactional
    public ResponseEntity<EventDto> delete(Long event_id) {
        Optional<Event> subject = repository.findById(event_id);
        if(subject.isPresent()){
            try{
                repository.deleteEvent(event_id);
                return new ResponseEntity<>(mapper.toDto(subject.get()),HttpStatus.OK);
            }catch (Exception e){
                throw new NotSaved("Event has not been deleted");
            }
        }else{
            throw new NotFound("Event is not found");
        }
    }
}
