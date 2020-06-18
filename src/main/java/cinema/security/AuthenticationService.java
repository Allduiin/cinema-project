package cinema.security;

import cinema.exceptions.AuthenticationException;
import cinema.model.User;

public interface AuthenticationService {
    User registerUser(String email, String password) throws AuthenticationException;

    User registerAdmin(String email, String password) throws AuthenticationException;
}
