package cinema.security;

import cinema.exceptions.AuthenticationException;
import cinema.model.User;

public interface AuthenticationService {
    User login(String login, String password) throws AuthenticationException;

    User register(String email, String password) throws AuthenticationException;
}
