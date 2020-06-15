package cinema.controllers;

import cinema.exceptions.AuthenticationException;
import cinema.model.dto.UserRequestRegistrationDto;
import cinema.security.AuthenticationService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public void registerUser(@RequestBody @Valid UserRequestRegistrationDto userDto)
            throws AuthenticationException {
        authenticationService.register(userDto.getEmail(), userDto.getPassword());
    }
}
