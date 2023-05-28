package uz.java.music.service.mapper;

import org.mapstruct.Mapper;
import uz.java.music.dto.PositionDto;
import uz.java.music.entity.Position;

@Mapper(componentModel = "spring")
public interface PositionMapper extends CommonMapper<PositionDto, Position> {
}
