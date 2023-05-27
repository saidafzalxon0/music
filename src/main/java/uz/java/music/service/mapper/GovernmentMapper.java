package uz.java.music.service.mapper;

import org.mapstruct.Mapper;
import uz.java.music.dto.Government_serviceDto;
import uz.java.music.entity.Government_service;

@Mapper(componentModel = "spring")
public interface GovernmentMapper extends CommonMapper<Government_serviceDto, Government_service> {
}
