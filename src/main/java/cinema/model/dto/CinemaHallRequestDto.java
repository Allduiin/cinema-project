package cinema.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CinemaHallRequestDto {
    private int capacity;
    private String description;
}
