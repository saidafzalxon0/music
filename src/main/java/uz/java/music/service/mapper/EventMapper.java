package uz.java.music.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import uz.java.music.dto.AdminDto;
import uz.java.music.dto.EventDto;
import uz.java.music.entity.Admin;
import uz.java.music.entity.Event;

@Mapper(componentModel = "spring")
public abstract class EventMapper implements CommonMapper<EventDto, Event>{

    @Mapping(target = "date", dateFormat = "dd.MM.yyyy")
    public abstract Event toEntity(EventDto eventDto);

    @Mapping(target = "date", dateFormat = "dd.MM.yyyy")
    public abstract EventDto toDto(Event event);
}
