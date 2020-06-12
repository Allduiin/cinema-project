package cinema.controllers;

import cinema.mappers.MovieSessionMapper;
import cinema.model.dto.MovieSessionRequestDto;
import cinema.model.dto.MovieSessionResponseDto;
import cinema.service.MovieSessionService;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/moviesessions")
public class MovieSessionController {
    @Autowired
    MovieSessionService movieSessionService;
    @Autowired
    MovieSessionMapper movieSessionMapper;

    @PostMapping("/add")
    public void addMovieSession(@RequestBody MovieSessionRequestDto requestDto) {
        movieSessionService.add(movieSessionMapper
                .getMovieSessionFromMovieSessionRequestDto(requestDto));
    }

    @GetMapping("/available")
    public List<MovieSessionResponseDto> getMovieSessions(
            @RequestParam Long movieId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return movieSessionService
                .findAvailableSessions(movieId, date)
                .stream()
                .map(movieSession -> movieSessionMapper
                        .getMovieSessionResponseDtoFromMovie(movieSession))
                .collect(Collectors.toList());
    }
}
