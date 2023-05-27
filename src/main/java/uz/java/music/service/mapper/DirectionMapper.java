package uz.java.music.service.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.java.music.dto.DirectionDto;
import uz.java.music.entity.Direction;

@Component
@Mapper(componentModel = "spring")
public abstract class DirectionMapper implements CommonMapper<DirectionDto, Direction>{
}
