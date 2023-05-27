package uz.java.music.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.java.music.dto.AdminDto;
import uz.java.music.service.AdminService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public record AdminRest(AdminService adminService) {

    @PostMapping
    public ResponseEntity<AdminDto> add(@Valid @RequestBody AdminDto adminDto) {
        return adminService.add(adminDto);
    }

    @PutMapping("/username")
    public ResponseEntity<AdminDto> update_username(@RequestParam("id") @NotNull(message = "Admin id not found") Long id,@RequestParam("username") @NotNull(message = "Admin username not found") String username){
        return adminService.update_username(id,username);
    }
    @PutMapping("/password")
    public ResponseEntity<AdminDto> update_password(@RequestParam("id") @NotNull(message = "Admin id not found") Long id,@RequestParam("old_password") @NotNull(message = "Admin old_password not found") String old_password, @RequestParam("password") @NotNull(message = "Admin new password not found") String password){
        return adminService.update_password(id,old_password,password);
    }

    @PatchMapping()
    public ResponseEntity<AdminDto> update(@Valid @RequestBody AdminDto adminDto) {
        return adminService.update(adminDto);
    }

    @GetMapping()
    public ResponseEntity<List<AdminDto>> getAll() {
        return adminService.getAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AdminDto> delete(@NotNull @PathVariable Long id) {
        return adminService.delete(id);
    }

}
