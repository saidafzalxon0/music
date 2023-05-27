package uz.java.music.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerRest {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleInvalidArgument(MethodArgumentNotValidException exception){
        Map<String,String> map = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
            map.put(fieldError.getField(),fieldError.getDefaultMessage());
        });
        return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AdminDuplicate.class)
    public ResponseEntity<Map<String,String>> handleBusinessException(AdminDuplicate e){
        Map<String,String> map =  new HashMap<>();
        map.put("errorMessage",e.getMessage());
        return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(AdminNotFound.class)
    public ResponseEntity<Map<String,String>> handleBusinessException(AdminNotFound e){
        Map<String,String> map =  new HashMap<>();
        map.put("errorMessage",e.getMessage());
        return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AdminNotSaved.class)
    public ResponseEntity<Map<String,String>> handleBusinessException(AdminNotSaved e){
        Map<String,String> map =  new HashMap<>();
        map.put("errorMessage",e.getMessage());
        return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
    }

}
