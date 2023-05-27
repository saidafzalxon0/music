package uz.java.music.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import uz.java.music.dto.AdminDto;
import uz.java.music.entity.Admin;

@Mapper(componentModel = "spring")
public abstract class AdminMapper implements CommonMapper<AdminDto, Admin> {

    @Autowired
    protected PasswordEncoder passwordEncoder;
    @Mapping(target = "password",expression = "java(passwordEncoder.encode(adminDto.getPassword()))")
    public abstract Admin toEntity(AdminDto adminDto);
}
