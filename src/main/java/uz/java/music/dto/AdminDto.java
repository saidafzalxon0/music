package uz.java.music.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import uz.java.music.service.AdminService;
import uz.java.music.status.AppStatusMessage;
@Getter
@Setter
public class AdminDto{


    private Long id;
    @NotNull(message = AppStatusMessage.NULL_VALUE)
    @NotEmpty(message = AppStatusMessage.EMPTY_STRING)
    @Size(min = 1,max = 255,message = AppStatusMessage.SIZE_MISMATCH)
    private String username;
    @NotNull(message = AppStatusMessage.NULL_VALUE)
    @NotEmpty(message = AppStatusMessage.EMPTY_STRING)
    @Size(min = 1,max = 255,message = AppStatusMessage.SIZE_MISMATCH)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

}
