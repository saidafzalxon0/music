package uz.java.music.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import uz.java.music.status.AppStatusMessage;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class PositionDto {

    private Long id;
    @NotNull(message = AppStatusMessage.NULL_VALUE)
    @NotEmpty(message = AppStatusMessage.EMPTY_STRING)
    @Size(min = 1,max = 255,message = AppStatusMessage.SIZE_MISMATCH)
    private String name;



}
