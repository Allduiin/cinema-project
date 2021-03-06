package cinema.service;

import cinema.model.MovieSession;
import java.time.LocalDate;
import java.util.List;

public interface MovieSessionService {
    List<MovieSession> getAvailableSessions(Long movieId, LocalDate date);

    MovieSession add(MovieSession session);

    MovieSession getById(Long movieSessionId);
}
