package uz.java.music.service;

import org.springframework.http.ResponseEntity;
import uz.java.music.dto.EventDto;

import java.util.List;

public interface EventService {
    ResponseEntity<EventDto> create(EventDto eventDto);
    ResponseEntity<List<EventDto>> getAll();
    ResponseEntity<EventDto> getById(Long event_id);

    ResponseEntity<EventDto> edit(EventDto eventDto);
    ResponseEntity<EventDto> delete(Long event_id);
}
