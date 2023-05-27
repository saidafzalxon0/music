package uz.java.music.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class AdminDuplicate extends DataIntegrityViolationException {

    public AdminDuplicate(String message){
        super(message);
    }
}
