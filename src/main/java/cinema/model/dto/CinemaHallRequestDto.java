package cinema.model.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CinemaHallRequestDto {
    @NotNull
    private int capacity;
    private String description;
}
