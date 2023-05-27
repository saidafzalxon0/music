package uz.java.music.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class Duplicate extends DataIntegrityViolationException {

    public Duplicate(String message){
        super(message);
    }
}
