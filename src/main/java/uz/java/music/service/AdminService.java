package uz.java.music.service;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import uz.java.music.dto.AdminDto;
import uz.java.music.dto.ResponseDto;
import uz.java.music.entity.Admin;

import java.util.HashMap;
import java.util.List;

public interface AdminService {

    ResponseDto<String> signIn(String username, String password);

    ResponseDto<AdminDto> add(AdminDto adminDto);

    ResponseDto<AdminDto> update(AdminDto adminDto);

    ResponseDto<AdminDto> update_username(Long id, String username);

    ResponseDto<AdminDto> update_password(Long id, String old_password, String password);

    ResponseDto<List<AdminDto>> getAll();

    ResponseDto<AdminDto> delete(Long id);
}
