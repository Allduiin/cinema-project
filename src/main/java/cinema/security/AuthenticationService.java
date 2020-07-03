package cinema.security;

import cinema.exceptions.AuthenticationException;
import cinema.model.Role;
import cinema.model.User;

public interface AuthenticationService {
    User register(String email, String password) throws AuthenticationException;

    User registerWithRole(String email, String password, Role.RoleName roleName)
            throws AuthenticationException;
}
