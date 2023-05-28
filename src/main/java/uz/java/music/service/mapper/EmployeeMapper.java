package uz.java.music.service.mapper;

import org.mapstruct.Mapper;
import uz.java.music.dto.AspirantDto;
import uz.java.music.dto.EmployeeDto;
import uz.java.music.entity.Aspirant;
import uz.java.music.entity.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper extends CommonMapper<EmployeeDto, Employee> {
}