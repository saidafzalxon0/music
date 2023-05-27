package uz.java.music.service.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.java.music.dto.SubjectDto;
import uz.java.music.entity.Subject;


@Component
@Mapper(componentModel = "spring")
public abstract class SubjectMapper implements CommonMapper<SubjectDto, Subject> {
}
