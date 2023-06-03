package uz.java.music.service;

import org.springframework.http.ResponseEntity;
import uz.java.music.dto.EventDto;
import uz.java.music.dto.ResponseDto;

import java.util.List;

public interface EventService {
    ResponseDto<EventDto> create(EventDto eventDto);
    ResponseDto<List<EventDto>> getAll();
    ResponseDto<EventDto> getById(Long event_id);

    ResponseDto<EventDto> edit(EventDto eventDto);
    ResponseDto<EventDto> delete(Long event_id);
}
