package cinema.controllers;

import cinema.exceptions.AuthenticationException;
import cinema.model.Role;
import cinema.security.AuthenticationService;
import cinema.service.RoleService;
import javax.annotation.PostConstruct;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InjectRolesUsersController {
    private final AuthenticationService authenticationService;
    private final RoleService roleService;

    public InjectRolesUsersController(AuthenticationService authenticationService,
                                      RoleService roleService) {
        this.authenticationService = authenticationService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void inject() throws AuthenticationException {
        roleService.add(new Role(Role.RoleName.ADMIN));
        roleService.add(new Role(Role.RoleName.USER));
        authenticationService.registerWithRole("Admin", "1", Role.RoleName.ADMIN);
        authenticationService.register("user@gmail.com", "1");
    }
}
