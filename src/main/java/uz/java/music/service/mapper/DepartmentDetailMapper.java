package uz.java.music.service.mapper;

import org.mapstruct.Mapper;
import uz.java.music.dto.DepartmentDetailDto;
import uz.java.music.entity.DepartmentDetail;
@Mapper(componentModel = "spring")
public interface DepartmentDetailMapper extends CommonMapper<DepartmentDetailDto, DepartmentDetail> {

}
