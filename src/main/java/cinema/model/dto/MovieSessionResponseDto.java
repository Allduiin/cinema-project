package cinema.model.dto;

import cinema.model.CinemaHall;
import cinema.model.Movie;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class MovieSessionResponseDto {
    private Long id;
    private Movie movie;
    private CinemaHall cinemaHall;
    private LocalDateTime showTime;
}
