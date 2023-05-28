package uz.java.music.service.mapper;

import org.mapstruct.Mapper;
import uz.java.music.dto.DepartmentDto;
import uz.java.music.entity.Department;

@Mapper(componentModel = "spring")
public interface DepartmentMapper extends CommonMapper<DepartmentDto, Department> {

}
