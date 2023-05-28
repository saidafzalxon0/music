package uz.java.music.service.mapper;

import org.mapstruct.Mapper;
import uz.java.music.dto.AspirantDto;
import uz.java.music.entity.Aspirant;

@Mapper(componentModel = "spring")
public interface AspirantMapper extends CommonMapper<AspirantDto, Aspirant> {
}
