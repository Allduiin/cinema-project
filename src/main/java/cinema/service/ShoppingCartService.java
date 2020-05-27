package cinema.service;

import cinema.model.MovieSession;
import cinema.model.ShoppingCart;
import cinema.model.User;

public interface ShoppingCartService {
    ShoppingCart addSession(MovieSession movieSession, User user);

    ShoppingCart getByUser(User user);

    void registerNewShoppingCart(User user);
}
