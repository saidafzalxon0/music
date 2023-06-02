package uz.java.music.service;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import uz.java.music.dto.AdminDto;
import uz.java.music.entity.Admin;

import java.util.HashMap;
import java.util.List;

public interface AdminService {

    ResponseEntity<String> signIn(String username,String password);

    ResponseEntity<AdminDto> add(AdminDto adminDto);

    ResponseEntity<AdminDto> update(AdminDto adminDto);

    ResponseEntity<AdminDto> update_username(Long id, String username);

    ResponseEntity<AdminDto> update_password(Long id, String old_password, String password);

    ResponseEntity<List<AdminDto>> getAll();

    ResponseEntity<AdminDto> delete(Long id);
}
