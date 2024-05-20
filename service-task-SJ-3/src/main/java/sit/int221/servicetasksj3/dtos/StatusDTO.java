package sit.int221.servicetasksj3.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.*;


@Data
public class StatusDTO {
    private Integer id;
    private String name;
    private String description;
}
