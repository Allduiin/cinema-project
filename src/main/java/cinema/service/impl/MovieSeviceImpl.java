package cinema.service.impl;

import cinema.dao.MovieDao;
import cinema.lib.Dao;
import cinema.lib.Inject;
import cinema.model.Movie;
import cinema.service.MovieService;
import java.util.List;

@Dao
public class MovieSeviceImpl implements MovieService {
    @Inject
    MovieDao movieDao;

    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }
}
