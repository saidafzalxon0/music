package uz.java.music.service.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.java.music.dto.EventDto;
import uz.java.music.entity.Event;

@Component
@Mapper(componentModel = "spring")
public interface EventMapper extends CommonMapper<EventDto, Event>{
}
