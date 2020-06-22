package cinema.dao;

import cinema.model.MovieSession;
import java.time.LocalDate;
import java.util.List;

public interface MovieSessionDao {
    List<MovieSession> getAvailableSessions(Long movieId, LocalDate date);

    MovieSession add(MovieSession session);

    MovieSession getById(Long movieSessionId);
}
