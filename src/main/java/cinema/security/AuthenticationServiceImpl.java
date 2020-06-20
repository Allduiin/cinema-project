package cinema.security;

import cinema.exceptions.AuthenticationException;
import cinema.model.Role;
import cinema.model.User;
import cinema.service.RoleService;
import cinema.service.ShoppingCartService;
import cinema.service.UserService;
import java.util.Set;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public AuthenticationServiceImpl(UserService userService,
                                     ShoppingCartService shoppingCartService,
                                     PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    public User registerUser(String email, String password) throws AuthenticationException {
        return register(email, password, Role.RoleName.USER);
    }

    @Override
    public User registerAdmin(String email, String password) throws AuthenticationException {
        return register(email, password, Role.RoleName.ADMIN);
    }

    private User register(String email, String password, Role.RoleName roleName)
            throws AuthenticationException {
        if (userService.findByEmail(email) != null) {
            throw new AuthenticationException("This email has already exists");
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(Set.of(roleService.getByRoleName(roleName)));
        user = userService.add(user);
        if (roleName != Role.RoleName.ADMIN) {
            shoppingCartService.registerNewShoppingCart(user);
        }
        return user;
    }
}
