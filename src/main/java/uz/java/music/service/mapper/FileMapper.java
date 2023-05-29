package uz.java.music.service.mapper;

import org.mapstruct.Mapper;
import uz.java.music.dto.FileDto;
import uz.java.music.entity.File;

@Mapper(componentModel = "spring")
public interface FileMapper extends CommonMapper<FileDto, File> {
}
