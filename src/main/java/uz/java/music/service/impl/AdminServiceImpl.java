package uz.java.music.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.java.music.config.JwtService;
import uz.java.music.dto.AdminDto;
import uz.java.music.dto.ResponseDto;
import uz.java.music.entity.Admin;
import uz.java.music.exception.Duplicate;
import uz.java.music.exception.NotFound;
import uz.java.music.exception.NotSaved;
import uz.java.music.repository.AdminRepository;
import uz.java.music.service.AdminService;
import uz.java.music.service.mapper.AdminMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService service;
    @Autowired
    private AdminMapper mapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public ResponseDto<String> signIn(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        var user = repository.findFirstByUsername(username).orElseThrow();
        var jwt = service.generateToken(user);
        return ResponseDto.<String>builder().status("success").data(jwt).build();
    }

    @Override
    public ResponseDto<AdminDto> add(AdminDto adminDto) {
        try{
            return ResponseDto.<AdminDto>builder().data(mapper.toDto(repository.save(mapper.toEntity(adminDto)))).status("success").build();
        }catch (InvalidDataAccessResourceUsageException e){
            throw new NotSaved("Admin not saved");
        }catch (DataIntegrityViolationException e){
            throw new Duplicate("Admin username already exists");
        }
    }

    @Override
    public ResponseDto<AdminDto> update(AdminDto adminDto) {
        if(adminDto.getId() == null){
            throw new NotFound("Admin is not found");
        }else {
            if (repository.findById(adminDto.getId()).isPresent()) {
                return ResponseDto.<AdminDto>builder().data(mapper.toDto(repository.save(mapper.toEntity(adminDto)))).status("success").build();
            } else {
                throw new NotSaved("Admin not updated");
            }
        }
    }
    @Transactional
    @Override
    public ResponseDto<AdminDto> update_username(Long id, String username) {
        try{
            repository.updateUsername(id,username);
            Optional<Admin> admin = repository.findById(id);
            if(admin.isPresent()){
                return ResponseDto.<AdminDto>builder().data(mapper.toDto(admin.get())).status("success").build();
            }else{
                throw new NotSaved("Admin username not updated");
            }
        }catch (InvalidDataAccessResourceUsageException e){
        throw new NotSaved("Admin not updated");
        }catch (DataIntegrityViolationException e){
        throw new Duplicate("Admin username already exists");
        }
    }

    @Transactional
    @Override
    public ResponseDto<AdminDto> update_password(Long id, String old_password, String password) {
        try{
            Optional<Admin> admin = repository.findById(id);
            if(admin.isPresent()){
                if(!passwordEncoder.matches(old_password,admin.get().getPassword())){
                    throw new NotFound("Password is not correct");
                }else{
                    repository.updatePassword(id,passwordEncoder.encode(password));
                    admin = repository.findById(id);
                    if(admin.isPresent()){
                        return ResponseDto.<AdminDto>builder().data(mapper.toDto(admin.get())).status("success").build();
                    }else{
                        throw new NotSaved("Admin not updated");
                    }
                }
            }else{
                throw new NotFound("Admin is not found");
            }
        }catch (InvalidDataAccessResourceUsageException e){
            throw new NotSaved("Admin not updated");
        }
    }

    @Override
    public ResponseDto<List<AdminDto>> getAll() {
        try {
            return ResponseDto.<List<AdminDto>>builder().data(repository.findAll().stream().map(mapper::toDto).toList()).status("success").build();
        } catch (InvalidDataAccessResourceUsageException e) {
            throw new NotSaved("Database not connected");
        }
    }

    @Override
    @Transactional
    public ResponseDto<AdminDto> delete(Long id) {
        Optional<Admin> admin = repository.findById(id);
        if(admin.isPresent()){
            try{
                repository.deleteAdmin(id);
                return ResponseDto.<AdminDto>builder().data(mapper.toDto(admin.get())).status("success").build();
            }catch (Exception e){
                throw new NotSaved("Admin not deleted");
            }
        }else{
            throw new NotFound("Admin is not found");
        }
    }
}
