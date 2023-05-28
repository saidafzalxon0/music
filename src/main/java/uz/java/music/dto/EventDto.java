package uz.java.music.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import uz.java.music.entity.File;
import uz.java.music.status.AppStatusMessage;

import java.util.Date;

@Getter
@Setter
public class EventDto {
    private Long id;

    @NotNull(message = AppStatusMessage.NULL_VALUE)
    @NotEmpty(message = AppStatusMessage.EMPTY_STRING)
    @Size(min = 1,max = 5000,message = AppStatusMessage.SIZE_MISMATCH)
    private String title;

    @NotNull(message = AppStatusMessage.NULL_VALUE)
    @NotEmpty(message = AppStatusMessage.EMPTY_STRING)
    @Size(min = 1,max = 10000,message = AppStatusMessage.SIZE_MISMATCH)
    private String body;

    @NotNull(message = AppStatusMessage.NULL_VALUE)
    private Date date;

    @NotNull(message = AppStatusMessage.NULL_VALUE)
    @NotEmpty(message = AppStatusMessage.EMPTY_STRING)
    @Size(min = 5,max = 5,message = AppStatusMessage.SIZE_MISMATCH)
    private String start_time;

    @NotNull(message = AppStatusMessage.NULL_VALUE)
    @NotEmpty(message = AppStatusMessage.EMPTY_STRING)
    @Size(min = 5,max = 5,message = AppStatusMessage.SIZE_MISMATCH)
    private String end_time;

    @NotNull(message = AppStatusMessage.NULL_VALUE)
    @NotEmpty(message = AppStatusMessage.EMPTY_STRING)
    @Size(min = 1,max = 500,message = AppStatusMessage.SIZE_MISMATCH)
    private String location;

    @NotNull(message = AppStatusMessage.NULL_VALUE)
    private File file;
}
