package uz.java.music.exception;

import org.springframework.dao.InvalidDataAccessResourceUsageException;

public class NotSaved extends InvalidDataAccessResourceUsageException {
    public NotSaved(String message){
        super(message);
    }
}
