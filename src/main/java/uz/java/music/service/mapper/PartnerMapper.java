package uz.java.music.service.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.java.music.dto.PartnerDto;
import uz.java.music.entity.Partner;

@Component
@Mapper(componentModel = "spring")
public interface PartnerMapper extends CommonMapper<PartnerDto, Partner>{
}
