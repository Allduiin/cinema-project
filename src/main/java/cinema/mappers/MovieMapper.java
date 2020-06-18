package cinema.mappers;

import cinema.model.Movie;
import cinema.model.dto.MovieRequestAddDto;
import cinema.model.dto.MovieResponseDto;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {
    public MovieResponseDto getMovieResponseDtoFromMovie(Movie movie) {
        MovieResponseDto movieResponseDto = new MovieResponseDto();
        movieResponseDto.setId(movie.getId());
        movieResponseDto.setTitle(movie.getTitle());
        movieResponseDto.setDescription(movie.getDescription());
        return movieResponseDto;
    }

    public Movie getMovieFromMovieRequestDto(MovieRequestAddDto movieRequestAddDto) {
        Movie movie = new Movie();
        movie.setTitle(movieRequestAddDto.getTitle());
        movie.setDescription(movieRequestAddDto.getDescription());
        return movie;
    }
}
