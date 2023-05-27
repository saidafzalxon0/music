package uz.java.music.exception;

import org.springframework.dao.InvalidDataAccessResourceUsageException;

public class AdminNotSaved extends InvalidDataAccessResourceUsageException {
    public AdminNotSaved(String message){
        super(message);
    }
}
