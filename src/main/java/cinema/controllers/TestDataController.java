package cinema.controllers;

import cinema.exceptions.AuthenticationException;
import cinema.model.CinemaHall;
import cinema.model.Movie;
import cinema.model.MovieSession;
import cinema.model.User;
import cinema.security.AuthenticationService;
import cinema.service.CinemaHallService;
import cinema.service.MovieService;
import cinema.service.MovieSessionService;
import cinema.service.OrderService;
import cinema.service.RoleService;
import cinema.service.ShoppingCartService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestDataController {
    private final MovieService movieService;
    private final CinemaHallService cinemaHallService;
    private final MovieSessionService movieSessionService;
    private final AuthenticationService authenticationService;
    private final ShoppingCartService shoppingCartService;
    private final OrderService orderService;

    public TestDataController(MovieService movieService,
                              CinemaHallService cinemaHallService,
                              MovieSessionService movieSessionService,
                              AuthenticationService authenticationService,
                              ShoppingCartService shoppingCartService,
                              OrderService orderService) {
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
        this.movieSessionService = movieSessionService;
        this.authenticationService = authenticationService;
        this.shoppingCartService = shoppingCartService;
        this.orderService = orderService;
    }

    @GetMapping("/inject-test-data")
    public String injectTestData() throws AuthenticationException {
        Movie movie = new Movie();
        movie.setTitle("Fast and Furious 5");
        movieService.add(movie);

        Movie movie2 = new Movie();
        movie2.setTitle("The Lord of the Rings");
        movieService.add(movie2);
        movieService.getAll();

        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(200);
        cinemaHallService.add(cinemaHall);

        MovieSession movieSession = new MovieSession();
        movieSession.setCinemaHall(cinemaHall);
        movieSession.setMovie(movie);
        movieSession.setShowTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(20, 5)));
        movieSessionService.add(movieSession);

        MovieSession movieSession1 = new MovieSession();
        movieSession1.setCinemaHall(cinemaHall);
        movieSession1.setMovie(movie2);
        movieSession1.setShowTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 20)));
        movieSessionService.add(movieSession1);

        User user = authenticationService.registerUser("bob@gmail.com", "1");

        shoppingCartService.addSession(movieSession, user);
        shoppingCartService.addSession(movieSession1, user);

        orderService.completeOrder(shoppingCartService.getByUser(user).getTickets(), user);

        shoppingCartService.addSession(movieSession, user);
        return "Your data was added to db";
    }
}
