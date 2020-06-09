package cinema.security;

import cinema.exceptions.AuthenticationException;
import cinema.model.User;
import cinema.service.UserService;
import cinema.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private UserService userService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        User userFromDb = userService.findByEmail(email);
        if (userFromDb != null && HashUtil.hashPassword(password, userFromDb.getSalt())
                .equals(userFromDb.getPassword())) {
            return userFromDb;
        }
        throw new AuthenticationException("Incorrect login or password");
    }

    @Override
    public User register(String email, String password) throws AuthenticationException {
        if (userService.findByEmail(email) != null) {
            throw new AuthenticationException("This email has already exists");
        }
        User user = new User();
        user.setEmail(email);
        user.setSalt(HashUtil.getSalt());
        user.setPassword(HashUtil.hashPassword(password, user.getSalt()));

        return userService.add(user);
    }
}
