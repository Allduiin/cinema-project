package cinema.mappers;

import cinema.model.MovieSession;
import cinema.model.dto.MovieSessionRequestDto;
import cinema.model.dto.MovieSessionResponseDto;
import cinema.service.CinemaHallService;
import cinema.service.MovieService;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class MovieSessionMapper {
    private final MovieService movieService;
    private final CinemaHallService cinemaHallService;

    public MovieSessionMapper(MovieService movieService, CinemaHallService cinemaHallService) {
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
    }

    public MovieSessionResponseDto getMovieSessionResponseDtoFromMovie(MovieSession movieSession) {
        MovieSessionResponseDto movieResponseSessionDto = new MovieSessionResponseDto();
        movieResponseSessionDto.setId(movieSession.getId());
        movieResponseSessionDto.setCinemaHall(movieSession.getCinemaHall());
        movieResponseSessionDto.setMovie(movieSession.getMovie());
        movieResponseSessionDto.setShowTime(movieSession.getShowTime());
        return movieResponseSessionDto;
    }

    public MovieSession getMovieSessionFromMovieSessionRequestDto(
            MovieSessionRequestDto movieSessionRequestDto) {
        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movieService.getById(movieSessionRequestDto.getMovieId()));
        movieSession.setCinemaHall(cinemaHallService
                .getById(movieSessionRequestDto.getCinemaHallId()));
        movieSession.setShowTime(LocalDateTime.parse(movieSessionRequestDto.getShowTime()));
        return movieSession;
    }
}
