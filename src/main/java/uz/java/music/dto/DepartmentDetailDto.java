package uz.java.music.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import uz.java.music.entity.Department;
import uz.java.music.entity.Direction;
import uz.java.music.entity.Employee;
import uz.java.music.entity.Subject;
import uz.java.music.status.AppStatusMessage;
@Getter
@Setter
public class DepartmentDetailDto {
    private Long id;
    @NotNull(message = AppStatusMessage.NULL_VALUE)
    @NotEmpty(message = AppStatusMessage.EMPTY_STRING)
    private Department department;
    @NotNull(message = AppStatusMessage.NULL_VALUE)
    @NotEmpty(message = AppStatusMessage.EMPTY_STRING)
    private Employee employee;

    private Subject subject;

    private Direction direction;
}
