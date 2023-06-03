package uz.java.music.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.java.music.dto.ResponseDto;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerRest {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseDto<Map<String,String>> handleInvalidArgument(MethodArgumentNotValidException exception){
        Map<String,String> map = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
            map.put(fieldError.getField(),fieldError.getDefaultMessage());
        });
        return ResponseDto.<Map<String, String>>builder().status("failed").data(map).build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Duplicate.class)
    public ResponseDto<Map<String,String>> handleBusinessException(Duplicate e){
        Map<String,String> map =  new HashMap<>();
        map.put("errorMessage",e.getMessage());
        return ResponseDto.<Map<String, String>>builder().status("failed").data(map).build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotFound.class)
    public ResponseDto<Map<String,String>> handleBusinessException(NotFound e){
        Map<String,String> map =  new HashMap<>();
        map.put("errorMessage",e.getMessage());
        return ResponseDto.<Map<String, String>>builder().status("failed").data(map).build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotSaved.class)
    public ResponseDto<Map<String,String>> handleBusinessException(NotSaved e){
        Map<String,String> map =  new HashMap<>();
        map.put("errorMessage",e.getMessage());
        return ResponseDto.<Map<String, String>>builder().status("failed").data(map).build();
    }

}
