package cinema;

import cinema.config.AppConfig;
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
import cinema.service.ShoppingCartService;
import cinema.service.UserService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) throws AuthenticationException {
        Movie movie = new Movie();
        movie.setTitle("Fast and Furious 5");
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        MovieService movieService = context.getBean(MovieService.class);
        movieService.add(movie);
        movieService.getAll().forEach(System.out::println);

        Movie movie2 = new Movie();
        movie2.setTitle("The Lord of the Rings");
        movieService.add(movie2);
        movieService.getAll();

        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(200);
        CinemaHallService cinemaHallService = context.getBean(CinemaHallService.class);
        cinemaHallService.add(cinemaHall);

        MovieSession movieSession = new MovieSession();
        movieSession.setCinemaHall(cinemaHall);
        movieSession.setMovie(movie);
        movieSession.setShowTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(20, 5)));
        MovieSessionService movieSessionService = context.getBean(MovieSessionService.class);
        movieSessionService.add(movieSession);

        MovieSession movieSession1 = new MovieSession();
        movieSession1.setCinemaHall(cinemaHall);
        movieSession1.setMovie(movie2);
        movieSession1.setShowTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 20)));
        movieSessionService.add(movieSession1);

        AuthenticationService authenticationService = context.getBean(AuthenticationService.class);
        authenticationService.registerUser("alisa@gmail.com", "1");
        authenticationService.registerUser("bob@gmail.com", "2");

        UserService userService = context.getBean(UserService.class);
        User user1 = userService.findByEmail("alisa@gmail.com");
        System.out.println(user1);
        User user2 = userService.findByEmail("bob@gmail.com");
        System.out.println(user2);

        ShoppingCartService shoppingCartService = context.getBean(ShoppingCartService.class);
        shoppingCartService.registerNewShoppingCart(user1);
        shoppingCartService.addSession(movieSession, user1);
        shoppingCartService.addSession(movieSession, user1);
        shoppingCartService.addSession(movieSession1, user1);
        shoppingCartService.getByUser(user1);

        OrderService orderService = context.getBean(OrderService.class);
        orderService.completeOrder(shoppingCartService.getByUser(user1).getTickets(), user1);
        orderService.getOrderHistory(user1).forEach(System.out::println);
    }
}
