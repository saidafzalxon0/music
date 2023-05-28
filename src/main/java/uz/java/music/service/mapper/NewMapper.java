package uz.java.music.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.java.music.dto.EventDto;
import uz.java.music.dto.NewDto;
import uz.java.music.entity.Event;
import uz.java.music.entity.New;

@Mapper(componentModel = "spring")
public abstract class NewMapper implements CommonMapper<NewDto,New> {
    @Mapping(target = "date", dateFormat = "dd.MM.yyyy")
    public abstract New toEntity(NewDto newDto);

    @Mapping(target = "date", dateFormat = "dd.MM.yyyy")
    public abstract NewDto toDto(New entity);
}
