package cinema.controllers;

import cinema.mappers.MovieMapper;
import cinema.model.dto.MovieRequestAddDto;
import cinema.model.dto.MovieResponseDto;
import cinema.service.MovieService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieMapper movieMapper;

    @PostMapping
    public void addMovie(@RequestBody @Valid MovieRequestAddDto requestDto) {
        movieService.add(movieMapper.getMovieFromMovieRequestDto(requestDto));
    }

    @GetMapping
    public List<MovieResponseDto> getMovies() {
        return movieService.getAll().stream()
                .map(movie -> movieMapper.getMovieResponseDtoFromMovie(movie))
                .collect(Collectors.toList());
    }
}
